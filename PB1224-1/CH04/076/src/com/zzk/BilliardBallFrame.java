package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class BilliardBallFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BilliardBallFrame frame = new BilliardBallFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public BilliardBallFrame() {
        super();
        setTitle("撞球動畫");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BilliardBallPanel panel = new BilliardBallPanel();// 建立面板對像
        getContentPane().add(panel);// 將面板增加到窗體容器
        Thread thread = new Thread(panel);// 建立線程對像
        thread.start();// 啟動線程對像
    }
    
    /**
     * 建立實現Runnable接口的內部面板類別
     */
    private class BilliardBallPanel extends JPanel implements Runnable {
        int x1 = 0;// 定義第一個球移動位置的x座標
        int y1 = 60;// 定義第一個球移動位置的y座標
        int x2 = 326 - 30;// 定義第二個球移動位置的初始x座標為窗體寬度減球的寬度
        int y2 = 60;// 定義第二個球移動位置的y座標
        int width = 30;// 定義球的寬度
        int height = 30;// 定義球的高度
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// 清除面板上的內容
            g.setColor(Color.BLUE);// 設定顏色
            g.fillOval(x1, y1, width, height);// 繪製第一個球
            g.setColor(Color.RED);// 設定顏色
            g.fillOval(x2, y2, width, height);// 繪製第二個球
        }
        public void run() {
            boolean flag1 = true;// 宣告第一個球的標記變數
            boolean flag2 = true;// 宣告第二個球的標記變數
            while (true) {
                // 第一個球的x座標值加上球的寬度大於等於第二個球的x座標值加1，表示兩球相遇
                if (x1 + width >= x2 + 1) {// 兩球相撞
                    x1 = x1 + 5;// 計算第一個球的x座標
                    width = width - 10;// 球的寬度減10
                    x2 = x1 + width;// 計算第二個球的x座標
                    flag1 = false;// 設定第一個球的標記變數為false
                    flag2 = false;// 設定第二個球的標記變數為false
                    repaint();// 呼叫paint()方法
                } else {// 兩球沒相撞
                    if (flag1) {// 標記變數為true，第一個球右移
                        x1 = x1 + 10;// 圖片x座標值加10，第一個球右移
                        width = 30;// 球的寬度
                    } else {// 標記變數為false，第一個球左移
                        x1 = x1 - 10;// 圖片x座標值減10，第一個球左移
                        width = 30;// 球的寬度
                        if (x1 <= 0) {// 圖片的x座標值小於等於0
                            x1 = 0;// 圖片的x座標值等於0
                            flag1 = true;// 設定標記變數為true
                        }
                    }
                    if (flag2) {// 標記變數為true，第二個球左移
                        x2 = x2 - 10;// 圖片x座標值減10，即第二個球左移
                        width = 30;
                    } else {// 標記變數為false，第二個球右移
                        x2 = x2 + 10;// 圖片x座標值加10，即第二個球右移
                        width = 30;// 球的寬度
                        if (x2 >= getWidth() - width) {// 圖片的x座標值大於等於面板的寬度與球的寬度之差
                            x2 = getWidth() - width ;// 圖片的x座標值等於面板的寬度與球的寬度之差
                            flag2 = true;// 設定標記變數為true
                        }
                    }
                }
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