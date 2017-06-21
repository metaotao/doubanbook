package com.douban.parser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.douban.tool.RandomCookie;
import com.douban.tool.SystemTool;

/**
 * Created by taotao on 2017/6/8
 */

public class NetConnectionImpl implements NetConnection {
	private Logger log = Logger.getLogger(NetConnectionImpl.class.getName());
	private int retryCount = SystemTool.RETRY_COUNT;
	private int SLEEP_TIME = SystemTool.SLEEP_TIME;
	private String user_agent = SystemTool.USER_AGENT;

	private String cookie = RandomCookie.getRandomCookie(11);

	public Document getDocument(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(user_agent)
					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("scheme", "https").header("version", "HTTP/1.1")
					.header("accept-encoding", "gzip, deflate, sdch").header("accept-language", "zh-CN,zh;q=0.8")
					.header("cookie", "bid=" + cookie).header("cache-control", "max-age=0").get();

		} catch (Exception e) {
			log.info("[ERROR] Excepion is:" + e.getMessage());
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e1) {
				// e1.printStackTrace();
			}

			retryCount--;
			if (retryCount > 0) {
				log.info("[INFO] the cookie is blocked,reset the cookie...");
				cookie = RandomCookie.getRandomCookie(11);
				getDocument(url);
			} else {
				log.info("[ERROR]Connection is failed:"+e.getMessage());
			}

		}
		return doc;

	}

}
