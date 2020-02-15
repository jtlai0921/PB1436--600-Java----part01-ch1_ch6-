package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
/**
 * �I�����O
 * @author �i���[
 *
 */
public class BackgroundPanel extends JPanel {
	private static final long serialVersionUID = 5260642571525243284L;
	// �I���Ϥ�
	private Image image;

	public BackgroundPanel() {
		setOpaque(false);
		setLayout(null);
	}

	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * �e�X�I��
	 */
	protected void paintComponent(Graphics g) {
		if (image != null) {
			// �Ϥ��e��
			int width = getWidth();
			// �Ϥ�����
			int height = getHeight();
			// �e�X�Ϥ�
			g.drawImage(image, 0, 0, width, height, this);
		}
		super.paintComponent(g);
	}
}
