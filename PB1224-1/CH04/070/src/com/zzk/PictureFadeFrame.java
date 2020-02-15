package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureFadeFrame extends JFrame {
    private Image img1 = null; // �ŧi�ϧιﹳ
    private Image img2 = null; // �ŧi�ϧιﹳ
    private PictureFadePanel pictureFadePanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        PictureFadeFrame frame = new PictureFadeFrame();
        frame.setVisible(true);
    }
    
    public PictureFadeFrame() {
        super();
        URL imgUrl = PictureFadeFrame.class.getResource("/img/img.jpg");// ��o�Ϥ��귽�����|
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imgUrl = PictureFadeFrame.class.getResource("/img/imag.jpg");// ��o�Ϥ��귽�����|
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        pictureFadePanel = new PictureFadePanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(pictureFadePanel); // �b����W�W�[�ϧέ��O�ﹳ
        Thread thread = new Thread(pictureFadePanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��H�J�H�X�S��"); // �]�w������D
    }
    
    // �إ߭��O���O
    class PictureFadePanel extends JPanel implements Runnable {
        boolean flag = true;// �w�q�аO�ܼơA�Ω󱱨�x����
        float x = 0.0f;// �w�q��ܳz���ת��ܼ�x
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(x);// ��o��ܳz���ת�AlphaComposite�ﹳ
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            g2.clearRect( 0, 0, getWidth(), getHeight());// ø�s�ϧ�
            g2.drawImage(img1, 0, 0, getWidth(), getHeight(), this);// ø�s�ϧ�
            g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
            g.drawImage(img2, 50, 40, getWidth() - 100, getHeight() - 80, this);// ø�s�վ�z���׫᪺�Ϥ��A��{�Ϥ��H�J�H�X�S��
        }
        public void run() {
            while (true) {
                if (flag) { // flag��true��
                    x -= 0.1f; // x�i���0.1�p��
                    if (x <= 0.0f) {// x�p�󵥩�0.0f��
                        x = 0.0f; // x����0.0f
                        flag = false;// ��flag�����Ȭ�false
                    }
                } else {// flag��false��
                    x += 0.1f;// x�i��[0.1�p��
                    if (x >= 1.0f) {// x�j�󵥩�1.0f��
                        x = 1.0f;// x����1.0f
                        flag = true;// ��flag�����Ȭ�true
                    }
                }
                alpha = AlphaComposite.SrcOver.derive(x);// ���s��o��ܳz���ת�AlphaComposite�ﹳ
                repaint();// �I�spaint()��k
                try {
                    Thread.sleep(200);// ��v200�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
