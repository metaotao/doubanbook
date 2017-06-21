package com.douban.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.douban.model.DBBean;

public class ConnectionPool implements IConnectionPool {

	// 连接池配置属性
	private DBBean dbBean = new DBBean();
	// 连接池活动状态
	private boolean isActive = false;
	// 记录创建总的连接数
	private int countActive = 0;
	// 空闲连接
	private List<Connection> freeConnection = new Vector<Connection>();
	// 活动连接
	private List<Connection> activeConnection = new Vector<Connection>();

	// 将线程和连接绑定，保证事务能同一执行
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	public ConnectionPool(DBBean dbBean) {
		super();
		this.dbBean = dbBean;
		init();
		checkPool();
		
	}

	public void init() {
		try {
			Class.forName(dbBean.getDrivername());

			for (int i = 0; i < dbBean.getInitConnections(); i++) {
				Connection conn = newConnection();
				// 初始化最小连接数
				if (conn != null) {
					freeConnection.add(conn);
					countActive++;
				}

			}
			isActive = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得当前连接
	public Connection getCurrentConnecton() {
		// TODO Auto-generated method stub
		// 默认从线程里面取
		Connection conn = threadLocal.get();
		if (!isValid(conn)) {
			conn = getConnection();
		}

		return conn;
	}

	// 获得连接
	public synchronized Connection getConnection() {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			// 判断是否超过最大连接数限制
			if (countActive < dbBean.getMaxActiveConnections()) {
				if (freeConnection.size() > 0) {
					conn = freeConnection.get(0);
					if (conn != null) {
						threadLocal.set(conn);
					}
					freeConnection.remove(0);
				} else {
					conn = newConnection();
				}
			} else {
				// 继续获得连接 直到重新获得连接
				wait(dbBean.getConnTimeOut());
				conn = getConnection();
			}

			if (isValid(conn)) {
				activeConnection.add(conn);
				countActive++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 获得新连接
	private synchronized Connection newConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		if (dbBean != null) {
			Class.forName(dbBean.getDrivername());
			conn = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUsername(), dbBean.getPassword());
		}
		return conn;
	}

	// 释放连接
	public synchronized void releaseConn(Connection conn) throws SQLException {
		if (isValid(conn) && !(freeConnection.size() > dbBean.getMaxConnections())) {
			freeConnection.add(conn);
			activeConnection.remove(conn);
			countActive--;
			threadLocal.remove();
			// 唤醒所有正待等待的线程
			notifyAll();
		}
	}

	// 判断连接是否可用
	private boolean isValid(Connection conn) {
		try {
			if (conn == null || conn.isClosed()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// 销毁连接池
	public synchronized void destroy() {
		for (Connection conn : freeConnection) {
			try {
				if (isValid(conn)) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Connection conn : activeConnection) {
			try {
				if (isValid(conn)) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		isActive = false;
		countActive = 0;
	}

	// 连接池状态
	public boolean isActive() {
		return isActive;
	}

	// 定时检查连接池情况
	public void checkPool() {
		if (dbBean.isCheckPool()) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					// 1.对线程里面的连接状态
					// 2.连接池最小 最大连接数
					System.out.println("空连接池连接数：" + freeConnection.size());
					System.out.println("活动连接数：：" + activeConnection.size());
					System.out.println("总的连接数：" + countActive);
				}
			}, dbBean.getLazyCheck(), dbBean.getPeriodCheck());
		}
	}

}
