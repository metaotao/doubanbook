package com.douban.parser;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.douban.model.BookInfoBean;

public class BookAdapter implements BookParser {
	private NetConnection netConnection;
	private Logger log = Logger.getLogger(NetConnectionImpl.class.getName());

	public BookAdapter(NetConnection netConnection) {
		this.netConnection = netConnection;

	}

	public Document getDocument(String url) {
		return netConnection.getDocument(url);
	}

	public List<String> bookTags(String url) throws Exception {

		Document doc = getDocument(url);
		List<String> tagsList = new ArrayList<String>();
		URI baseUrl = new URI(url);

		String relativeUrl = null;
		String absoluteUrl = null;
		String text = null;
		Elements elements = doc.select("a");

		for (Element element : elements) {
			relativeUrl = element.attr("href");
			text = element.text();

			if (relativeUrl.startsWith("/tag/")) {

				URI abs = baseUrl.resolve(relativeUrl);
				absoluteUrl = abs.toURL().toString();
				/**
				 * 一次插入数据库后 避免重复插入
				 */
				// InsertTag.getInstance().insertInfo(absoluteUrl, text);
				tagsList.add(text);
				System.out.println(relativeUrl + "  " + text + "  " + absoluteUrl);
			}
		}
		return tagsList;
	}

	public List<String> bookUrls(String url) throws Exception {
		Document doc = getDocument(url);
		List<String> urlsList = new ArrayList<String>();

		String absoluteUrl = null;
		String text = null;
		/**
		 * method 1
		 */
		Elements elements = doc.select("ul.subject-list >li");
		for (Element element : elements) {
			Elements elems = element.select("a");

			for (Element elem : elems) {
				absoluteUrl = elem.attr("href");

				text = elem.text();
				if (absoluteUrl.startsWith("https") && ("").equals(text)) {
					urlsList.add(absoluteUrl);
					System.out.println(absoluteUrl);
				}
			}
		}

		/**
		 * method 2
		 */
		// Elements elements = doc.select("div.book-list > dl");
		// for (Element element : elements) {
		// Elements elems = element.select("a");
		// for (Element elem : elems) {
		// absoluteUrl = elem.attr("href");
		//
		// text = elem.text();
		// if (absoluteUrl.startsWith("https") && !("").equals(text)) {
		// urlsList.add(absoluteUrl);
		// System.out.println(absoluteUrl + " " + text);
		// }
		// }
		// }
		return urlsList;
	}

	public void bookParser(String url, String tag, BookInfoBean bookInfoBean) {
		try {
			Document doc = getDocument(url);

			Elements elements = doc.select("a.nbg,#info,div.rating_self>strong,div.rating_sum>span,div.mod-hd>h2");
			Element e1 = elements.get(0);
			Element e2 = elements.get(1);
			Element e3 = elements.get(2);
			Element e4 = elements.get(3);
			Element e5 = elements.get(4);

			bookInfoBean.setBookUrl(url);
			bookInfoBean.setTag(tag);
			String bookName = e1.attr("title");
			bookInfoBean.setBookname(bookName);

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
					bookInfoBean.setAuthor(author);
				} else if (split[i].equals("出版社:")) {
					publisher = split[i + 1];
					bookInfoBean.setPublisher(publisher);
				} else if (split[i].equals("定价:")) {
					price = split[i + 1];
					bookInfoBean.setPrice(price);
				} else if (split[i].equals("出版年:")) {
					publishYear = split[i + 1];
					bookInfoBean.setPublishYear(publishYear);
				}
				// System.out.println(split[i]);
			}

			String ratingScore = e3.text();
			bookInfoBean.setRatingScore(ratingScore);

			String s4 = e4.text();
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m4 = p.matcher(s4);
			String ratingNum = m4.replaceAll("").trim();
			bookInfoBean.setRatingNum(ratingNum);

			String s5 = e5.select("span.pl").text();
			Matcher m5 = p.matcher(s5);
			String commentNum = m5.replaceAll("").trim();
			bookInfoBean.setCommentNum(commentNum);

		} catch (Exception e) {
			log.debug("[ERROR] :"+ e.getMessage());
		}
	}
}
