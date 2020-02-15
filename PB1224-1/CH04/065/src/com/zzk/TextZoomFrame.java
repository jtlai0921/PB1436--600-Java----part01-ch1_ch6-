package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextZoomFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private TextZoomPanel textZoomPanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        TextZoomFrame frame = new TextZoomFrame();
        frame.setVisible(true);
    }
    
    public TextZoomFrame() {
        super();
        URL imgUrl = TextZoomFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        textZoomPanel = new TextZoomPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 376, 237); // 設定窗體大小和位置
        this.add(textZoomPanel); // 在窗體上增加圖形面板對像
        Thread thread = new Thread(textZoomPanel);// 建立線程對像
        thread.start();// 啟動線程對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("文字縮放特效"); // 設定窗體標題
    }
    
    // 建立面板類別
    class TextZoomPanel extends JPanel implements Runnable {
        boolean flag = false;// 定義標記變數，用於控制x的值
        int x = 12;// 定義表示字體大小的變數x
        Font font = new Font("華文楷體", Font.BOLD, x);// 建立字體對像
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// 繪製圖形
            g2.setFont(font);// 指定字體
            g2.setColor(Color.RED);// 指定顏色
            g2.drawString("編程詞典", 30, 120);// 繪製純文字
        }
        public void run() {
            while (true) {
                if (flag) {        // flag為true時
                    x-=1;       // x進行減1計算
                    if (x <= 12) {// x小於等於12時
                        x = 12;   // x等於12
                        flag = false;// 為flag給予值為false
                    }
                } else {// flag為false時
                    x+=1;// x進行加1計算
                    if (x >= 72) {// x大於等於72時
                        x = 72;// x等於72
                        flag = true;// 為flag給予值為true
                    }
                }
                font = new Font("華文楷體", Font.BOLD, x);// 重新建立字體對像
                repaint();// 呼叫paint()方法
                try {
                    Thread.sleep(50);// 休眠50毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
