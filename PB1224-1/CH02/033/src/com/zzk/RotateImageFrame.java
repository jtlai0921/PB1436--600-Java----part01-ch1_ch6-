package com.zzk;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class RotateImageFrame extends JFrame {
    private Image img = null;
    private RotatePanel rotatePanel = null;
    public RotateImageFrame() {
        URL imgUrl = RotateImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl);   // ��o�Ϥ��귽
        rotatePanel = new RotatePanel();  // �إ߱���ϧΪ����O�ﹳ
        this.setBounds(150, 120, 380, 310);                 // �]�w����j�p�M��m
        add(rotatePanel);// �b����W��m�ϧέ��O
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // �]�w���������Ҧ�
        this.setTitle("����ϧ�");                     // �]�w������D
    }
    public static void main(String[] args) {
        new RotateImageFrame().setVisible(true);
    }
    class RotatePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;         // ��oGraphics2D�ﹳ
            g2.drawImage(img, 80, 10, 260, 150, this);      // ø�s���w�j�p���Ϥ�
            g2.rotate(Math.toRadians(10));                 // �N�Ϥ�����10��
            g2.drawImage(img, 80, 10, 260, 150, this);      // ø�s���w�j�p���Ϥ�
            g2.rotate(Math.toRadians(10));                // �N�Ϥ�����10��
            g2.drawImage(img, 80, 10, 260, 150, this);      // ø�s���w�j�p���Ϥ�
        }
    }
}
