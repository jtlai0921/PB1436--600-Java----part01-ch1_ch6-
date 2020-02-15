package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MosaicFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private Image img = null; // �ŧi�ϧιﹳ
    private MosaicPanel mosaicPanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        MosaicFrame frame = new MosaicFrame();
        frame.setVisible(true);
    }
    
    public MosaicFrame() {
        super();
        URL imgUrl = MosaicFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        mosaicPanel = new MosaicPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(mosaicPanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ����ɧJ�S��"); // �]�w������D

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int x = 104;// �x��ø�s�I��x�y��
                int y = 60; // �x��ø�s�I��y�y��
                Rectangle2D.Float rect = null;
                image = new BufferedImage(getWidth() + 10, getHeight(),
                        BufferedImage.TYPE_INT_ARGB);// �إ߽w�Ĺϧιﹳ
                Graphics2D gs2d = (Graphics2D) image.getGraphics();// ��o�w�Ĺϧι�H��Graphics2D�ﹳ
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.90f);// ��o��ܳz���ת�AlphaComposite�ﹳ
                gs2d.setComposite(alpha);// �]�w�z����
                GradientPaint paint = null;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint = new GradientPaint(x, y, Color.white, x + 10,
                                y + 10, Color.gray,true);// �إߴ`�����h��GraphientPaint�ﹳ
                        gs2d.setPaint(paint);// �]�w���h
                        rect = new Rectangle2D.Float(x, y, 20, 20);// �إ߯x�ιﹳ
                        gs2d.fill(rect);// �b�w�Ĺϧιﹳ�Wø�s�x��
                        y = y + 20;// �p��U�@�ӯx�Ϊ�y�y��
                    }
                    y = 60;// �٭�y�y��
                    x = x + 20;// �p��x�y��
                }
                
                for (int i = 0; i < 3; i++) {// ��for�`���A��{3���ҽk
                    float[] elements = new float[9];// �w�q��ܹ������q���}�C
                    for (int j = 0; j < 9; j++) {
                        elements[j] = 0.11f;// ���}�C������
                    }
                    convolve(elements);// �I�s��k�A��{�ҽk�\��
                }
                mosaicPanel.repaint();// �I�spaint()��k
            }
        });
        button.setText("�W�[���ɧJ");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h    �X");
        panel.add(button_1);
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// �إ� Kernel�ﹳ
        ConvolveOp op = new ConvolveOp(kernel);// �إ�ConvolveOp�ﹳ
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // �L�o�w�Ĺϧιﹳ
    }
    
    // �إ߭��O���O
    class MosaicPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, getWidth(),getHeight(), this);// ø�s�ϧιﹳ
            g2.drawImage(image, 0, 0, this);// ø�s�w�Ĺϧιﹳ
        }
    }
}
