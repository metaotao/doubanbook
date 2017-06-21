package com.douban.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douban.dao.StartConnection;

public class Test {
	private int count;
	public Test() {
		List<String> list = selectBookTags();
		for (int i = 0; i < list.size(); i++) {
			selectBookCount(list.get(i));
		}
		System.out.println(count);
	}

	public List<String> selectBookTags() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<String> urlsList = new ArrayList<String>();
		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "select tag from types";

			pre = conn.prepareStatement(sql);

			rs = pre.executeQuery();

			while (rs.next()) {
				urlsList.add(rs.getString(1));
			}
		} catch (SQLException e) {

		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {

			}
		}
		return urlsList;
	}

	public void selectBookCount(String tag) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;

		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "select count(*) from bookinfo where tag=?";

			pre = conn.prepareStatement(sql);
			pre.setString(1, tag);
			rs = pre.executeQuery();

			while (rs.next()) {
				System.out.println("标签为：" + tag + "数量为：" + rs.getString(1));
				count++;
			}
		} catch (SQLException e) {

		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args) {
		new Test();
	}
}
