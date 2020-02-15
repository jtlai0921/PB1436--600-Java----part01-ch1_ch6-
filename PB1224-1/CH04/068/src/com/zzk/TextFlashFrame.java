package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextFlashFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private TextFlashPanel textFlashPanel = null; // 宣告圖形面板對像
    public static void main(String args[]) {
        TextFlashFrame frame = new TextFlashFrame();
        frame.setVisible(true);
    }
    
    public TextFlashFrame() {
        super();
        URL imgUrl = TextFlashFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        textFlashPanel = new TextFlashPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 310, 230); // 設定窗體大小和位置
        this.add(textFlashPanel); // 在窗體上增加圖形面板對像
        Thread thread = new Thread(textFlashPanel);// 建立線程對像
        thread.start();// 啟動線程對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("文字閃現特效"); // 設定窗體標題
    }
    
    // 建立面板類別
    class TextFlashPanel extends JPanel implements Runnable {
        boolean flag = true;// 標記變數
        String value = "";// 存放繪製內容的變數
        public void paint(Graphics g) {
            g.clearRect(0, 0, 310, 230);// 清除繪圖上下文的內容
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// 繪製圖形
            Font font = new Font("華文楷體", Font.BOLD, 42);// 建立字體對像
            g.setFont(font);// 指定字體
            g.setColor(Color.RED);// 指定顏色
            g.drawString(value, 10, 110);// 繪製純文字
            
        }
        public void run() {
            try {
                while (true) { // 讀取內容
                    Thread.sleep(150); // 目前線程休眠1秒
                    if (flag) {// flag為true
                        flag = false;   // 給予值為false
                        value="明日編程詞典";// 為value給予值
                    } else {
                        flag = true;// 給予值為true
                        value="";// 給予值為空字串
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