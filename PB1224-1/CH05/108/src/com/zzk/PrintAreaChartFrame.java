package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class PrintAreaChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 獲得列印對像
    ChartPanel chartPanel = null;
    private Robot robot = null; // 宣告Robot對像
    private BufferedImage buffImage = null; // 宣告緩衝圖形對像
    
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintAreaChartFrame frame = new PrintAreaChartFrame();
                    RefineryUtilities.centerFrameOnScreen(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintAreaChartFrame() {
        super();
        setTitle("列印區域圖表");
        setBounds(0, 0, 820, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        XYDataset xydataset = createDataset();              // 建立資料集對像
        JFreeChart chart = createChart(xydataset);          // 建立JFreeChart對像
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        getContentPane().add(chartPanel);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        final JButton button = new JButton();
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrinterJob job = PrinterJob.getPrinterJob(); // 獲得列印對像
                    if (!job.printDialog()) // 開啟列印交談視窗
                        return;// 單擊列印交談視窗的取消按鈕或關閉列印交談視窗結束程式的執行
                    job.setPrintable(new Printable() {
                        // 實現print()方法，繪製列印內容
                        public int print(Graphics graphics, PageFormat pf,
                                int pageIndex) throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // 獲得圖形上下文對像
                            int x = (int) (PrintAreaChartFrame.this.getBounds()
                                    .getX()) + 8;// 背景面板在屏幕上的X座標
                            int y = (int) (PrintAreaChartFrame.this.getBounds()
                                    .getY()) + 30;// 背景面板在屏幕上的Y座標
                            int w = (int) chartPanel.getBounds().getWidth();// 圖表的寬度
                            int h = (int) chartPanel.getBounds().getHeight();// 圖表的高度
                            Rectangle rect = new Rectangle(x, y, w, h);// 建立Rectangle對像
                            buffImage = robot.createScreenCapture(rect);// 獲得緩衝圖形對像
                            int imgWidth = buffImage.getWidth();// 圖形的寬度
                            int imgHeight = buffImage.getHeight();// 圖形的高度
                            float wh = imgWidth / imgHeight;// 圖形寬高比
                            int printX = (int) pf.getImageableX();// 獲得可列印區域的x座標
                            int printY = (int) pf.getImageableY();// 獲得可列印區域的y座標
                            int width = (int) pf.getImageableWidth();// 獲得可列印區域的寬度
                            int height = (int) pf.getImageableHeight();// 獲得可列印區域的高度
                            if (imgWidth > width) { // 如果寬大於可列印區域
                                imgWidth = width;
                                imgHeight = (int) (imgHeight * wh);
                            }
                            if (imgHeight > height) { // 如果高大於可列印區域
                                imgHeight = height;
                                imgWidth = (int) (imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth,
                                    imgHeight, PrintAreaChartFrame.this);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("列印區域圖表"); // 設定列印工作的名稱
                    job.print(); // 呼叫print()方法開始列印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("打    印");
        button.setBounds(28, 56, 86, 28);
        final JButton button_1 = new JButton();
        panel.add(button_1);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        button_1.setBounds(151, 56, 86, 28);
    }
    
    
    private XYDataset createDataset() {
        long value = 0;
        Day day = new Day(1, 1, 2009);// 獲得2009年1月1日的Day對像
        long seed = System.currentTimeMillis();// 獲得目前時間的毫秒數
        Random ran = new Random(seed);// 建立隨機數種子為seed的Random對像
        TimeSeries soft = new TimeSeries("明日科技圖書");// 建立時間序列
        for (int i = 0; i < 365; i++) {                 //增加一年365天的資料
            value += ran.nextInt() / 10000;// 隨機獲得資料
            soft.add(day, value);// 增加資料
            day = (Day) day.next();// 獲得下一個日期的Day對像
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection(soft);// 建立資料集對像
        return dataset;
    }

    @SuppressWarnings("deprecation")
    private JFreeChart createChart(XYDataset xydataset) {
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("隸書", Font.BOLD, 24));//設定標題字體
        standardChartTheme.setRegularFont(new Font("細明體", Font.BOLD, 14));//設定圖例的字體
        standardChartTheme.setLargeFont(new Font("細明體", Font.BOLD, 18));//設定軸向的字體
        ChartFactory.setChartTheme(standardChartTheme);//設定主題樣式
        JFreeChart jfreechart = ChartFactory.createXYAreaChart(
                "明日科技圖書，年度使用者滿意度統計", // 圖表標題
                "年統計月份", // X軸標題
                "使用者滿意度", // Y軸標題
                xydataset, // 製圖的資料集
                PlotOrientation.VERTICAL, // 定義區域圖的方向為垂直
                false,      // 是否顯示圖例標誌
                true,       // 是否顯示提示資訊
                false);     // 是否支援超鏈接
        jfreechart.setBackgroundPaint(Color.PINK); // 設定背景
        XYPlot xyplot = (XYPlot) jfreechart.getPlot(); //獲得XYPlot對像
        xyplot.setDomainGridlinePaint(Color.GREEN);// 設定圖表網格線的顏色
        xyplot.setDomainGridlineStroke(new BasicStroke(1f));// 設定網格線的粗細
        XYPlot plot = jfreechart.getXYPlot();// 獲得圖表的繪製屬性
        // 建立指定樣式的日期格式對像
        DateFormat format = new SimpleDateFormat("MM月份");
        DateAxis domainAxis = new DateAxis("2009年統計月份           ");// 建立時間軸對像
        DateTickUnit dtu = new DateTickUnit(DateTickUnit.DAY, 29, format);
        domainAxis.setTickUnit(dtu);       // 設定橫軸上的時間刻度的顯示格式
        domainAxis.setLowerMargin(0.0); // 設定圖表空白
        domainAxis.setUpperMargin(0.0); // 設定圖表空白
        domainAxis.setTickLabelFont(new Font("黑體", Font.BOLD, 14)); // 設定軸標記字體
        domainAxis.setLabelFont(new Font("細明體", Font.ITALIC, 20));   // 設定橫軸字體
        plot.setDomainAxis(domainAxis); // 為繪圖屬性增加橫軸對像
        plot.setForegroundAlpha(0.5f);// 設定前景透明度為50%
        return jfreechart;
    }
    
    /////
}
