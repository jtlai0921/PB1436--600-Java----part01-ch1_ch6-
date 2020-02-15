package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class PictureMixFrame extends JFrame {
    private Image img1 = null; // �ŧi�ϧιﹳ
    private Image img2 = null; // �ŧi�ϧιﹳ
    private JSlider slider = null;
    private PictureMixPanel pictureMixPanel = null; // �ŧi�ϧέ��O�ﹳ
    public static void main(String args[]) {
        PictureMixFrame frame = new PictureMixFrame();
        frame.setVisible(true);
    }
    
    public PictureMixFrame() {
        super();
        URL imgUrl = PictureMixFrame.class.getResource("/img/img.jpg");// ��o�Ϥ��귽�����|
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imgUrl = PictureMixFrame.class.getResource("/img/imag.jpg");// ��o�Ϥ��귽�����|
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        pictureMixPanel = new PictureMixPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(pictureMixPanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ����X�S��"); // �]�w������D

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                pictureMixPanel.repaint();// ���s�I�s���O���O��paint()��k
            }
        });
        getContentPane().add(slider, BorderLayout.SOUTH);
    }
    
    // �إ߭��O���O
    class PictureMixPanel extends JPanel {
        boolean flag = true;// �w�q�аO�ܼơA�Ω󱱨�x����
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.5f);// ��o��ܳz���ת�AlphaComposite�ﹳ
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            g2.drawImage(img1, 0, 0,  getWidth(), getHeight(), this);// ø�s�ϧ�
            float value = slider.getValue();// �ưʰ϶����󪺨���
            float alphaValue = value / 100;// �p��z���ת���
            alpha = AlphaComposite.SrcOver.derive(alphaValue);// ��o��ܳz���ת�AlphaComposite�ﹳ
            g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
            g.drawImage(img2, 0, 0, getWidth(), getHeight(), this);// ø�s�վ�z���׫᪺�Ϥ�
        }
        
    }
}
