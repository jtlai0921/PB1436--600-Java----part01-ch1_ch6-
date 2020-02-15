package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextZoomFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private TextZoomPanel textZoomPanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        TextZoomFrame frame = new TextZoomFrame();
        frame.setVisible(true);
    }
    
    public TextZoomFrame() {
        super();
        URL imgUrl = TextZoomFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        textZoomPanel = new TextZoomPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 376, 237); // �]�w����j�p�M��m
        this.add(textZoomPanel); // �b����W�W�[�ϧέ��O�ﹳ
        Thread thread = new Thread(textZoomPanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("��r�Y��S��"); // �]�w������D
    }
    
    // �إ߭��O���O
    class TextZoomPanel extends JPanel implements Runnable {
        boolean flag = false;// �w�q�аO�ܼơA�Ω󱱨�x����
        int x = 12;// �w�q��ܦr��j�p���ܼ�x
        Font font = new Font("�ؤ巢��", Font.BOLD, x);// �إߦr��ﹳ
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ø�s�ϧ�
            g2.setFont(font);// ���w�r��
            g2.setColor(Color.RED);// ���w�C��
            g2.drawString("�s�{����", 30, 120);// ø�s�¤�r
        }
        public void run() {
            while (true) {
                if (flag) {        // flag��true��
                    x-=1;       // x�i���1�p��
                    if (x <= 12) {// x�p�󵥩�12��
                        x = 12;   // x����12
                        flag = false;// ��flag�����Ȭ�false
                    }
                } else {// flag��false��
                    x+=1;// x�i��[1�p��
                    if (x >= 72) {// x�j�󵥩�72��
                        x = 72;// x����72
                        flag = true;// ��flag�����Ȭ�true
                    }
                }
                font = new Font("�ؤ巢��", Font.BOLD, x);// ���s�إߦr��ﹳ
                repaint();// �I�spaint()��k
                try {
                    Thread.sleep(50);// ��v50�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
