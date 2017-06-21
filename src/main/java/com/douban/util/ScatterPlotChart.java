package com.douban.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

import com.douban.util.bean.RatingDisBean;

public class ScatterPlotChart extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ScatterPlotChart();
	}

	public ScatterPlotChart() {
		List<RatingDisBean> list = ScatterDataAnalysis.getInstance().selectBookInfo();
		XYDataset xydataset = createxydataset("Rating Distribution", list);
		JFreeChart chart = createChart(xydataset, "Rating People", "Rating");
		chart.getTitle().setFont(new Font("微软雅黑", Font.PLAIN, 25));
		ChartFrame frame = new ChartFrame("distribution", chart);
		frame.setSize(new Dimension(700,500));
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
	}

	public static XYDataset createxydataset(String des, List<RatingDisBean> list) {
		DefaultXYDataset xydataset = new DefaultXYDataset();
		int size = list.size();
		System.out.println(size);
		double[][] datas = new double[2][size];
		for (int i = 0; i < size; i++) {
			RatingDisBean bean = list.get(i);
			double x = Double.parseDouble(bean.getDatingNum());
			double y = Double.parseDouble(bean.getDatingScore());
			
			datas[0][i] = x;
			datas[1][i] = y;
			System.out.println(datas[0][i]+"  "+datas[1][i]);
		}
		System.out.println(datas[0].length);
		xydataset.addSeries(des, datas);
		return xydataset;

	}

	public static JFreeChart createChart(XYDataset xydataset, String shou, String shu) {
		JFreeChart jfreechart = ChartFactory.createScatterPlot("Rating and Rating People Distribution", shou, shu,
				xydataset, PlotOrientation.VERTICAL, true, false, false);
		jfreechart.setBackgroundPaint(Color.white);
		jfreechart.setBorderPaint(Color.GREEN);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setNoDataMessage("no data");
		xyplot.setNoDataMessageFont(new Font("微软雅黑", Font.BOLD, 14));
		xyplot.setNoDataMessagePaint(Color.blue);
		xyplot.setBackgroundPaint(Color.lightGray);

		TextTitle textTitle = jfreechart.getTitle();
		textTitle.setFont(new Font("宋体", Font.BOLD, 20));
		LegendTitle legend = jfreechart.getLegend();
		if (legend != null) {
			legend.setItemFont(new Font("宋体", Font.BOLD, 20));
		}

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
		renderer.setBaseShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setSeriesOutlinePaint(0, Color.WHITE);  
		renderer.setUseOutlinePaint(true);  
		renderer.setSeriesOutlineStroke(1, new BasicStroke(0.5F));
		xyplot.setRenderer(renderer);

		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setTickMarkInsideLength(3.0F);  
	    numberaxis.setTickMarkOutsideLength(0.0F); 
		numberaxis.setUpperBound(280000);
		numberaxis.setLowerBound(50000);
		
		numberaxis.setAxisLineStroke(new BasicStroke(1.5f));  

		NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
		numberaxis1.setUpperBound(10.0);
		numberaxis1.setLowerBound(5.0);
		
		NumberTickUnit ntu1 = new NumberTickUnit(0.5);
		numberaxis1.setTickUnit(ntu1);
		
		return jfreechart;
	}
}
