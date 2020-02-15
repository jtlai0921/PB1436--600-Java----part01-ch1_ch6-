package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WatermarkTextFrame extends JFrame {
    private Image img = null;  // 宣告圖形對像
    private WatermarkTextPanel watermarkTextPanel = null;  // 宣告圖形面板對像
    public static void main(String args[]) {
        WatermarkTextFrame frame = new WatermarkTextFrame();
        frame.setVisible(true);
    }
    public WatermarkTextFrame() {
        super();
        URL imgUrl = WatermarkTextFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        watermarkTextPanel = new WatermarkTextPanel();  // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(watermarkTextPanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("水印文字特效"); // 設定窗體標題
    }
    // 建立面板類別
    class WatermarkTextPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;// 獲得Graphics2D對像
            g2.drawImage(img, 0, 0, 300, 237, this);// 繪製圖形
            g2.rotate(Math.toRadians(-30));// 旋轉繪圖上下文對像
            Font font = new Font("楷體",Font.BOLD,60);// 建立字體對像
            g2.setFont(font);//指定字體
            g2.setColor(Color.WHITE);// 指定顏色
            AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.3f);// 獲得表示透明度的AlphaComposite對像
            g2.setComposite(alpha);// 指定AlphaComposite對像
            g2.drawString("編程詞典", -60, 180);// 繪製純文字,實現水印
        }
    }
}
