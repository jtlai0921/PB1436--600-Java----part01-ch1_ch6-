package com.zzk;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.*;

@SuppressWarnings("serial")
public class RotateImageFrame extends JFrame {
    private Image img = null;
    private RotatePanel rotatePanel = null;
    public RotateImageFrame() {
        URL imgUrl = RotateImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�Ϥ��귽
        rotatePanel = new RotatePanel(); // �إ߱���ϧΪ����O�ﹳ
        this.setBounds(150, 120, 380, 277);// �]�w����j�p�M��m
        add(rotatePanel);// �b����W��m�ϧέ��O
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ�����ʵe"); // �]�w������D
        Thread th = new Thread(rotatePanel);// �إ߽u�{�ﹳ
        th.start();// �Ұʽu�{
    }
    public static void main(String[] args) {
        new RotateImageFrame().setVisible(true);
    }
    class RotatePanel extends JPanel implements Runnable {
        int angle = 0;// ��l���ਤ��
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            g2.translate(190, 120);// �����y�жb
            g2.clearRect(-190, -120, getWidth(), getHeight());// �M���e���W�����e
            g2.rotate(Math.toRadians(angle)); // ����e��
            g2.drawImage(img, -95, -80, 190, 160, this);// ø�s���w�j�p���Ϥ�
        }
        public void run() {
            while (true) {
                angle = (angle + 10) % 360;// �p����ਤ��
                try {
                    Thread.sleep(200);// ��v200�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// �I�spaint()��k
            }
        }
    }
}
