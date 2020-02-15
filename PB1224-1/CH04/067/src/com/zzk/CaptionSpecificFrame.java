package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CaptionSpecificFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private CaptionSpecificPanel captionSpecificPanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        CaptionSpecificFrame frame = new CaptionSpecificFrame();
        frame.setVisible(true);
    }
    
    public CaptionSpecificFrame() {
        super();
        URL imgUrl = CaptionSpecificFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        captionSpecificPanel = new CaptionSpecificPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(captionSpecificPanel); // 在窗體上增加圖形面板對像
        Thread thread = new Thread(captionSpecificPanel);// 建立線程對像
        thread.start();// 啟動線程對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("字幕顯示特效"); // 設定窗體標題
    }
    
    // 建立面板類別
    class CaptionSpecificPanel extends JPanel implements Runnable {
        int x = 50;// 儲存繪製點的x座標
        int y = 216;// 儲存繪製點的y座標
        String value = "明日編程詞典網址";// 儲存繪製的內容
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
                    Thread.sleep(100); // 目前線程休眠1秒
                    if (y <= 216 - 50) {// 如果已經向上移動50像素
                        y = 216;// y座標定位到最下方
                        if (value.equals("明日編程詞典網址")) {
                            value = "http://www.mrbccd.com";// 改變繪製的內容
                        } else {
                            value = "明日編程詞典網址";// 改變繪製的內容
                        }
                    } else {// 如果還沒向上移動到50像素
                        y -= 2;// y座標上移
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