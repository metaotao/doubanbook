package com.douban.spider;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.log4j.Logger;

import com.douban.dao.InsertBookInfo;
import com.douban.model.BookInfoBean;
import com.douban.parser.BookParser;

public class BookInfoSpider implements Runnable {
	private ConcurrentLinkedQueue<String> inputQueue = new ConcurrentLinkedQueue<String>();
	// book info
	private BookInfoBean bookInfo = new BookInfoBean();
	private BookParser bookParser;
	// book url list
	private List<String> urlsList;
	// book tag
	private String tag;

	private Logger log = Logger.getLogger(BookUrlsSpider.class.getName());

	public BookInfoSpider(String tag, BookParser bookParser) {
		this.tag = tag;
		this.bookParser = bookParser;
	}

	public BookInfoSpider(List<String> tagsList, BookParser bookParser) {
		for (String tag : tagsList) {
			inputQueue.offer(tag);
		}
		this.bookParser = bookParser;
	}

	public void run() {
		urlsList = InsertBookInfo.getInstance().selectBookUrls(tag);
		log.info("[INFO] start running..." + tag);
		try {
			while (!urlsList.isEmpty()) {
				String bookUrl = getUrl();
				bookParser.bookParser(bookUrl,tag, bookInfo);
				InsertBookInfo.getInstance().insertBookInfo(bookInfo);
				System.out.println(bookInfo);
				Thread.sleep(900);
			}
			InsertBookInfo.getInstance().updateBookUrls(tag);
			Thread.sleep(2000);
		} catch (Exception e) {
			log.error("[ERROR] database connected error:", e);
		}

	}

	public String getUrl() {
		return urlsList.remove(0);
	}

}
