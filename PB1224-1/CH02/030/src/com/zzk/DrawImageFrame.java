package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawImageFrame extends JFrame {
    private Image img = null;  // 宣告圖形對像
    private DrawImagePanel imagePanel = null;  // 宣告圖形面板對像
    public static void main(String args[]) {
        DrawImageFrame frame = new DrawImageFrame();
        frame.setVisible(true);
    }
    public DrawImageFrame() {
        super();
        URL imgUrl = DrawImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imagePanel = new DrawImagePanel();  // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(imagePanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("繪製圖形"); // 設定窗體標題
    }
    // 建立面板類別
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, this); // 繪製指定的圖片
        }
    }
}
