package com.zzk;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.*;
/**
 * @author 張振坤
 *
 */
@SuppressWarnings("serial")
public class BakcgroundPanel extends JPanel {
	public BakcgroundPanel() {
		super();
	}
	public void paintComponent(Graphics g) {
		try {
			URL url = getClass().getResource("/images/back.jpg"); // 指定圖片路徑
			ImageIcon image = new ImageIcon(url); // 建立ImageIcon對像
			g.drawImage(image.getImage(), 0, 0, this); // 將圖片繪製到面板上
			g.setColor(Color.RED); // 繪製顏色
			g.fillRect(0, 40, 190, 40); // 在面板上繪製長方形
			g.setColor(Color.yellow);
			g.fillRect(190, 40, 40, 240);
			g.setColor(Color.pink);
			g.fillRect(190, 180, 230, 40);
			g.setColor(Color.cyan);
			g.fillRect(300, 180, 40, 140);

		} catch (Exception e) {
		}
	}
}
