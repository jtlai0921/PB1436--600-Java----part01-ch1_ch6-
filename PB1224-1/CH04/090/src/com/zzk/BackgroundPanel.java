package com.zzk;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * �a�I�������O����
 * 
 * @author �i���[
 */
public class BackgroundPanel extends JPanel {

	/**
	 * �I���Ϥ�
	 */
	private Image image;

	/**
	 * �غc��k
	 */
	public BackgroundPanel() {
		super();
		setOpaque(false);
		setLayout(null);
	}

	/**
	 * �]�w�Ϥ�����k
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {// ���s�w�qø�s����~�[
		if (image != null) {
			int width = getWidth();// ��o����j�p
			int height = getHeight();
			g.drawImage(image, 0, 0, width, height, this);// ø�s�Ϥ��P����j�p�ۦP
		}
		super.paintComponent(g);// ����W���O��k
	}
}
