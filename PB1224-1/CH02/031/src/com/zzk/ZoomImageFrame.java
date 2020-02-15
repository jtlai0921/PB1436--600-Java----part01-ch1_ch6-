package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class ZoomImageFrame extends JFrame {
    private Image img = null;  // �ŧi�ϧιﹳ
    private ZoomImagePanel imagePanel = null;  // �ŧi�ϧέ��O�ﹳ
    private int imgWidth, imgHeight;// �Ω��x�s�Ϥ����e�שM����
    private int newW, newH;// �Ω��x�s�Ϥ��Y��᪺�e�שM����
    private JSlider slider = null;// �ŧi�ưʰ϶��ﹳ
    public static void main(String args[]) {
        ZoomImageFrame frame = new ZoomImageFrame();
        frame.setVisible(true);
    }
    public ZoomImageFrame() {
        super();
        URL imgUrl = ZoomImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imagePanel = new ZoomImagePanel();  // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 355, 253); // �]�w����j�p�M��m
        this.add(imagePanel); // �b���餤����m�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Y��ϧ�"); // �]�w������D
        slider = new JSlider();// �إ߷ưʰ϶��ﹳ
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                imagePanel.repaint();// ���s�I�s���O���O��paint()��k
            }
        });
        getContentPane().add(slider, BorderLayout.SOUTH);// �b���驳����m�W�[�ưʰ϶��ﹳ
    }
    // �إ߭��O���O
    class ZoomImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �M��ø�ϤW�U�媺���e
            imgWidth = img.getWidth(this); // ��o�Ϥ��e��
            imgHeight = img.getHeight(this); // ��o�Ϥ�����
            float value = slider.getValue();// �ưʰ϶����󪺨���
            newW = (int) (imgWidth * value / 100);// �p��Ϥ��Y��᪺�e��
            newH = (int) (imgHeight * value / 100);// �p��Ϥ��Y��᪺����
            g.drawImage(img, 0, 0, newW, newH, this);// ø�s���w�j�p���Ϥ�
        }
    }
}
