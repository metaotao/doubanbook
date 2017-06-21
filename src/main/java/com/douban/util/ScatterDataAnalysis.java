package com.douban.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douban.dao.StartConnection;
import com.douban.util.bean.RatingDisBean;

public class ScatterDataAnalysis {
	public static ScatterDataAnalysis getInstance() {
		return Singtonle.instance;
	}

	private static class Singtonle {
		private static ScatterDataAnalysis instance = new ScatterDataAnalysis();
	}

	public List<RatingDisBean> selectBookInfo() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pre = null;
		List<RatingDisBean> beansList = new ArrayList<RatingDisBean>();
		RatingDisBean ratingBean;
		try {
			conn = StartConnection.getInstance().getConnection();
			String sql = "select ratingScore,ratingNum from bookinfo where ratingNum>50000+0 group by url";

			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();

			while (rs.next()) {
				ratingBean = new RatingDisBean();
				ratingBean.setDatingScore(rs.getString(1));
				ratingBean.setDatingNum(rs.getString(2));
				beansList.add(ratingBean);
			}
		} catch (SQLException e) {

		} finally {
			try {
				pre.close();
				StartConnection.getInstance().close(conn);
			} catch (Exception e) {

			}
		}
		return beansList;
	}

}
