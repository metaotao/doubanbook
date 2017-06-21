package com.douban.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarChart extends JFrame {
	private static final long serialVersionUID = 1L;
	ChartPanel chartPane;

	public static void main(String[] args) {
		new BarChart();
	}

	public BarChart() {
		setLayout(new FlowLayout());
		setSize(1000, 700);
		init();
		setVisible(true);

		add(getChartPanel());
	}

	@SuppressWarnings("deprecation")
	public void init() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("Number Ranking", // 图表标题
				"Book Types", // 目录轴的显示标签
				"The Number of Books", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, false, // 是否生成工具
				false // 是否生成URL链接
		);
		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		
		BarRenderer3D renderer =new BarRenderer3D();

		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setItemLabelFont(new Font("黑体",Font.BOLD,14));//12号黑体加粗
        renderer.setItemLabelPaint(Color.black);//字体为黑色
        
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.BASELINE_CENTER));  
        renderer.setItemLabelsVisible(true);

		plot.setRenderer(renderer);
		
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setUpperMargin(0.1);
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		
		chartPane = new ChartPanel(chart, true);

//		File dir = new File("images\\");
//		if (!dir.exists()) {
//			dir.mkdir();
//		}
//		String fName = String.valueOf(System.currentTimeMillis()) + "bar.png";
//		File file = new File("images\\", fName);
//		try {
//			ChartUtilities.saveChartAsPNG(file, chart, 550, 400);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
	}

	private static CategoryDataset getDataSet() {
		String[] strs = { "文学", "流行", "文化", "生活", "经管", "科技" };
		int[] percent = new int[6];
		for (int i = 0; i < strs.length; i++) {

			int counter = PieDataAnalysis.getInstance().getBookCount(strs[i]);
			percent[i] = counter;

		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(percent[0], "文学", "文学");
		dataset.addValue(percent[1], "流行", "流行");
		dataset.addValue(percent[2], "文化", "文化");
		dataset.addValue(percent[3], "生活", "生活");
		dataset.addValue(percent[4], "经管", "经管");
		dataset.addValue(percent[5], "科技", "科技");

		System.out.println("文学:" + percent[0]);
		System.out.println("流行:" + percent[1]);
		System.out.println("文化:" + percent[2]);
		System.out.println("生活:" + percent[3]);
		System.out.println("经管:" + percent[4]);
		System.out.println("科技:" + percent[5]);
		return dataset;

	}

	public ChartPanel getChartPanel() {
		return chartPane;
	}
}
