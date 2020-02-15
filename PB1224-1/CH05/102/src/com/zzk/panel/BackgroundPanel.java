package com.zzk.panel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
public class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image image; // �w�q�ϧιﹳ
    public BackgroundPanel(Image image) {
        super(); // �I�s�W���O���غc��k
        this.image = image; // ���ϧιﹳ������
        initialize();
    }
    /*
     * ���s�w�qpaintComponent��k
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // �I�s�����O����k
        Graphics2D g2 = (Graphics2D) g; // �إ�Graphics2D�ﹳ
        if (image != null) {
            int width = getWidth(); // ��o���O���e��
            int height = getHeight(); // ��o���O������
            // ø�s�ϧ�
            g2.drawImage(image, 0, 0, width, height, this);
        }
    }
    private void initialize() {
        this.setSize(300, 200);
    }
}