package com.douban.util;

import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart extends JFrame {

	private static final long serialVersionUID = 1L;
	ChartPanel panel;

	public static void main(String[] args) {
		new PieChart();
	}

	public PieChart() {

		setLayout(new FlowLayout());

		setSize(1000, 700);
		init();
		setVisible(true);

		add(getPieChartPanel());
	}

	/**
	 * 创建饼状图的步骤如下： 1、创建一个饼状的实例，注意传参的格式，还有需要注意的是此时的数据集应该是defaultPieDataset，
	 * 而不是CategoryDataset格式 2、获得饼状图的所在区域 3、设置两个格式化的数据格式，为后面的床架饼状图的实例做基础
	 * 4、细节方面是对无数据、零值、负值等情况的处理 5、最后就是设置在出现汉字的地方进行字体内容的设置了（同样的，这是为了防止出现乱码的状况）
	 */
	public void init() {
		DefaultPieDataset dataset = getDataset();

		JFreeChart chart = ChartFactory.createPieChart3D("The proportion of the book", dataset, true, false, false);

		PiePlot piePlot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");
		NumberFormat nf = NumberFormat.getInstance();

		// 获得StandardPieSectionLabelGenerator对象,生成的格式，{0}表示section名，
		StandardPieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0} {2}",
				// {1}表示section的值，{2}表示百分比。可以自定义
				nf, df);
		piePlot.setLabelGenerator(generator);// 设置百分比
		piePlot.setLabelFont(new Font("黑体", Font.ITALIC, 20));

		// 当饼状图内没有有数据时，作如下数据中设置
		piePlot.setNoDataMessage("此时并没有任何数据可用");
		piePlot.setCircular(false);
		piePlot.setLabelGap(0.02D);

		piePlot.setIgnoreNullValues(true);// 设置不显示空位
		piePlot.setIgnoreZeroValues(true);// 设置不显示负值或零值

		panel = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("微软雅黑", Font.PLAIN, 25));
		chart.getLegend().setItemFont(new Font("微软雅黑", Font.PLAIN, 16));

		File dir = new File("images\\");
		if (!dir.exists()) {
			dir.mkdir();
		}
		String fName = String.valueOf(System.currentTimeMillis()) + "pie.png";
		File file = new File("images\\", fName);
		try {
			ChartUtilities.saveChartAsPNG(file, chart, 550, 400);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * 需要注意的是在向数据集中添加数据的时候调用的是dataset.setvalue()方法，而不是柱状图中的addValue()方法
	 * 这一点应该尤其注意一下，以免在使用的时候出现错误
	 * 
	 * @return
	 */
	private DefaultPieDataset getDataset() {
		String[] strs = { "文学", "流行", "文化", "生活", "经管", "科技" };
		int[] percent = new int[6];
		for (int i = 0; i < strs.length; i++) {

			int counter = PieDataAnalysis.getInstance().getBookCount(strs[i]);
			percent[i] = counter;

		}

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("文学", percent[0]);
		dataset.setValue("流行", percent[1]);
		dataset.setValue("文化", percent[2]);
		dataset.setValue("生活", percent[3]);
		dataset.setValue("经管", percent[4]);
		dataset.setValue("科技", percent[5]);

		System.out.println("文学:" + percent[0]);
		System.out.println("流行:" + percent[1]);
		System.out.println("文化:" + percent[2]);
		System.out.println("生活:" + percent[3]);
		System.out.println("经管:" + percent[4]);
		System.out.println("科技:" + percent[5]);

		return dataset;
	}

	public ChartPanel getPieChartPanel() {
		return panel;
	}
}
