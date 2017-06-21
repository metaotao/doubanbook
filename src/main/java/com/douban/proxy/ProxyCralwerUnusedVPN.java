package com.douban.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONObject;

public class ProxyCralwerUnusedVPN {
	ThreadLocal<Integer> localWantedNumber = new ThreadLocal<Integer>();
	ThreadLocal<List<ProxyInfo>> localProxyInfos = new ThreadLocal<List<ProxyInfo>>();

	public static void main(String[] args) {
		ProxyCralwerUnusedVPN proxyCrawler = new ProxyCralwerUnusedVPN();
		/**
		 * 想要获取的代理IP个数，由需求方自行指定。（如果个数太多，将导致返回变慢）
		 */
		proxyCrawler.startCrawler(2);
	}

	/**
	 * 暴露给外部模块调用的入口
	 * 
	 * @param wantedNumber
	 *            调用方期望获取到的代理IP个数
	 */
	public String startCrawler(int wantedNumber) {
		localWantedNumber.set(wantedNumber);

	    kuaidailiCom("http://www.kuaidaili.com/free/outha/", 15);  
	    kuaidailiCom("http://www.mimiip.com/hw/", 15);
		kuaidailiCom("http://www.xicidaili.com/nn/", 15);
		kuaidailiCom("http://www.xicidaili.com/nt/", 15);
		kuaidailiCom("http://www.mimiip.com/gngao/", 15);	
		kuaidailiCom("http://www.xicidaili.com/wt/", 15);
		kuaidailiCom("http://www.kuaidaili.com/free/inha/", 15);
		kuaidailiCom("http://www.kuaidaili.com/free/intr/", 15);
		kuaidailiCom("http://www.kuaidaili.com/free/outtr/", 15);

		/**
		 * 构造返回数据
		 */
		ProxyResponse response = new ProxyResponse();
		response.setSuccess("true");
		Map<String, Object> dataInfoMap = new HashMap<String, Object>();
		dataInfoMap.put("numFound", localProxyInfos.get().size());
		dataInfoMap.put("pageNum", 1);
		dataInfoMap.put("proxy", localProxyInfos.get());
		response.setData(dataInfoMap);
		String responseString = JSONObject.toJSON(response).toString();
		System.out.println(responseString);
		return responseString;
	}

	private void kuaidailiCom(String baseUrl, int totalPage) {
		String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{1,6}";
		Pattern ipPtn = Pattern.compile(ipReg);

		for (int i = 1; i < totalPage; i++) {
			if (getCurrentProxyNumber() >= localWantedNumber.get()) {
				return;
			}
			try {
				Document doc = Jsoup.connect(baseUrl + i + "/")
						.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
						.header("Accept-Encoding", "gzip, deflate, sdch")
						.header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6").header("Cache-Control", "max-age=0")
						.header("User-Agent",
								"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
						.header("Cookie",
								"Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1462812244; _gat=1; _ga=GA1.2.1061361785.1462812244")
						.header("Host", "www.kuaidaili.com").header("Referer", "http://www.kuaidaili.com/free/outha/")
						.timeout(30 * 1000).get();
				Matcher m = ipPtn.matcher(doc.text());

				while (m.find()) {
					if (getCurrentProxyNumber() >= localWantedNumber.get()) {
						break;
					}
					String[] strs = m.group().split(" ");
					if (checkProxy(strs[0], Integer.parseInt(strs[1]))) {
						System.out.println("获取到可用代理IP\t" + strs[0] + "\t" + strs[1]);
						addProxy(strs[0], strs[1], "http");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean checkProxy(String ip, Integer port) {
		try {
			// http://1212.ip138.com/ic.asp 可以换成任何比较快的网页
			Jsoup.connect("http://1212.ip138.com/ic.asp").timeout(2 * 1000).proxy(ip, port).get();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private int getCurrentProxyNumber() {
		List<ProxyInfo> proxyInfos = localProxyInfos.get();
		if (proxyInfos == null) {
			proxyInfos = new ArrayList<ProxyInfo>();
			localProxyInfos.set(proxyInfos);
			return 0;
		} else {
			return proxyInfos.size();
		}
	}

	private void addProxy(String ip, String port, String protocol) {
		List<ProxyInfo> proxyInfos = localProxyInfos.get();
		if (proxyInfos == null) {
			proxyInfos = new ArrayList<ProxyInfo>();
			proxyInfos.add(new ProxyInfo(ip, port, protocol));
		} else {
			proxyInfos.add(new ProxyInfo(ip, port, protocol));
		}
	}
}

class ProxyInfo {
	private String userName = "";
	private String ip;
	private String password = "";
	private String type;
	private String port;
	private int is_internet = 1;

	public ProxyInfo(String ip, String port, String type) {
		this.ip = ip;
		this.type = type;
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getIs_internet() {
		return is_internet;
	}

	public void setIs_internet(int is_internet) {
		this.is_internet = is_internet;
	}
}

class ProxyResponse {
	private String success;
	private Map<String, Object> data;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
