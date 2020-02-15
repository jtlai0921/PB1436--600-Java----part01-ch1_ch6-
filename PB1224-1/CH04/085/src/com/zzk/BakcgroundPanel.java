package com.zzk;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.*;
/**
 * @author �i���[
 *
 */
@SuppressWarnings("serial")
public class BakcgroundPanel extends JPanel {
	public BakcgroundPanel() {
		super();
	}
	public void paintComponent(Graphics g) {
		try {
			URL url = getClass().getResource("/images/back.jpg"); // ���w�Ϥ����|
			ImageIcon image = new ImageIcon(url); // �إ�ImageIcon�ﹳ
			g.drawImage(image.getImage(), 0, 0, this); // �N�Ϥ�ø�s�쭱�O�W
			g.setColor(Color.RED); // ø�s�C��
			g.fillRect(0, 40, 190, 40); // �b���O�Wø�s�����
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