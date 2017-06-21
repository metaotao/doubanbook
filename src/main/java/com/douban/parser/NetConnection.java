package com.douban.parser;

import org.jsoup.nodes.Document;

/**
 * Created by taotao on 2017/6/8
 */
public interface NetConnection {
	public Document getDocument(String url);
}
