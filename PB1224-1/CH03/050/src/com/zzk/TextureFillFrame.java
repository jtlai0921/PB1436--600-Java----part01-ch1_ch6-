package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextureFillFrame extends JFrame {
    private TextureFillPanel textureFillPanel = null;  // 宣告面板對像
    public static void main(String args[]) {
        TextureFillFrame frame = new TextureFillFrame();
        frame.setVisible(true);
    }
    public TextureFillFrame() {
        super();
        textureFillPanel = new TextureFillPanel();  // 建立圖形面板對像
        this.setBounds(200, 160, 308, 230); // 設定窗體大小和位置
        this.add(textureFillPanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("紋理填充特效"); // 設定窗體標題
    }
    // 建立面板類別
    class TextureFillPanel extends JPanel {
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
            Graphics2D g2 = image.createGraphics();// 獲得緩衝圖形對象的繪圖上下文對像
            g2.setColor(Color.BLUE);// 設定顏色
            g2.fillRect(0,0,90,90);// 繪製填充矩形
            g2.setColor(Color.RED);// 設定顏色
            g2.fillOval(95,95,90,90);// 繪製帶填充色的圓形
            Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// 建立Rectangle2D對像
            TexturePaint textPaint = new TexturePaint(image,rect);// 建立紋理填充對像
            Graphics2D graphics2 = (Graphics2D)g;// 轉換paint()方法的繪圖上下文對像
            graphics2.setPaint(textPaint);// 為繪圖上下文對像設定紋理填充對像
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(45, 25, 200, 140);// 建立矩形對像
            graphics2.fill(ellipse2);// 繪製填充紋理的矩形
        }
    }
}
