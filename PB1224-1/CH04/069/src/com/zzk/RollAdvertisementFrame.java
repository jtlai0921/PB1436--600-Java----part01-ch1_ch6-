package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RollAdvertisementFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private RollAdvertisementPanel rollAdvertisementPanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        RollAdvertisementFrame frame = new RollAdvertisementFrame();
        frame.setVisible(true);
    }
    
    public RollAdvertisementFrame() {
        super();
        URL imgUrl = RollAdvertisementFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        rollAdvertisementPanel = new RollAdvertisementPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(rollAdvertisementPanel); // 在窗體上增加圖形面板對像
        Thread thread = new Thread(rollAdvertisementPanel);// 建立線程對像
        thread.start();// 啟動線程對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("捲動廣告字幕"); // 設定窗體標題
    }
    
    // 建立面板類別
    class RollAdvertisementPanel extends JPanel implements Runnable {
        int x = 316;// 儲存繪製點的x座標
        int y = 190;// 儲存繪製點的y座標
        String value = "明日編程詞典網址：http://www.mrbccd.com";// 儲存繪製的內容
        public void paint(Graphics g) {
            g.clearRect(0, 0, 316, 237);// 清除繪圖上下文的內容
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// 繪製圖形
            Font font = new Font("華文楷體", Font.BOLD, 20);// 建立字體對像
            g.setFont(font);// 指定字體
            g.setColor(Color.RED);// 指定顏色
            g.drawString(value, x, y);// 繪製純文字
        }
        public void run() {
            try {
                while (true) { // 讀取內容
                    Thread.sleep(50); // 目前線程休眠1秒
                    if (x <= -400) {// 該條件可以根據需要自行調整
                        x = 316;// x座標定位到最右側
                    } else {
                        x -= 2;// x座標左移
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