package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class PrintBarReportFrame extends JFrame implements Printable {
    private PageFormat pf;
    private PreviewCanvas canvas;
    private boolean isPreview = false; // 是否可以列印
    private boolean previewFlag = false; // 是否已經進行預覽
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintBarReportFrame frame = new PrintBarReportFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public PrintBarReportFrame() {
        super();
        
        CategoryDataset dataset = createDataset();// 建立資料集對像
        JFreeChart chart = createChart(dataset);// 建立圖表對像
        String path = System.getProperty("user.dir")+"/src/chartImg/chart.jpg";// 圖表的儲存位置
        try {
            ChartUtilities.writeChartAsJPEG(new FileOutputStream(path), chart, 450, 360);// 將圖表儲存為圖片
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        addWindowListener(new WindowAdapter() {
            public void windowActivated(final WindowEvent e) {
                if (previewFlag) {
                    isPreview = true;// 設定為可以列印
                    canvas.repaint();// 呼叫paint()方法
                }
            }
        });
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// 窗體大小改變時執行
                if (previewFlag) {
                    isPreview = true;// 設定為可以列印
                    canvas.repaint();// 呼叫paint()方法
                }
            }
        });
        getContentPane().setBackground(new Color(232, 162, 255));
        setTitle("列印帶柱形圖表的報表");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        //pf.setOrientation(PageFormat.LANDSCAPE);// 列印起始點,也就是列印方向
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(633, 931));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 列印預覽
                if (QueryResultSet.gainRecord() != null) {
                    isPreview = true;// 表示可以列印
                    previewFlag = true;// 設定為已經列印預覽
                    PrinterJob job = PrinterJob.getPrinterJob();// 獲得列印對像
                    pf = job.pageDialog(pf);// 顯示修改PageFormat實例的交談視窗
                    canvas.repaint();// 呼叫paint()方法
                } 
            }
        });
        previewButton.setText("預覽報表");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!previewFlag){
                    return;
                }
                PrinterJob job = PrinterJob.getPrinterJob(); // 獲得PrinterJob對象的實例
                if (!job.printDialog())// 開啟列印交談視窗
                    return;
                // 設定列印內容
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // 設定可以列印
                        if (pageIndex < 1) {
                            printPicture(graphics, pageFormat, pageIndex); // 繪製列印內容
                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                    
                });
                job.setJobName("列印報表");
                try {
                    job.print();// 呼叫print()方法，實現列印
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        printButton.setText("列印報表");
        panel.add(printButton);
    }
    
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();// 建立資料集對像
        ResultSet rs = QueryResultSet.gainRecord();// 獲得查詢結果集
        try {
            while (rs.next()) {
                int value = rs.getInt(1);// 獲得年齡
                String sex = rs.getString(2);// 獲得性別
                String address = rs.getString(3);// 獲得地址
                dataset.addValue(value, sex, address);// 在資料集中增加資料資訊
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;// 傳回資料集對像
    }
    
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("\n\n統計來自各城市男女員工的平均年齡", // 圖表的標題純文字
                "所在城市       ", // x軸上的標籤文字
                "平均年齡", // y軸上的標籤文字
                dataset, // 資料集
                PlotOrientation.VERTICAL, // 垂直方向
                true, // 顯示圖例
                true, // 顯示說明文字
                false // 不產生鏈接
                );
        chart.setBackgroundPaint(Color.white);// 設定背景顏色
        CategoryPlot plot = (CategoryPlot) chart.getPlot();// 獲得圖表的CategoryPlot對像
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();// 獲得y軸的實例
        rangeAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// 設定y軸顯示值的字體
        rangeAxis.setLabelFont(new Font("黑體", Font.PLAIN, 12));// 設定y軸上標籤文字的字體
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());// 設定y軸顯示標準的整數單元
        BarRenderer renderer = (BarRenderer) plot.getRenderer();// 獲得柱形的描述對像
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f,
                0.0f, new Color(0, 0, 64));// 建立漸層色對像
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f,
                0.0f, new Color(0, 64, 0));// 建立漸層色對像
        renderer.setSeriesPaint(0, gp0);// 指定柱的顏色
        renderer.setSeriesPaint(1, gp1);// 指定柱的顏色
        CategoryAxis domainAxis = plot.getDomainAxis();// 獲得x軸的實例
        domainAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// 設定x軸顯示資訊的字體
        domainAxis.setLabelFont(new Font("細明體", Font.PLAIN, 12));// 設定x軸上標籤文字的字體
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions
                .createUpRotationLabelPositions(Math.PI / 6.0));// 指定x軸上顯示資訊的位置
        TextTitle textTitle = chart.getTitle();// 獲得標題對像
        textTitle.setFont(new Font("黑體", Font.PLAIN, 20));// 設定標題的字體
        chart.getLegend().setItemFont(new Font("細明體", Font.PLAIN, 12));// 設定圖例純文字的字體
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);// 設定圖例的對齊方式
        return chart;
    }
    
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex);// 繪製列印內容
        return Printable.PAGE_EXISTS;// 傳回PAGE_EXISTS
    }
    
    // 畫布
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.translate(10, 10);// 平移繪圖上下文
                int x = (int) (pf.getImageableX() - 1);// 獲得可列印區域的x座標偏左，用於繪製虛線
                int y = (int) (pf.getImageableY() - 1);// 獲得可列印區域的y座標偏上，用於繪製虛線
                int width = (int) (pf.getImageableWidth() + 1);// 獲得可列印區域的寬度偏右，用於繪製虛線
                int height = (int) (pf.getImageableHeight() + 1);// 獲得可列印區域的高度偏下，用於繪製虛線
                int mw = (int) pf.getWidth();// 獲得列印頁的寬度
                int mh = (int) pf.getHeight();// 獲得列印頁的高度
                g2.drawRect(0, 0, mw, mh);// 繪製實線外框
                g2.setColor(new Color(255, 253, 234));// 設定前景色
                g2.fillRect(1, 1, mw - 1, mh - 1);// 繪製填充區域
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// 指定虛線模式
                g2.setColor(Color.BLACK);// 設定前景色
                g2.drawRect(x, y, width, height);// 繪製虛線內框
                g2.setColor(Color.WHITE);// 設定前景色
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);// 繪製填充區域
                PrintBarReportFrame.this.print(g, pf, 0);// 呼叫print()方法
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 繪製列印內容
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX();// 獲得可列印區域座標的X位置
        int y = (int) pageFormat.getImageableY();// 獲得可列印區域座標的Y位置
        int w = (int) pageFormat.getImageableWidth();// 獲得可列印區域的寬度
        Graphics2D g2 = (Graphics2D) graphics;// 轉為Graphics2D對像
        ResultSet rs = QueryResultSet.gainReport();// 獲得查詢結果集
        if (rs != null && isPreview) {
            g2.setColor(Color.BLUE);// 設定為藍色
            g2.setFont(new Font("華文行楷",Font.BOLD,40));// 設定字體
            g2.drawString("員工基本資訊報表", x + 60, y + 50);// 繪製報表標題
            g2.setColor(Color.BLACK);// 設定為黑色
            g2.setFont(new Font("細明體",Font.PLAIN,12));// 設定字體
            g2.setStroke(new BasicStroke(1f));// 指定實線模式
            try {
                y = y + 80;// 調整列印位置的y值
                int y1 = y;// 儲存調整後列印位置的y值
                /**************繪製報表的標題*******************/
                g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// 繪製水平直線
                g2.drawString("編號", x + 20, y);// 繪製報表內容
                g2.drawString("姓名", x + 60, y);// 繪製報表內容
                g2.drawString("性別", x + 110, y);// 繪製報表內容
                g2.drawString("年齡", x + 150, y);// 繪製報表內容
                g2.drawString("聯繫地址", x + 190, y);// 繪製報表內容
                g2.drawString("郵政寫程式", x + 290, y);// 繪製報表內容
                g2.drawString("電話號碼", x + 360, y);// 繪製報表內容
                g2.drawLine(x + 10, y1 - 20, x + 10, y + 10);// 繪製垂直直線
                g2.drawLine(x + 50, y1 - 20, x + 50, y + 10);// 繪製垂直直線
                g2.drawLine(x + 100, y1 - 20, x + 100, y + 10);// 繪製垂直直線
                g2.drawLine(x + 140, y1 - 20, x + 140, y + 10);// 繪製垂直直線
                g2.drawLine(x + 180, y1 - 20, x + 180, y + 10);// 繪製垂直直線
                g2.drawLine(x + 280, y1 - 20, x + 280, y + 10);// 繪製垂直直線
                g2.drawLine(x + 350, y1 - 20, x + 350, y + 10);// 繪製垂直直線
                g2.drawLine(x + w - 10, y1 - 20, x + w - 10, y + 10);// 繪製垂直直線
                /************************************************/
                while (rs.next()) {
                    y = y + 30;// 調整列印位置的y值
                    g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// 繪製水平直線
                    g2.drawString(String.valueOf(rs.getInt(1)), x + 30, y);// 繪製報表內容
                    g2.drawString(rs.getString(2), x + 60, y);// 繪製報表內容
                    g2.drawString(rs.getString(3), x + 110, y);// 繪製報表內容
                    g2.drawString(String.valueOf(rs.getInt(4)), x + 150, y);// 繪製報表內容
                    g2.drawString(rs.getString(5), x + 190, y);// 繪製報表內容
                    g2.drawString(rs.getString(6), x + 290, y);// 繪製報表內容
                    g2.drawString(rs.getString(7), x + 360, y);// 繪製報表內容
                }
                g2.drawLine(x + 10, y1 + 10, x + 10, y + 10);// 繪製垂直直線
                g2.drawLine(x + 50, y1 + 10, x + 50, y + 10);// 繪製垂直直線
                g2.drawLine(x + 100, y1 + 10, x + 100, y + 10);// 繪製垂直直線
                g2.drawLine(x + 140, y1 + 10, x + 140, y + 10);// 繪製垂直直線
                g2.drawLine(x + 180, y1 + 10, x + 180, y + 10);// 繪製垂直直線
                g2.drawLine(x + 280, y1 + 10, x + 280, y + 10);// 繪製垂直直線
                g2.drawLine(x + 350, y1 + 10, x + 350, y + 10);// 繪製垂直直線
                g2.drawLine(x + w - 10, y1 + 10, x + w - 10, y + 10);// 繪製垂直直線
                g2.drawLine(x + 10, y + 30 - 20, x + w - 10, y + 30 - 20);// 繪製水平直線
                /*********************繪製圖表圖片*****************************/
                URL imgUrl = PrintBarReportFrame.class.getResource("/chartImg/chart.jpg");// 獲得圖片資源的路徑
                Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖表圖片的圖形對像
                g2.drawImage(img, x+10, y+20,420,300, this);// 在列印頁繪製圖表圖片
                
                rs.close();// 關閉結果集
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        isPreview = true; // 設定不可以列印
        previewFlag = true;
    }
}
