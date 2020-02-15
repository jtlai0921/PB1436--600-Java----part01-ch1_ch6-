package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextFadeFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private TextFadePanel textFadePanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        TextFadeFrame frame = new TextFadeFrame();
        frame.setVisible(true);
    }
    
    public TextFadeFrame() {
        super();
        URL imgUrl = TextFadeFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        textFadePanel = new TextFadePanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(textFadePanel); // �b����W�W�[�ϧέ��O�ﹳ
        Thread thread = new Thread(textFadePanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("��r�H�J�H�X�S��"); // �]�w������D
    }
    
    // �إ߭��O���O
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// �w�q�аO�ܼơA�Ω󱱨�x����
        float x = 0.0f;// �w�q��ܳz���ת��ܼ�x
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(x);// ��o��ܳz���ת�AlphaComposite�ﹳ
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// ø�s�ϧ�
            Font font = new Font("�ؤ巢��", Font.BOLD, 60);// �إߦr��ﹳ
            g2.setFont(font);// ���w�r��
            g2.setColor(Color.RED);// ���w�C��
            g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
            g2.drawString("�s�{����", 30, 120);// ø�s�¤�r
        }
        ////// �`�N�B�I�Ƭ۴�A�p�⵲�G����T�A�ҥH�[�Wif (x <= 0.0f) { x = 0.0f;
        public void run() {
            while (true) {
                if (flag) {        // flag��true��
                    x-=0.1f;       // x�i���0.1�p��
                    if (x <= 0.0f) {// x�p�󵥩�0.0f��
                        x = 0.0f;   // x����0.0f
                        flag = false;// ��flag�����Ȭ�false
                    }
                } else {// flag��false��
                    x+=0.1f;// x�i��[0.1�p��
                    if (x >= 1.0f) {// x�j�󵥩�1.0f��
                        x = 1.0f;// x����1.0f
                        flag = true;// ��flag�����Ȭ�true
                    }
                }
                alpha = AlphaComposite.SrcOver.derive(x);// ���s��o��ܳz���ת�AlphaComposite�ﹳ
                repaint();// �I�spaint()��k
                try {
                    Thread.sleep(150);// ��v150�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
