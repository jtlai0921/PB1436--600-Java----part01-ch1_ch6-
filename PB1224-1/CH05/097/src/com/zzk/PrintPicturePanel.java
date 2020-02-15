package com.zzk;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author 張振坤
 *
 */
@SuppressWarnings("serial")
public class PrintPicturePanel extends JPanel implements Printable {
    private JButton printButton;
    private JButton previewButton;
    private JPanel controlPanel;
    private File imgFile = null;
    private BufferedImage src;
    private PrinterJob job;
    private PageFormat pf;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("列印圖片");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 280);
        PrintPicturePanel panel = new PrintPicturePanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    
    public PrintPicturePanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(getControlPanel(), BorderLayout.SOUTH);
        pf = new PageFormat();// 建立PageFormat對像
        pf.setOrientation(PageFormat.LANDSCAPE);// 設定列印方向
        job = PrinterJob.getPrinterJob();// 獲得列印對像
    }
    
    private void drawPage(Graphics2D g2) {
        int imgW = 0;
        int imgH = 0;
        if (src != null) {
            imgW = src.getWidth();
            imgH = src.getHeight();
            if (imgW > getWidth()) {
                imgW = getWidth();
            }
            if (imgH > getHeight()) {
                imgH = getHeight();
            }
        }
        g2.drawImage(src, 0, 0, imgW, imgH, this);// 繪製圖形
    }
    
    protected JButton getPreviewButton() {
        if (previewButton == null) {
            previewButton = new JButton();
            previewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();// 建立檔案選擇器
                    FileFilter filter = new FileNameExtensionFilter(
                            "圖形檔案（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// 建立過濾器
                    fileChooser.setFileFilter(filter);// 設定過濾器
                    int i = fileChooser.showOpenDialog(null);// 顯示開啟交談視窗
                    if (i == JFileChooser.APPROVE_OPTION) {
                        imgFile = fileChooser.getSelectedFile(); // 獲得勾選圖片的File對像
                    }
                    if (imgFile != null) {
                        try {
                            src = ImageIO.read(imgFile);// 建構BufferedImage對像
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    PrintPicturePanel.this.repaint();// 呼叫paintComponent()方法
                }
            });
            previewButton.setText("選擇圖片");
        }
        return previewButton;
    }
    
    protected JButton getPrintButton() {
        if (printButton == null) {
            printButton = new JButton();
            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        job.setPrintable(PrintPicturePanel.this);// 為列印對像指定Printable對像
                        job.setJobName("列印圖片");// 設定列印工作的名稱
                        job.print();// 執行列印
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            printButton.setText("列印");
        }
        return printButton;
    }
    
    protected JPanel getControlPanel() {
        if (controlPanel == null) {
            controlPanel = new JPanel();
            controlPanel.setBorder(new LineBorder(Color.BLUE, 1, false));
            final FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(20);
            controlPanel.setLayout(flowLayout);
            controlPanel.add(getPreviewButton());
            controlPanel.add(getPrintButton());
        }
        return controlPanel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPage(g2);
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex > 0)
            return Printable.NO_SUCH_PAGE;// 沒有列印頁
        int x = (int) pageFormat.getImageableX();//獲得可列印區域的x座標
        int y = (int) pageFormat.getImageableY();//獲得可列印區域的y座標
        Graphics2D g2 = (Graphics2D) graphics;
        int ableW = (int) pageFormat.getImageableWidth();//獲得可列印區域的寬度
        int ableH = (int) pageFormat.getImageableHeight();//獲得可列印區域的高度
        int imgW = 0;// 定義列印圖片的寬度
        int imgH = 0;// 定義列印圖片的高度
        if (src != null) {
            imgW = src.getWidth();// 獲得圖片的寬度
            imgH = src.getHeight();// 獲得圖片的高度
            if (imgW > ableW) {// 圖片寬度大於列印區域的寬度
                imgW = ableW;// 圖片寬度為列印區域的寬度
            }
            if (imgH > ableH) {// 圖片高度大於列印區域的高度
                imgH = ableH;// 圖片高度為列印區域的高度
            }
        }
        g2.drawImage(src, x, y, imgW, imgH, this);// 繪製列印內容
        return Printable.PAGE_EXISTS;// 傳回存在列印內容的資訊
    }
}
