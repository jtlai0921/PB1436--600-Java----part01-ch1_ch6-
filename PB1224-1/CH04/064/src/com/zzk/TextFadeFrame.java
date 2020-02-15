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

public class TextFadeFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private TextFadePanel textFadePanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        TextFadeFrame frame = new TextFadeFrame();
        frame.setVisible(true);
    }
    
    public TextFadeFrame() {
        super();
        URL imgUrl = TextFadeFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        textFadePanel = new TextFadePanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(textFadePanel); // 在窗體上增加圖形面板對像
        Thread thread = new Thread(textFadePanel);// 建立線程對像
        thread.start();// 啟動線程對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("文字淡入淡出特效"); // 設定窗體標題
    }
    
    // 建立面板類別
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// 定義標記變數，用於控制x的值
        float x = 0.0f;// 定義表示透明度的變數x
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(x);// 獲得表示透明度的AlphaComposite對像
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// 繪製圖形
            Font font = new Font("華文楷體", Font.BOLD, 60);// 建立字體對像
            g2.setFont(font);// 指定字體
            g2.setColor(Color.RED);// 指定顏色
            g2.setComposite(alpha);// 指定AlphaComposite對像
            g2.drawString("編程詞典", 30, 120);// 繪製純文字
        }
        ////// 注意浮點數相減，計算結果不精確，所以加上if (x <= 0.0f) { x = 0.0f;
        public void run() {
            while (true) {
                if (flag) {        // flag為true時
                    x-=0.1f;       // x進行減0.1計算
                    if (x <= 0.0f) {// x小於等於0.0f時
                        x = 0.0f;   // x等於0.0f
                        flag = false;// 為flag給予值為false
                    }
                } else {// flag為false時
                    x+=0.1f;// x進行加0.1計算
                    if (x >= 1.0f) {// x大於等於1.0f時
                        x = 1.0f;// x等於1.0f
                        flag = true;// 為flag給予值為true
                    }
                }
                alpha = AlphaComposite.SrcOver.derive(x);// 重新獲得表示透明度的AlphaComposite對像
                repaint();// 呼叫paint()方法
                try {
                    Thread.sleep(150);// 休眠150毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
