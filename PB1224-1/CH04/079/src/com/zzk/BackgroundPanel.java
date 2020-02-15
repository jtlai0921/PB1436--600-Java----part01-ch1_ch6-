package com.zzk;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * �I�����O
 * @author �i���[
 */
public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 5260642571525243284L;
	private Image image;// �I���ϧ�
	public BackgroundPanel() {
		setOpaque(false);// �z��
		setLayout(null);// ����G��
	}
	public void setImage(Image image) {
		this.image = image;// �]�w�ϧ�
	}
	protected void paintComponent(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, this);// ø�s�ϧ�
		}
		super.paintComponent(g);// �I�s�W���O����k
	}
}
