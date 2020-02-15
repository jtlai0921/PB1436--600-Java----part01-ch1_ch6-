package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RefineryUtilities;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class PrintPieChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 獲得列印對像
    ChartPanel chartPanel = null;
    private Robot robot = null; // 宣告Robot對像
    private BufferedImage buffImage = null; // 宣告緩衝圖形對像
    
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintPieChartFrame frame = new PrintPieChartFrame();
                    RefineryUtilities.centerFrameOnScreen(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintPieChartFrame() {
        super();
        setTitle("列印餅形圖表");
        setBounds(0, 0, 500, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
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
                            int x = (int) (PrintPieChartFrame.this.getBounds()
                                    .getX()) + 8;// 背景面板在屏幕上的X座標
                            int y = (int) (PrintPieChartFrame.this.getBounds()
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
                                    imgHeight, PrintPieChartFrame.this);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("列印餅形圖表"); // 設定列印工作的名稱
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
        
        //
    }
    
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();// 建立資料集對像
        ResultSet rs = QueryResultSet.gainRecord();// 獲得查詢結果集
        try {
            while (rs.next()) {
                int value = rs.getInt(1);// 獲得平均年齡
                String sex = rs.getString(2);// 獲得性別
                String address = rs.getString(3);// 獲得地址
                dataset.setValue(address + "," + sex, value);// 設定資料集的值
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("\n\n統計來自各城市男女員工的平均年齡", // 圖表的標題純文字
                dataset, // 資料集
                true, // 顯示圖例
                true, // 顯示說明文字
                false // 不產生鏈接
                );
        chart.setBackgroundPaint(Color.white);// 設定背景顏色
        chart.getTitle().setFont(new Font("黑體", Font.PLAIN, 20));// 設定標題字體
        chart.getLegend().setItemFont(new Font("黑體", Font.PLAIN, 12));// 設定圖例純文字的字體
        PiePlot plot = (PiePlot) chart.getPlot();// 獲得圖表的PiePlot對像
        plot.setLabelFont(new Font("黑體", Font.PLAIN, 12));// 設定餅狀圖標籤的字體
        return chart;
    }
}
