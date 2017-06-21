package com.douban.model;

import java.io.Serializable;

public class BookInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bookname;
	private String bookUrl;
	private String tag;
	private String author;
	private String ratingScore;
	private String ratingNum;
	private String price;
	private String publisher;
	private String publishYear;
	private String commentNum;

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRatingScore() {
		return ratingScore;
	}

	public void setRatingScore(String ratingScore) {
		this.ratingScore = ratingScore;
	}

	public String getRatingNum() {
		return ratingNum;
	}

	public void setRatingNum(String ratingNum) {
		this.ratingNum = ratingNum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String toString() {
		return "标签为:" + tag + "  " + bookname + " " + author + " " + publisher + " " + publishYear + " " + ratingScore
				+ " " + ratingNum + " " + commentNum;

	}

}
