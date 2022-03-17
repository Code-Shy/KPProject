package pers.gqh.kp.utils;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * @Package pers.gqh.kp.utils
 * @Description
 * @Author Simple OR XXX
 * @Date 2022/3/17
 **/
public class PicUtils {

    public static void main(String[] args)   {

        scatterPlotPaint();
    }



    public static void scatterPlotPaint() {
        double[][] a = {{1, 2, 3, 5, 6, 7, 8},
                        {4, 5, 6, 7, 8, 9, 10}};

        DefaultXYDataset defaultXYDataset = new DefaultXYDataset();
        defaultXYDataset.addSeries("Data", a);
        JFreeChart chart = ChartFactory.createScatterPlot("", "Weight", "Value", defaultXYDataset,
                PlotOrientation.VERTICAL, true, true, true);//设置表头，x轴，y轴，name表示问题的类型
        ChartFrame frame = new ChartFrame("KP数据散点图", chart, true);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setBackgroundPaint(Color.white);//设置背景面板颜色
        ValueAxis valueAxis = xyplot.getDomainAxis();
        valueAxis.setAxisLineStroke(new BasicStroke(1.0f));//设置坐标轴粗细
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

}
