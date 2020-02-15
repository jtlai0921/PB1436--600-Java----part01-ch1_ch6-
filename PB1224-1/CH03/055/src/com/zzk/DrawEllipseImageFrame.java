package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawEllipseImageFrame extends JFrame {
    private Image img = null;  // �ŧi�ϧιﹳ
    private EllipseImagePanel imagePanel = null;  // �ŧi�ϧέ��O�ﹳ
    public static void main(String args[]) {
        DrawEllipseImageFrame frame = new DrawEllipseImageFrame();
        frame.setVisible(true);
    }
    public DrawEllipseImageFrame() {
        super();
        URL imgUrl = DrawEllipseImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imagePanel = new EllipseImagePanel();  // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(imagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�H������ܹϧ�"); // �]�w������D
    }
    // �إ߭��O���O
    class EllipseImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(img, 20, 20, 260, 160, this);// ø�s�ϧ�
            Rectangle2D.Float rectangle = new Rectangle2D.Float(0, 0, getWidth(),getHeight());// �إ߯x�ιﹳ
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20, 20, 260, 160);// �إ߾��ιﹳ
            Area area1 = new Area(rectangle);   // �إ߰ϰ�x��
            Area area2 = new Area(ellipse);   // �إ߰ϰ���
            area1.subtract(area2);// ��Ӱϰ�Ϊ��i���B��
            g2.setColor(getBackground());// �]�wø�ϤW�U�媺�C�⬰���O���I���C��
            g2.fill(area1);  // ø�s��B��᪺�ϰ�Ϊ�
        }
    }
}
