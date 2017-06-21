package com.douban.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.douban.model.BookInfoBean;

public class InsertBookInfo {
	private Logger log = Logger.getLogger(InsertBookInfo.class);

	// 单例实现
	public static InsertBookInfo getInstance() {
		return Singtonle.instance;
	}

	private static class Singtonle {
		private static InsertBookInfo instance = new InsertBookInfo();
	}

	public List<String> selectBookUrls(String tag) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<String> urlsList = new ArrayList<String>();
		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "select bookUrl from bookurls where tag=? ";

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

	public void updateBookUrls(String tag) {
		Connection conn = null;
		PreparedStatement pre = null;

		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "update bookurls set iscrawled =1 where tag=?";

			pre = conn.prepareStatement(sql);
			
			pre.setString(1, tag);
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

	public void insertBookInfo(BookInfoBean bookInfoBean) {

		Connection conn = null;
		PreparedStatement pre = null;
		try {
			String bookUrl = bookInfoBean.getBookUrl();
			String bookname = bookInfoBean.getBookname();
			String tag = bookInfoBean.getTag();
			String author = bookInfoBean.getAuthor();
			String ratingScore = bookInfoBean.getRatingScore();
			String ratingNum = bookInfoBean.getRatingNum();
			String price = bookInfoBean.getPrice();
			String publisher = bookInfoBean.getPublisher();
			String publishYear = bookInfoBean.getPublishYear();
			String commentNum = bookInfoBean.getCommentNum();

			conn = StartConnection.getInstance().getConnection();
			String sql = "insert into bookinfo_2(url,tag,bookname,author,ratingScore,ratingNum,price,publisher,publishYear,commentNum) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";

			pre = conn.prepareStatement(sql);

			pre.setString(1, bookUrl);
			pre.setString(2, tag);
			pre.setString(3, bookname);
			pre.setString(4, author);
			pre.setString(5, ratingScore);
			pre.setString(6, ratingNum);
			pre.setString(7, price);
			pre.setString(8, publisher);
			pre.setString(9, publishYear);
			pre.setString(10, commentNum);

			pre.executeUpdate();

		} catch (SQLException e) {
			log.error("[ERROR] database connected error:", e);
		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

	}

}
