package com.douban.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.douban.model.DBBean;

public class ReadDBTool {
	private DBBean bean = new DBBean();

	private static ReadDBTool instance;
	private List<DBBean> beans = new ArrayList<DBBean>();

	public static ReadDBTool getInstance() {
		if (instance == null) {
			instance = new ReadDBTool();
		}
		return instance;
	}

	public ReadDBTool() {
		instance = this;
		parserXml("data\\db.xml");
	}

	public void setBeans(List<DBBean> beans) {
		this.beans = beans;
	}

	public List<DBBean> getBeans() {
		return beans;
	}

	public DBBean getBean() {
		return bean;
	}

	public void setBean(DBBean bean) {
		this.bean = bean;
	}

	@SuppressWarnings("unchecked")
	public void parserXml(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for (Element element : elements) {
				List<Element> elementList = element.elements();
				for (Element ele : elementList) {
					String name = ele.getName();
					if (name.equals("drivername")) {
						bean.setDrivername(ele.getText());

					}
					if (name.equals("urlConn")) {
						System.out.println(ele.getText());
						bean.setUrl(ele.getText());

					}
					if (name.equals("username")) {
						bean.setUsername(ele.getText());

					}
					if (name.equals("password")) {
						bean.setPassword(ele.getText());
					}

					if (name.equals("minConnections")) {
						bean.setMinConnections(Integer.parseInt(ele.getText()));
					}

					if (name.equals("maxConnections")) {
						bean.setMaxConnections(Integer.parseInt(ele.getText()));
					}

					if (name.equals("connTimeOut")) {
						bean.setConnTimeOut(Long.parseLong(ele.getText()));
					}

					if (name.equals("maxActiveConnections")) {
						bean.setMaxActiveConnections(Integer.parseInt(ele.getText()));
					}
				}
				beans.add(bean);
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
