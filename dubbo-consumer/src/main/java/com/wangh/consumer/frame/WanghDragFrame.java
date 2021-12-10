// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space

package com.wangh.consumer.frame;

import com.wangh.consumer.application.ApplicationContextHelper;
import com.wangh.consumer.data.DataSource;
import com.wangh.consumer.service.LocalService;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;

/**
 * 曲线拖拽demo
 *
 * @Return: {@link null}
 * @Version: 1.0.0
 * @Author: wanghao@tsintergy.com
 * @Date: 2021/12/8 13:38
 */
public class WanghDragFrame extends ApplicationFrame implements ChartMouseListener, MouseListener,
    MouseMotionListener {

    private LocalService localService = (LocalService) ApplicationContextHelper.getBean("localService");

    private ChartPanel chartPanel;

    JFreeChart jfreechart;

    private XYItemEntity xyItemEntity;

    public WanghDragFrame(String s) {
        super(s);

        DataSource dataSource = new DataSource();
        DefaultTableXYDataset date = dataSource.getData();

        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
        //应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);

        jfreechart = ChartFactory
            .createXYLineChart("曲线拖拽demo", "横坐标", "负荷数据", date);
        chartPanel = new ChartPanel(jfreechart);
        chartPanel.setMouseZoomable(false);
        chartPanel.addChartMouseListener(this);
        chartPanel.addMouseListener(this);
        chartPanel.addMouseMotionListener(this);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }


    /**
     * 功能描述:<br>鼠标点击
     *
     * @Return:
     * @Version: 1.0.0
     * @Author: wanghao@tsintergy.com
     * @Date: 2021/12/8 13:38
     */
    @Override
    public void chartMouseClicked(ChartMouseEvent chartmouseevent) {
        //双击调用接口
        if (chartmouseevent.getTrigger().getClickCount() == 2) {
            System.out.println(this.localService.saySomething());
        }

        if (chartmouseevent.getEntity() != null) {
            if (chartmouseevent.getEntity() instanceof XYItemEntity) {
                xyItemEntity = (XYItemEntity) chartmouseevent.getEntity();
                int seriesIndex = xyItemEntity.getSeriesIndex();

                XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
                XYLineAndShapeRenderer xyLineAndShapeRenderer = (XYLineAndShapeRenderer) xyPlot.getRenderer();
                for (int index = 0; index < xyItemEntity.getDataset().getSeriesCount(); index++) {
                    xyLineAndShapeRenderer.setSeriesShapesVisible(index, false);
                }
                xyLineAndShapeRenderer.setSeriesShapesVisible(seriesIndex, true); // 数据点可见

                System.out.println("Mouse clicked: " + xyItemEntity.toString());
            }
        } else {
            System.out.println("Mouse clicked: null entity.");
        }
    }


    /**
     * 功能描述:<br> 鼠标悬浮移动
     *
     * @param chartmouseevent 监听的鼠标悬浮操作
     * @Version: 1.0.0
     * @Author: wanghao@tsintergy.com
     * @Date: 2021/12/8 13:39
     */
    @Override
    public void chartMouseMoved(ChartMouseEvent chartmouseevent) {
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * 功能描述:<br> 鼠标点击释放监听
     *
     * @param e 鼠标事件
     * @Version: 1.0.0
     * @Author: wanghao@tsintergy.com
     * @Date: 2021/12/8 13:39
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //鼠标释放后要释放对象
        xyItemEntity = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }


    /**
     * 功能描述:<br> 鼠标拖拽
     *
     * @Version: 1.0.0
     * @Author: wanghao@tsintergy.com
     * @Date: 2021/12/8 13:40
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (xyItemEntity == null) {
            return;
        }
        //多条曲线时，操作的曲线序号 从0开始
        int seriesIndex = xyItemEntity.getSeriesIndex();
        //横坐标序号
        int itemIndex = xyItemEntity.getItem();
        //从0开始
        System.out.println(itemIndex);
        //获取鼠标x坐标
        int xPos = e.getX();
        //获取鼠标y坐标
        int yPos = e.getY();

        Point2D point2D = chartPanel.translateScreenToJava2D(new Point(xPos, yPos));

        XYPlot xyPlot = (XYPlot) jfreechart.getPlot();
        ChartRenderingInfo chartRenderingInfo = chartPanel.getChartRenderingInfo();
        //获取操作区域
        Rectangle2D rectangle2D = chartRenderingInfo.getPlotInfo().getDataArea();
        //横轴范围对象
        ValueAxis domainAxis = xyPlot.getDomainAxis();
        //纵轴范围对象
        ValueAxis valueAxis = xyPlot.getRangeAxis();
        //横坐标边界
        RectangleEdge doMainEdge = xyPlot.getDomainAxisEdge();
        //纵坐标边界
        RectangleEdge rectangleEdge = xyPlot.getRangeAxisEdge();

        //鼠标横坐标相对于图表计算的值
        double domainValue = domainAxis.java2DToValue(point2D.getX(), rectangle2D, doMainEdge);
        //鼠标纵坐标相对于图表计算的值
        double value = valueAxis.java2DToValue(point2D.getY(), rectangle2D, rectangleEdge);


        System.out.println("横坐标：" + point2D.getX() + "纵坐标：" + point2D.getY());
        System.out.println("横坐标换算后：" + domainValue + "纵坐标换算后：" + value);

        //坐标表小数四射入成整数以匹配横坐标
        int anInt = WanghDragFrame.getInt(domainValue);
        if (anInt == 0) {
            anInt = 0;
        } else if (anInt > xyItemEntity.getDataset().getItemCount(seriesIndex)) {
            anInt = xyItemEntity.getDataset().getItemCount(seriesIndex) - 1;
        } else {
            anInt = anInt - 1;
        }

        DefaultTableXYDataset tsc = (DefaultTableXYDataset) xyItemEntity.getDataset();
        XYSeries series = tsc.getSeries(seriesIndex);
        //按照横坐标序号更新点
        series.updateByIndex(anInt, new Double(value));
    }

    public static int getInt(double number) {
        BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
