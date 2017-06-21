package com.douban.spider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.douban.parser.BookAdapter;
import com.douban.parser.BookParser;
import com.douban.parser.NetConnection;
import com.douban.parser.NetConnectionImpl;
import com.douban.tool.SystemTool;
public class StartSpider {
	private int maxThreadCount = SystemTool.DEFAULT_MAX_THREAD;
	private String urlPath = SystemTool.URL;
	private NetConnection netConn;
	private BookParser bookParser;
	// private BookUrlsSpider bookUrlsSpider;
	private BookInfoSpider bookInfoSpider;
	private ExecutorService executorService;
	private Logger log = Logger.getLogger(StartSpider.class.getName());

	public static void main(String[] args) {
		new StartSpider();
	}

	public StartSpider() {
		netConn = new NetConnectionImpl();
		bookParser = new BookAdapter(netConn);
		startThread();
	}

	public void startThread() {
		List<String> tagList = null;
		try {
			executorService = Executors.newFixedThreadPool(maxThreadCount);
			//tagList = tagsList();
			tagList = new ArrayList<String>(Arrays.asList("悬疑","网络小说","张小娴"));
			
			System.out.println(tagList.size());
			String tag = null;
			while (!tagList.isEmpty()) {
				tag = getTag(tagList);
				// bookUrlsSpider = new BookUrlsSpider(tag, bookParser);
				bookInfoSpider = new BookInfoSpider(tag, bookParser);
				executorService.execute(bookInfoSpider);
			}

			while (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {

			}

		} catch (Exception e) {
			log.error("download thread failed:" + e.getMessage());
		}
	}

	public List<String> tagsList() throws Exception {
		return bookParser.bookTags(urlPath);
	}

	public String getTag(List<String> tagsList) {
		return tagsList.remove(0);
	}

}
