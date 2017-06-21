package com.douban.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douban.dao.StartConnection;

public class PieDataAnalysis {
	public static PieDataAnalysis getInstance() {
		return Singtonle.instance;
	}

	private static class Singtonle {
		private static PieDataAnalysis instance = new PieDataAnalysis();
	}

	public List<String> selectBookTags(String type) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<String> tagsList = new ArrayList<String>();
		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "select distinct(tag) from types where type=?";

			pre = conn.prepareStatement(sql);
			pre.setString(1, type);
			rs = pre.executeQuery();

			while (rs.next()) {
				tagsList.add(rs.getString(1));
			}
		} catch (SQLException e) {

		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {

			}
		}
		return tagsList;
	}

	public int getBookCount(String tag) {
		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		int counter = 0;
		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "select count(*) from bookinfo where bookinfo.tag in (select types.tag from types where types.type=?)";
			pre = conn.prepareStatement(sql);
			pre.setString(1, tag);
			rs = pre.executeQuery();
			while (rs.next()) {
				counter = rs.getInt(1);
				
			}

		} catch (SQLException e) {

		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {

			}
		}
		return counter;
	}
}
