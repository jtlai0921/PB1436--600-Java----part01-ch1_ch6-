package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureFadeFrame extends JFrame {
    private Image img1 = null; // 宣告圖形對像
    private Image img2 = null; // 宣告圖形對像
    private PictureFadePanel pictureFadePanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        PictureFadeFrame frame = new PictureFadeFrame();
        frame.setVisible(true);
    }
    
    public PictureFadeFrame() {
        super();
        URL imgUrl = PictureFadeFrame.class.getResource("/img/img.jpg");// 獲得圖片資源的路徑
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imgUrl = PictureFadeFrame.class.getResource("/img/imag.jpg");// 獲得圖片資源的路徑
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        pictureFadePanel = new PictureFadePanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(pictureFadePanel); // 在窗體上增加圖形面板對像
        Thread thread = new Thread(pictureFadePanel);// 建立線程對像
        thread.start();// 啟動線程對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片淡入淡出特效"); // 設定窗體標題
    }
    
    // 建立面板類別
    class PictureFadePanel extends JPanel implements Runnable {
        boolean flag = true;// 定義標記變數，用於控制x的值
        float x = 0.0f;// 定義表示透明度的變數x
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(x);// 獲得表示透明度的AlphaComposite對像
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            g2.clearRect( 0, 0, getWidth(), getHeight());// 繪製圖形
            g2.drawImage(img1, 0, 0, getWidth(), getHeight(), this);// 繪製圖形
            g2.setComposite(alpha);// 指定AlphaComposite對像
            g.drawImage(img2, 50, 40, getWidth() - 100, getHeight() - 80, this);// 繪製調整透明度後的圖片，實現圖片淡入淡出特效
        }
        public void run() {
            while (true) {
                if (flag) { // flag為true時
                    x -= 0.1f; // x進行減0.1計算
                    if (x <= 0.0f) {// x小於等於0.0f時
                        x = 0.0f; // x等於0.0f
                        flag = false;// 為flag給予值為false
                    }
                } else {// flag為false時
                    x += 0.1f;// x進行加0.1計算
                    if (x >= 1.0f) {// x大於等於1.0f時
                        x = 1.0f;// x等於1.0f
                        flag = true;// 為flag給予值為true
                    }
                }
                alpha = AlphaComposite.SrcOver.derive(x);// 重新獲得表示透明度的AlphaComposite對像
                repaint();// 呼叫paint()方法
                try {
                    Thread.sleep(200);// 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
