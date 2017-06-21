package com.douban.proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {
	/**
	 * 通过地址得到document对象
	 * @param url
	 */
	public static Document getDocument(String url) {
			try {
				Proxy.setProxyIp();
				Document document = Jsoup.connect(url).timeout(1000).get();

				if(document == null || document.toString().trim().equals("")) {// 表示ip被拦截或者其他情况
					System.out.println("出现ip被拦截或者其他情况");
					Proxy.setProxyIp();
					getDocument(url);
				}
				return document;
			} catch (Exception e) { // 链接超时等其他情况
				System.out.println("出现链接超时等其他情况");
				Proxy.setProxyIp();// 换代理ip
				getDocument(url);// 继续爬取网页
			}
			return getDocument(url);
		}
	
	
	public static void main(String[] args) {
		System.out.println(getDocument("http://www.zhihu.com/people/feng-zhi-wei-77/"));
	}
}
