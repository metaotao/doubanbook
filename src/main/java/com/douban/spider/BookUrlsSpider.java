package com.douban.spider;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.douban.dao.InsertUrls;
import com.douban.filter.BloomFilter;
import com.douban.parser.BookParser;
import com.douban.tool.SystemTool;

public class BookUrlsSpider implements Runnable {
	private ConcurrentLinkedQueue<String> inputQueue = new ConcurrentLinkedQueue<String>();

	private ConcurrentLinkedQueue<String> outputQueue = new ConcurrentLinkedQueue<String>();

	// page size
	private int pageSize = SystemTool.PAGE_SIZE;
	private int page = 0;
	private int pageSum = SystemTool.PAGE_SUM;
	// base url
	private String baseUrl = SystemTool.URL;
	// parser rule
	private BookParser bookParser;

	private Logger log = Logger.getLogger(BookUrlsSpider.class.getName());

	// input url
	public BookUrlsSpider(String tag, BookParser bookParser) {
		inputQueue.offer(tag);
		this.bookParser = bookParser;

	}

	public BookUrlsSpider(List<String> tagsList, BookParser bookParser) {
		for (String tag : tagsList) {
			inputQueue.offer(tag);
		}
		this.bookParser = bookParser;
	}

	public String getSearchUrl(String tag, int page) {
		int begin = page * pageSize;
		String urlPath = baseUrl + tag + "?start=" + Integer.toString(begin);
		return urlPath;
	}

	public void getResults(List<String> list) {
		for (String url : list) {
			outputQueue.offer(url);
		}
	}

	public void insertUrls(String tag) {
		String bookurl = null;
		BloomFilter filter = new BloomFilter();
		while (!outputQueue.isEmpty()) {
			bookurl = outputQueue.poll();
			if (filter.contains(bookurl)) {
				System.out.println("contain: " + bookurl);
				continue;
			}
			filter.add(bookurl);

			InsertUrls.getInstance().insertInfo(bookurl, tag);
		}

		System.out.println("data insert finished.");
	}

	public void run() {
		String tag = inputQueue.poll();
		try {
			log.info("[INFO] start running..." + tag);
			while (page < pageSum) {
				String url = getSearchUrl(tag, page);
				System.out.println(url + "************" + tag);
				List<String> urlList = bookParser.bookUrls(url);

				getResults(urlList);
				page++;
				Thread.sleep(900);
			}
			insertUrls(tag);
			page = 0;
			Thread.sleep(2000);

		} catch (UnsupportedEncodingException e) {
			log.error("[ERROR] :", e);
		} catch (Exception e) {
			log.error("[ERROR] :", e);
		}
	}

}
