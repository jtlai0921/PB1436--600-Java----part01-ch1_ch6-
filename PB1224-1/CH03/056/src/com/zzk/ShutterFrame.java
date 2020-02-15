package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShutterFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private Image img = null; // �ŧi�ϧιﹳ
    private ShutterPanel shutterPanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        ShutterFrame frame = new ShutterFrame();
        frame.setVisible(true);
    }
    
    public ShutterFrame() {
        super();
        URL imgUrl = ShutterFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        shutterPanel = new ShutterPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(shutterPanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��ʸ����S��"); // �]�w������D
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// �إ� Kernel�ﹳ
        ConvolveOp op = new ConvolveOp(kernel);// �إ�ConvolveOp�ﹳ
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // �L�o�w�Ĺϧιﹳ
        repaint();// �I�spaint()��k
    }
    
    // �إ߭��O���O
    class ShutterPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, this);// ø�s�ϧιﹳ
            int y = 5;  // ���uø�s�I��y�y��
            int space = 10;// �U�@�����u�������q
            Line2D.Float line = null;
            image = new BufferedImage(getWidth() + 10, getHeight(),
                    BufferedImage.TYPE_INT_ARGB);// �إ߽w�Ĺϧιﹳ
            Graphics2D gs2d = (Graphics2D) image.getGraphics();// ��o�w�Ĺϧι�H��Graphics2D�ﹳ
            BasicStroke stroke = new BasicStroke(7); // �إ߼e�׬O7�����e�ﹳ
            gs2d.setStroke(stroke);// �]�w���e�ﹳ
            gs2d.setColor(Color.WHITE);// ���w�C��
            while (y <= getHeight()) {
                line = new Line2D.Float(0, y, getWidth(), y);// �إߪ��u�ﹳ
                gs2d.draw(line);// �b�w�Ĺϧιﹳ�Wø�s���u
                y = y + space;// �p��U�@�����u��y�y��
            }
            for (int i = 0; i < 3; i++) {// ��for�`���A��{3���ҽk
                float[] elements = new float[9];// �w�q��ܹ������q���}�C
                for (int j = 0; j < 9; j++) {
                    elements[j] = 0.11f;// ���}�C������
                }
                convolve(elements);// �I�s��k�A��{�ҽk�\��
            }
            g2.drawImage(image, 0, 0, this);// ø�s�w�Ĺϧιﹳ
        }
    }
}
