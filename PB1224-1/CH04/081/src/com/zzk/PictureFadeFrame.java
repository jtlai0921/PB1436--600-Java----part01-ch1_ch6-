package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureFadeFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private Image fadeImage = null;// 宣告閃現的圖形對像
    private TextFadePanel textFadePanel = null; // 宣告圖形面板對像
    private URL imgUrl = null;// 宣告URL對像
    public static void main(String args[]) {
        PictureFadeFrame frame = new PictureFadeFrame();
        frame.setVisible(true);
    }
    
    public PictureFadeFrame() {
        super();
        imgUrl = PictureFadeFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形對像
        imgUrl = PictureFadeFrame.class.getResource("/img/fade.jpg");// 獲得圖片資源的路徑
        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形對像
        textFadePanel = new TextFadePanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 310, 230); // 設定窗體大小和位置
        this.add(textFadePanel); // 在窗體上增加圖形面板對像
        Thread thread = new Thread(textFadePanel);// 建立線程對像
        thread.start();// 啟動線程對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片閃現動畫"); // 設定窗體標題
    }
    
    // 建立面板類別
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// 標記變數
        String value = "";// 存放繪製內容的變數
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(),getHeight());// 清除繪圖上下文的內容
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// 繪製圖形
            g.drawImage(fadeImage,10,10,getWidth()-20,getHeight()-20,this);// 繪製閃現的圖形對像
        }
        public void run() {
            try {
                while (true) { // 讀取內容
                    Thread.sleep(200); // 目前線程休眠200毫秒
                    if (flag) {// flag為true
                        flag = false;   // 給予值為false
                        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形對像
                    } else {
                        flag = true;// 給予值為true
                        fadeImage = Toolkit.getDefaultToolkit().getImage(""); // 獲得圖形對像
                    }
                    repaint();// 呼叫paint()方法
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
