package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DynamicFlexImageFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private DynamicFlexPanel imagePanel = null; // 宣告圖形面板對像
    private Thread thread = null;// 宣告線程對像
    
    public static void main(String args[]) {
        DynamicFlexImageFrame frame = new DynamicFlexImageFrame();
        frame.setVisible(true);
    }
    
    public DynamicFlexImageFrame() {
        super();
        URL imgUrl = DynamicFlexImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imagePanel = new DynamicFlexPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 340, 200); // 設定窗體大小和位置
        this.add(imagePanel); // 在窗體上增加圖形面板對像
        thread = new Thread(imagePanel);// 建立線程對像
        thread.start();// 啟動線程
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片動態伸展"); // 設定窗體標題
    }
    
    // 建立面板類別
    class DynamicFlexPanel extends JPanel implements Runnable {
        private boolean flag = true;// 標記變數
        int width = 0;// 調整圖形寬度的變數
        int height = 0;// 調整圖形高度的變數
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// 清除繪圖上下文的內容
            g.drawImage(img, 0, 0, width, height, this); // 繪製指定大小的圖片
        }
        public void run() {
            while (true) {
                if (flag) {
                    width+=2;// 調整寬度值
                    height++;// 調整高度值
                    if (width >= getWidth() || height >= getHeight()) {
                        flag = false;// 達到圖形的寬度或高度時，改變標記變數的值
                    }
                } else {
                    width -= 2;// 調整寬度值
                    height--;// 調整高度值
                    if (width <= 0 || height <= 0) {
                        flag = true;// 圖形的寬度或高度小於等於0時，改變標記變數的值
                    }
                }
                repaint();// 呼叫paint()方法
                try {
                    Thread.sleep(20);// 線程休眠20毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
