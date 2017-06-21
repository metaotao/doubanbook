package com.douban.parser;

import java.util.List;

import com.douban.model.BookInfoBean;

/**
 * Created by taotao on 2017/6/8
 */

public interface BookParser {
	public List<String> bookTags(String url) throws Exception;

	public List<String> bookUrls(String url) throws Exception;

	public void bookParser(String url, String tag, BookInfoBean bookInfoBean);

}
