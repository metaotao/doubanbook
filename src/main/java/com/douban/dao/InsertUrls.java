package com.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class InsertUrls {
	private Logger log = Logger.getLogger(InsertTag.class.getName());

	// 单例实现
	public static InsertUrls getInstance() {
		return Singtonle.instance;
	}

	private static class Singtonle {
		private static InsertUrls instance = new InsertUrls();
	}

	public void insertInfo(String url,String tag) {
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "insert into bookurls(bookUrl,tag) values(?,?)";
			pre = conn.prepareStatement(sql);

			pre.setString(1, url);
			pre.setString(2, tag);
			pre.executeUpdate();

		} catch (SQLException e) {
			log.error("[ERROR] database connected error:", e);
		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {

			}
		}
	}

}
