package com.zzk;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class RotateImageFrame extends JFrame {
    private Image img = null;
    private RotatePanel rotatePanel = null;
    public RotateImageFrame() {
        URL imgUrl = RotateImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl);   // 獲得圖片資源
        rotatePanel = new RotatePanel();  // 建立旋轉圖形的面板對像
        this.setBounds(150, 120, 380, 310);                 // 設定窗體大小和位置
        add(rotatePanel);// 在窗體上放置圖形面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 設定窗體關閉模式
        this.setTitle("旋轉圖形");                     // 設定窗體標題
    }
    public static void main(String[] args) {
        new RotateImageFrame().setVisible(true);
    }
    class RotatePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;         // 獲得Graphics2D對像
            g2.drawImage(img, 80, 10, 260, 150, this);      // 繪製指定大小的圖片
            g2.rotate(Math.toRadians(10));                 // 將圖片旋轉10度
            g2.drawImage(img, 80, 10, 260, 150, this);      // 繪製指定大小的圖片
            g2.rotate(Math.toRadians(10));                // 將圖片旋轉10度
            g2.drawImage(img, 80, 10, 260, 150, this);      // 繪製指定大小的圖片
        }
    }
}
