package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CaptionSpecificFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private CaptionSpecificPanel captionSpecificPanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        CaptionSpecificFrame frame = new CaptionSpecificFrame();
        frame.setVisible(true);
    }
    
    public CaptionSpecificFrame() {
        super();
        URL imgUrl = CaptionSpecificFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        captionSpecificPanel = new CaptionSpecificPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(captionSpecificPanel); // �b����W�W�[�ϧέ��O�ﹳ
        Thread thread = new Thread(captionSpecificPanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�r����ܯS��"); // �]�w������D
    }
    
    // �إ߭��O���O
    class CaptionSpecificPanel extends JPanel implements Runnable {
        int x = 50;// �x�sø�s�I��x�y��
        int y = 216;// �x�sø�s�I��y�y��
        String value = "����s�{������}";// �x�sø�s�����e
        public void paint(Graphics g) {
            g.clearRect(0, 0, 316, 237);// �M��ø�ϤW�U�媺���e
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ø�s�ϧ�
            Font font = new Font("�ؤ巢��", Font.BOLD, 20);// �إߦr��ﹳ
            g.setFont(font);// ���w�r��
            g.setColor(Color.RED);// ���w�C��
            g.drawString(value, x, y);// ø�s�¤�r
        }
        public void run() {
            try {
                while (true) { // Ū�����e
                    Thread.sleep(100); // �ثe�u�{��v1��
                    if (y <= 216 - 50) {// �p�G�w�g�V�W����50����
                        y = 216;// y�y�Щw���̤U��
                        if (value.equals("����s�{������}")) {
                            value = "http://www.mrbccd.com";// ����ø�s�����e
                        } else {
                            value = "����s�{������}";// ����ø�s�����e
                        }
                    } else {// �p�G�٨S�V�W���ʨ�50����
                        y -= 2;// y�y�ФW��
                    }
                    repaint();// �I�spaint()��k
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
