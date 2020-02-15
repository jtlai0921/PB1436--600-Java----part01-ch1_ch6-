package com.zzk;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class ShearImageFrame extends JFrame {
	private Image img;
	private ShearImagePanel canvasPanel = null;
	public ShearImageFrame() {
        URL imgUrl = ShearImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl);  // 獲得圖片資源
        canvasPanel = new ShearImagePanel();     // 建立繪製傾斜圖形的面板對像
        this.setBounds(100, 100, 360, 240);                // 設定窗體大小和位置
        add(canvasPanel);// 在窗體上增加面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("傾斜圖形");                    // 設定窗體標題
	}
	public static void main(String[] args) {
		new ShearImageFrame().setVisible(true);
	}
	class ShearImagePanel extends JPanel {// 繪製傾斜圖形的面板類別
		public void paint(Graphics g) {
			Graphics2D g2=(Graphics2D) g;// 獲得Graphics2D對像
			g2.shear(0.5, 0);// 傾斜圖形
			g2.drawImage(img, 10, 20, 220, 160, this);     // 繪製指定大小的圖片
		}
	}
}
