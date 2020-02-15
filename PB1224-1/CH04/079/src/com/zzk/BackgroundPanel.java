package com.zzk;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * 背景面板
 * @author 張振坤
 */
public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 5260642571525243284L;
	private Image image;// 背景圖形
	public BackgroundPanel() {
		setOpaque(false);// 透明
		setLayout(null);// 絕對佈局
	}
	public void setImage(Image image) {
		this.image = image;// 設定圖形
	}
	protected void paintComponent(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, this);// 繪製圖形
		}
		super.paintComponent(g);// 呼叫超類別的方法
	}
}
