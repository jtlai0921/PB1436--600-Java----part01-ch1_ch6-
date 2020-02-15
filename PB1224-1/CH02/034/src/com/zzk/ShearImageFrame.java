package com.zzk;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class ShearImageFrame extends JFrame {
	private Image img;
	private ShearImagePanel canvasPanel = null;
	public ShearImageFrame() {
        URL imgUrl = ShearImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl);  // ��o�Ϥ��귽
        canvasPanel = new ShearImagePanel();     // �إ�ø�s�ɱ׹ϧΪ����O�ﹳ
        this.setBounds(100, 100, 360, 240);                // �]�w����j�p�M��m
        add(canvasPanel);// �b����W�W�[���O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�ɱ׹ϧ�");                    // �]�w������D
	}
	public static void main(String[] args) {
		new ShearImageFrame().setVisible(true);
	}
	class ShearImagePanel extends JPanel {// ø�s�ɱ׹ϧΪ����O���O
		public void paint(Graphics g) {
			Graphics2D g2=(Graphics2D) g;// ��oGraphics2D�ﹳ
			g2.shear(0.5, 0);// �ɱ׹ϧ�
			g2.drawImage(img, 10, 20, 220, 160, this);     // ø�s���w�j�p���Ϥ�
		}
	}
}
