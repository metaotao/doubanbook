package com.douban.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestSpider {

	public static void main(String[] args) {
		new TestSpider();
	}

	public TestSpider() {
		startThread();
	}

	public void startThread() {
		try {
			bookParser("https://book.douban.com/subject/27060376/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void bookParser(String url) throws Exception {
		Document doc = getDocument(url);

		Elements elements = doc.select("a.nbg,#info,div.rating_self>strong,div.rating_sum>span,div.mod-hd>h2");
		
		System.out.println(elements);
		Element e1 = elements.get(0);
		Element e2 = elements.get(1);
		Element e3 = elements.get(2);
		Element e4 = elements.get(3);
		Element e5 = elements.get(4);

		String bookName = e1.attr("title");

		String s2 = e2.text();
		String author = null;
		String publisher = null;
		String price = null;
		String publishYear = null;

		String[] split = s2.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (split[i].contains("作者:")) {
				author = split[i + 1];
				if (split[i + 1].contains("[")) {
					author = split[i + 1] + split[i + 2];
				}

			} else if (split[i].equals("出版社:")) {
				publisher = split[i + 1];

			} else if (split[i].equals("定价:")) {
				price = split[i + 1];

			} else if (split[i].equals("出版年:")) {
				publishYear = split[i + 1];

			}
			// System.out.println(split[i]);
		}

		String ratingScore = e3.text();

		String s4 = e4.text();
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m4 = p.matcher(s4);
		String ratingNum = m4.replaceAll("").trim();

		String s5 = e5.select("span.pl").text();
		Matcher m5 = p.matcher(s5);
		String commentNum = m5.replaceAll("").trim();
		System.out.println(bookName + " " + author + " " + price + " " + publisher + " " + publishYear + " "
				+ ratingScore + " " + ratingNum + " " + commentNum);
	}

	public Document getDocument(String url) {
		Document doc = null;
		try {

			doc = Jsoup.connect(url)
					.userAgent(
							"User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36")
					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("scheme", "https").header("version", "HTTP/1.1")
					.header("accept-encoding", "gzip, deflate, sdch").header("accept-language", "zh-CN,zh;q=0.8")
					// .header("cookie", "bid=\"Q5KWZL7y8g7\";")
					.header("cache-control", "max-age=0").get();

		} catch (Exception e) {

		}
		return doc;

	}
}
