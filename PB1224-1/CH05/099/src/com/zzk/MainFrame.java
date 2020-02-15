package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private PageFormat pf;
    private PreviewCanvas canvas;
    private File imgFile = null;
    private Image src;
    private boolean isPreview = false; // 是否可以列印
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// 窗體大小改變時執行
                // 列印預覽
                if (imgFile != null) {
                    isPreview = true; // 表示可以列印
                    canvas.repaint();// 呼叫paint()方法
                }
            }
        });
        setTitle("倒序列印");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 750));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 列印預覽
                if (imgFile != null) {
                    isPreview = true; // 表示可以列印
                    PrinterJob job = PrinterJob.getPrinterJob();// 獲得列印對像
                    pf = job.pageDialog(pf);// 顯示修改PageFormat實例的交談視窗
                    canvas.repaint();// 呼叫paint()方法
                } else {
                    JOptionPane.showMessageDialog(null, "請先選擇要列印的圖片！");
                }
            }
        });
        previewButton.setText("列印預覽");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // 獲得PrinterJob對象的實例
                if (!job.printDialog())// 開啟列印交談視窗
                    return;// 單擊列印交談視窗的取消按鈕或關閉列印交談視窗結束程式的執行
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
                job.setJobName("列印圖形");
                try {
                    job.print();// 呼叫print()方法，實現列印
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("開始列印");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("請選擇要列印的圖片：");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // 選擇檔案按鈕
        final JButton selectButton = new JButton();
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// 建立檔案選擇器
                FileFilter filter = new FileNameExtensionFilter(
                        "圖形檔案（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// 建立過濾器
                fileChooser.setFileFilter(filter);// 設定過濾器
                int i = fileChooser.showOpenDialog(getContentPane());// 顯示開啟交談視窗
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // 獲得勾選的圖片對像
                    filePath.setText(imgFile.getAbsolutePath()); // 顯示圖片路徑
                }
            }
        });
        selectButton.setText("選擇檔案");
        panel_1.add(selectButton);
        
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex); // 繪製列印內容
        return Printable.PAGE_EXISTS; // 傳回PAGE_EXISTS
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
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// 指定虛線模式
                g2.drawRect(x, y, width, height);// 繪製虛線內框
                MainFrame.this.print(g2, pf, 0);// 呼叫print()方法
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 繪製倒序的列印內容
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX(); // 獲得可列印區域座標的X位置
        int y = (int) pageFormat.getImageableY(); // 獲得可列印區域座標的Y位置
        Graphics2D g2 = (Graphics2D) graphics;
        if (imgFile != null && isPreview) {
            try {
                src = ImageIO.read(imgFile);// 建構BufferedImage對像
            } catch (IOException e) {
                e.printStackTrace();
            }
            double imgWidth = src.getWidth(this);// 獲得圖形的寬度
            double imgHeight = src.getHeight(this);// 獲得圖形的高度
            double imgWidthS = imgWidth;// 儲存圖形的寬度
            double imgHeightS = imgHeight;// 儲存圖形的高度
            int mw = (int) pf.getWidth();// 獲得列印頁的寬度
            int mh = (int) pf.getHeight();// 獲得列印頁的高度
            if (imgWidth > mw) { // 如果寬大於可列印區域
                imgWidth = mw - x;// 設定新寬度值
            }
            if (imgHeight > mh) { // 如果高大於可列印區域
                imgHeight = mh - y;// 設定新的高度值
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, x,
                    (int) imgHeightS, (int) imgWidthS, y, this);// 繪製倒序圖形
        }
        isPreview = false; // 設定不可以列印
    }
}
