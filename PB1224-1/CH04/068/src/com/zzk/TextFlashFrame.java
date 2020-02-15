package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextFlashFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private TextFlashPanel textFlashPanel = null; // �ŧi�ϧέ��O�ﹳ
    public static void main(String args[]) {
        TextFlashFrame frame = new TextFlashFrame();
        frame.setVisible(true);
    }
    
    public TextFlashFrame() {
        super();
        URL imgUrl = TextFlashFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        textFlashPanel = new TextFlashPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 310, 230); // �]�w����j�p�M��m
        this.add(textFlashPanel); // �b����W�W�[�ϧέ��O�ﹳ
        Thread thread = new Thread(textFlashPanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("��r�{�{�S��"); // �]�w������D
    }
    
    // �إ߭��O���O
    class TextFlashPanel extends JPanel implements Runnable {
        boolean flag = true;// �аO�ܼ�
        String value = "";// �s��ø�s���e���ܼ�
        public void paint(Graphics g) {
            g.clearRect(0, 0, 310, 230);// �M��ø�ϤW�U�媺���e
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// ø�s�ϧ�
            Font font = new Font("�ؤ巢��", Font.BOLD, 42);// �إߦr��ﹳ
            g.setFont(font);// ���w�r��
            g.setColor(Color.RED);// ���w�C��
            g.drawString(value, 10, 110);// ø�s�¤�r
            
        }
        public void run() {
            try {
                while (true) { // Ū�����e
                    Thread.sleep(150); // �ثe�u�{��v1��
                    if (flag) {// flag��true
                        flag = false;   // �����Ȭ�false
                        value="����s�{����";// ��value������
                    } else {
                        flag = true;// �����Ȭ�true
                        value="";// �����Ȭ��Ŧr��
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