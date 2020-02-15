package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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
    
    private JTextField watermarkText;
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
    private BufferedImage src;
    private boolean isPreview = false; // 是否可以列印
    private String watermarkWord = "明日科技"; // 水印文字
    
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
        getContentPane().setBackground(new Color(232, 162, 255));
        setTitle("自動為列印內容增加水印");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 770));
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
                if (imgFile != null) {
                    isPreview = true; // 表示可以列印
                    watermarkWord = watermarkText.getText(); // 獲得輸入的水印文字
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
                    return;
                // 設定列印內容
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // 設定可以列印
                        if (pageIndex < 1) {
                            watermarkWord = watermarkText.getText(); // 獲得輸入的文印文字
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
        panel_1.setOpaque(false);
        panel_1.setLayout(null);
        panel_1.setPreferredSize(new Dimension(0, 70));
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("請選擇要列印的圖片：");
        label.setBounds(34, 15, 130, 18);
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(500, 24));
        filePath.setBounds(165, 10, 500, 24);
        panel_1.add(filePath);
        
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
        selectButton.setBounds(680, 10, 86, 28);
        panel_1.add(selectButton);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("請輸入水印文字：");
        label_1.setBounds(60, 40, 104, 18);
        panel_1.add(label_1);
        
        watermarkText = new JTextField();
        watermarkText.setPreferredSize(new Dimension(500, 24));
        watermarkText.setBounds(165, 40, 500, 24);
        panel_1.add(watermarkText);
        // 選擇檔案按鈕
        
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        // TODO Auto-generated method stub
        
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
                g2.setColor(new Color(255, 253, 234)); // 設定前景色
                g2.fillRect(1, 1, mw - 1, mh - 1);// 繪製填充區域
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// 指定虛線模式
                g2.setColor(Color.BLACK); // 設定前景色
                g2.drawRect(x, y, width, height);// 繪製虛線內框
                g2.setColor(Color.WHITE); // 設定前景色
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);// 繪製填充區域
                MainFrame.this.print(g, pf, 0);// 呼叫print()方法
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
            int wordSize = (int) imgWidth / 10;
            int mw = (int) pf.getWidth() - x * 2;
            int mh = (int) pf.getHeight() - y * 2;
            if (imgWidth > mw) { // 如果寬大於可列印區域
                imgWidth = mw;
            }
            if (imgHeight > mh) { // 如果高大於可列印區域
                imgHeight = mh;
            }
            /*********************** 增加水印文字 ****************************/
            Graphics2D g = src.createGraphics(); // 獲得圖片繪圖上下文
            Font font = new Font("黑體", Font.BOLD, wordSize); // 建立字體對像
            g.setFont(font); // 設定繪圖字體
            g.setPaint(Color.RED); // 設定繪圖顏色
            // 獲得文字佔用的像素區域
            Rectangle2D rec = font.getStringBounds(watermarkWord, g
                    .getFontRenderContext());
            double pw = rec.getWidth(); // 獲得水印文字佔用的像素寬度
            double ph = rec.getHeight(); // 獲得水印文字佔用的像素高度
            g.rotate(Math.toRadians(30), wordSize + pw / 2, wordSize + ph / 2); // 轉換角度
            g.setComposite(AlphaComposite.SrcOver.derive(0.4f));// 設定水印透明合成規則
            g.drawString(watermarkWord, wordSize * 2, wordSize * 2 + (int) ph); // 繪製文字水印
            /***************************************************************/
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // 繪製圖形
        }
        isPreview = false; // 設定不可以列印
    }
}
