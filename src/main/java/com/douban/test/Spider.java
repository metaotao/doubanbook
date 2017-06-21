package com.douban.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {

	public void run() {
		// 调用getTagsStringList()获取标签列表并且对这个链表遍历获取该标签的图书。
		List<String> tagList = getTagsStringList();
		for (int i = 0; i < tagList.size(); i++) {
			// 获取该标签的图书
			
			getBookListbyTag(tagList.get(i));
		}
	}

	// 获取标签tag的所有图书
	public List<String> getBookListbyTag(String tag) {
		
		// 循环豆瓣的页面，豆瓣在https://www.douban.com/tag/标签/book?strat=起始书本编号
		// 这个页面展示数量为20的书本，利用循环控制起始编号获取该标签的所有书本
		for (int num = 0;; num += 15) {
			try {
				// 使用Jsoup连接，需要导入Jsoup包，网上下一个就好了，注意必须设置cookie，要不然多访问几次豆瓣会禁止访问，cookie随便设置一个值，不过最好模仿豆瓣给浏览器返回的。当然也可以实现先从豆瓣得到cookie。
				Document doc = Jsoup
						.connect("https://www.douban.com/tag/" + tag + "/book?"+"start="+Integer.toString(num))
						.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
						.header("scheme", "https").header("version", "HTTP/1.1")
						.header("accept-encoding", "gzip, deflate, sdch").header("accept-language", "zh-CN,zh;q=0.8")
						.header("cache-control", "max-age=0")
						.userAgent(
								"Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")
						.header("cookie", "bid=\"Q5KWZL7y8g7\";").get();
				Elements bookElements = doc.select("div.book-list > dl");
				// 如果当前页已经没有书本了，跳出循环
				if (bookElements.size() < 1)
					break;
				System.out.println(bookElements.select("a.title").html());
				for (int i = 0; i < bookElements.size(); i++) {
					String bookName = bookElements.get(i).select("a.title").html();
					String bookRank = bookElements.get(i).select("span.rating_nums").html();
					if (!bookRank.equals("")) {

						System.out.println(bookName + bookRank + "tag" + tag + "num" + num);

						// bookList.add(book);
					}
				}
				// 暂停2秒，豆瓣对一定时间内范围有次数限制，2秒是我实验过程中最小的数字了，多了就会403错误，2s间隔大概2小时能读完豆瓣自给标签的50000本书
				Thread.sleep(2000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// 若报错了，则重新读取这个页面
				num -= 15;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// return bookList;
		return null;
	}

	// 该函数实现获取标签List
	public List<String> getTagsStringList() {
		List<String> lis = new ArrayList<String>();
		try {
			// 利用Jsoup连接标签列表页面，同样设置cookie
			Document doc = Jsoup.connect("https://book.douban.com/tag/").header("host", "book.douban.com")
					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("scheme", "https").header("version", "HTTP/1.1")
					.header("accept-encoding", "gzip, deflate, sdch").header("accept-language", "zh-CN,zh;q=0.8")
					.header("cookie", "bid=\"Q5KWZL7y8g7\";").header("cache-control", "max-age=0")
					.userAgent(
							"Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36")
					.get();
			
			URI baseUrl=null;
			try {
				baseUrl = new URI("https://book.douban.com/tag/");
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
					lis.add(text);
					System.out.println(text + "  " + absoluteUrl);
				}
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lis;
	}

	public static void main(String[] args) {
		Spider s = new Spider();
		s.run();

	}
}
