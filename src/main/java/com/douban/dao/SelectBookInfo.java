package com.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class SelectBookInfo {
	private Logger log = Logger.getLogger(InsertTag.class.getName());

	// 单例实现
	public static SelectBookInfo getInstance() {
		return Singtonle.instance;
	}

	private static class Singtonle {
		private static SelectBookInfo instance = new SelectBookInfo();
	}
	
	public List<String> selectBookUrls(String tag) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<String> urlsList = new ArrayList<String>();
		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "select bookname ,author ,ratingScore from bookinfo where tag=? and ratingNum>1000";

			pre = conn.prepareStatement(sql);
			pre.setString(1, tag);
			rs = pre.executeQuery();

			while (rs.next()) {
				urlsList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			log.error("[ERROR] database connected error:", e);
		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {

			}
		}
		return urlsList;
	}
}
