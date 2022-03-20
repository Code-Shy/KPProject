package pers.gqh.kp.utils;


import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.*;
import pers.gqh.kp.entity.Data;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Package pers.gqh.kp.utils
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/17
 **/
public class PicUtils {
    /**
     * 画散点图
     *
     * @param data      数据
     * @param dataIndex 文件序号
     * @throws IOException
     */
    public static void scatterPlotPaint(Data data, int dataIndex) throws IOException {
        double[][] tempData = new double[2][data.getN()];
        //将Integer转换为int
        for (int i = 0; i < data.getN(); i++) {
            tempData[0][i] = Integer.parseInt(data.getW().get(i).toString());
        }
        for (int i = 0; i < data.getN(); i++) {
            tempData[1][i] = Integer.parseInt(data.getV().get(i).toString());
        }

        DefaultXYDataset defaultXYDataset = new DefaultXYDataset();
        //添加数据
        defaultXYDataset.addSeries(" ", tempData);
        //设置表头，x轴，y轴
        JFreeChart chart = ChartFactory.createScatterPlot("", "Weight", "Value", defaultXYDataset,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        //设置背景面板颜色
        xyplot.setBackgroundPaint(Color.white);
        ValueAxis valueAxis = xyplot.getDomainAxis();
        //设置坐标轴粗细
        valueAxis.setAxisLineStroke(new BasicStroke(1.0f));

        //输出PNG文件
        OutputStream os_png = new FileOutputStream("pic/beibao" + dataIndex + ".png");
        ChartUtilities.writeChartAsPNG(os_png, chart, 500, 500);
        System.out.println("文件已导出到KP/pic/beibao" + dataIndex + ".png");

        //以面板显示
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 400));

        //创建一个主窗口来显示面板
        JFrame frame = new JFrame("散点图");
        frame.setLocation(500, 400);
        frame.setSize(600, 500);

        //将主窗口的内容面板设置为图表面板
        frame.setContentPane(chartPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
