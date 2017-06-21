package com.douban.dao;

import java.sql.Connection;

public class StartConnection {
	/**
	 * 模拟线程启动 去获得连接
	 */
	private static StartConnection instance;
	private IConnectionPool pool = ConnectionPoolManager.getInstance().getPool("doubanpool");

	public static StartConnection getInstance() {
		if (instance == null) {
			instance = new StartConnection();
		}

		return instance;
	}

	public StartConnection() {
		instance = this;
	}

	public Connection getConnection() {
		Connection conn = null;
		if (pool != null && pool.isActive()) {
			conn = pool.getConnection();
		}
		return conn;
	}

	public void close(Connection conn) {
		ConnectionPoolManager.getInstance().close("doubanpool", conn);
	}

	public Connection getCurrentConnection() {
		Connection conn = null;
		if (pool != null && pool.isActive()) {
			conn = pool.getCurrentConnecton();
		}
		return conn;
	}
}
