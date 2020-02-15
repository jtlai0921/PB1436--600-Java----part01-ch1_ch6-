package com.zzk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 帶背景的面板元件
 * 
 * @author 張振坤
 */
public class PrintPanel extends JPanel implements Printable {
    
    /**
     * 背景圖片
     */
    private PageFormat pageFormat;// 列印格式對像
    private JTextArea textArea;// 純文字域元件
    private int px;// 可列印區域的水平座標
    private int py;// 可列印區域的垂直座標
    private int pwidth;// 可列印區域的寬度
    private int pheight;// 可列印區域的高度
    private boolean reverse = false;// 是否反轉
    
    /**
     * 建構方法
     */
    public PrintPanel() {
        super();
        setLayout(null);
        pageFormat = new PageFormat();// 建立頁面格式對像
        pageFormat.setOrientation(PageFormat.LANDSCAPE);// 設定水平頁面
        textArea = new JTextArea();// 建立純文字域元件
        textArea.setBackground(new Color(250, 250, 250));
        textArea.setBounds(200, 5, 100, 54);
        textArea.setLineWrap(true);// 自動換行
        textArea.setOpaque(false);
        // 設定初識純文字
        textArea.setText("請在這裡輸入要列印的純文字，單擊鏡面效果按鈕可以看效果，列印按鈕將以目前效果列印。");
        add(textArea);
        setVisible(true);// 顯示窗體
    }
    
    @Override
    protected void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);// 繪製原有元件內容
        try {
            print(g, pageFormat, 0);// 呼叫列印方法繪製面板界面
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int print(Graphics g1, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        Graphics2D g = (Graphics2D) g1;
        px = (int) pageFormat.getImageableX();// 獲得可列印區域的x座標
        py = (int) pageFormat.getImageableY();// 獲得可列印區域的y座標
        pwidth = (int) pageFormat.getImageableWidth();// 獲得可列印的寬度
        pheight = (int) pageFormat.getImageableHeight();// 獲得可列印的高度
        textArea.setBounds(px, py, pwidth, pheight);// 設定純文字域元件大小
        int pageWidth = (int) pageFormat.getWidth();// 獲得列印頁面寬度
        int pageHeight = (int) pageFormat.getHeight();// 獲得列印頁面高度
        Dimension preferredSize = new Dimension(pageWidth, pageHeight);
        setPreferredSize(preferredSize);// 設定面板大小
        getParent().doLayout();// 重新定義佈局父容器
        g.setColor(Color.WHITE);// 設定前景色為白色
        g.fill3DRect(0, 0, pageWidth, pageHeight, true);// 繪製與列印頁面相同大小的矩形
        if (pageIndex < 1 && textArea != null && reverse) {// 如果目前列印頁數小於1並且開啟鏡面效果
            BufferedImage image = new BufferedImage(pwidth - px, pheight - py,
                    BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
            Graphics2D graphics = image.createGraphics();// 獲得圖片對象的繪圖上下文
            graphics.setColor(Color.WHITE);// 設定前景色為白色
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());// 使用白色填充界面
            graphics.setColor(Color.BLACK);// 設定前景色為黑色
            Font font = textArea.getFont();// 獲得純文字域元件的字體對像
            graphics.setFont(font);// 把字體對像設定給圖片的繪圖上下文
            textArea.print(graphics);// 把純文字域界面繪製到緩衝圖形對像上
            image.flush();// 更新圖片繪圖緩衝區
            g.drawImage(image, px, py, pwidth, pheight, image.getWidth(), 0, 0,
                    image.getHeight(), this);// 反向繪製列印內容，實現鏡面效果
            return Printable.PAGE_EXISTS;// 傳回可列印標誌
        } else {// 否則
            return Printable.NO_SUCH_PAGE;// 傳回不支援列印標誌
        }
    }
    
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
        if (reverse) {
            textArea.setVisible(false);
            textArea.setEditable(false);
        } else {
            textArea.setVisible(true);
            textArea.setEditable(true);
        }
    }
    
    public void pageSet(PrinterJob job) {
        pageFormat = job.pageDialog(pageFormat);// 開啟頁面設定交談視窗
        repaint();// 重新繪製界面
    }
    
    public boolean isReverse() {
        return reverse;
    }
}
