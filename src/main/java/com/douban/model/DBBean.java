package com.douban.model;

public class DBBean {
	private String drivername;
	private String url;
	private String username;
	private String password;
	private String poolName = "doubanpool";
	private int minConnections;
	private int maxConnections;
	private long connTimeOut;
	private int maxActiveConnections;
	private long connectionTimeOut = 1000 * 60 * 20;
	private int initConnections = 5;
	private boolean isCurrentConnection = true;
	private boolean isCheckPool = true;
	private long lazyCheck = 1000 * 60 * 60;
	private long periodCheck = 1000 * 60 * 60;

	public DBBean(String drivername, String url, String username, String password, String poolName) {
		super();
		this.drivername = drivername;
		this.url = url;
		this.username = username;
		this.password = password;
		this.poolName = poolName;
	}

	public DBBean() {

	}

	public int getInitConnections() {
		return initConnections;
	}

	public void setInitConnections(int initConnections) {
		this.initConnections = initConnections;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public int getMinConnections() {
		return minConnections;
	}

	public void setMinConnections(int minConnections) {
		this.minConnections = minConnections;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public long getConnTimeOut() {
		return connTimeOut;
	}

	public void setConnTimeOut(long connTimeOut) {
		this.connTimeOut = connTimeOut;
	}

	public int getMaxActiveConnections() {
		return maxActiveConnections;
	}

	public void setMaxActiveConnections(int maxActiveConnections) {
		this.maxActiveConnections = maxActiveConnections;
	}

	public long getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(long connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public boolean isCurrentConnection() {
		return isCurrentConnection;
	}

	public void setCurrentConnection(boolean isCurrentConnection) {
		this.isCurrentConnection = isCurrentConnection;
	}

	public boolean isCheckPool() {
		return isCheckPool;
	}

	public void setCheckPool(boolean isCheckPool) {
		this.isCheckPool = isCheckPool;
	}

	public long getLazyCheck() {
		return lazyCheck;
	}

	public void setLazyCheck(long lazyCheck) {
		this.lazyCheck = lazyCheck;
	}

	public long getPeriodCheck() {
		return periodCheck;
	}

	public void setPeriodCheck(long periodCheck) {
		this.periodCheck = periodCheck;
	}
}
