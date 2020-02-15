package com.zzk;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
public class RotateImageFrame extends JFrame {
    private Image img = null;
    private RotatePanel rotatePanel = null;
    public RotateImageFrame() {
        URL imgUrl = RotateImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖片資源
        rotatePanel = new RotatePanel(); // 建立旋轉圖形的面板對像
        this.setBounds(150, 120, 380, 277);// 設定窗體大小和位置
        add(rotatePanel);// 在窗體上放置圖形面板
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片旋轉動畫"); // 設定窗體標題
        Thread th = new Thread(rotatePanel);// 建立線程對像
        th.start();// 啟動線程
    }
    public static void main(String[] args) {
        new RotateImageFrame().setVisible(true);
    }
    class RotatePanel extends JPanel implements Runnable {
        int angle = 0;// 初始旋轉角度
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            g2.translate(190, 120);// 平移座標軸
            g2.clearRect(-190, -120, getWidth(), getHeight());// 清除畫布上的內容
            g2.rotate(Math.toRadians(angle)); // 旋轉畫布
            g2.drawImage(img, -95, -80, 190, 160, this);// 繪製指定大小的圖片
        }
        public void run() {
            while (true) {
                angle = (angle + 10) % 360;// 計算旋轉角度
                try {
                    Thread.sleep(200);// 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// 呼叫paint()方法
            }
        }
    }
}
