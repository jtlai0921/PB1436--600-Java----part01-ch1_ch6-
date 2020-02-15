package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureFadeFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private Image fadeImage = null;// �ŧi�{�{���ϧιﹳ
    private TextFadePanel textFadePanel = null; // �ŧi�ϧέ��O�ﹳ
    private URL imgUrl = null;// �ŧiURL�ﹳ
    public static void main(String args[]) {
        PictureFadeFrame frame = new PictureFadeFrame();
        frame.setVisible(true);
    }
    
    public PictureFadeFrame() {
        super();
        imgUrl = PictureFadeFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧιﹳ
        imgUrl = PictureFadeFrame.class.getResource("/img/fade.jpg");// ��o�Ϥ��귽�����|
        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧιﹳ
        textFadePanel = new TextFadePanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 310, 230); // �]�w����j�p�M��m
        this.add(textFadePanel); // �b����W�W�[�ϧέ��O�ﹳ
        Thread thread = new Thread(textFadePanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��{�{�ʵe"); // �]�w������D
    }
    
    // �إ߭��O���O
    class TextFadePanel extends JPanel implements Runnable {
        boolean flag = true;// �аO�ܼ�
        String value = "";// �s��ø�s���e���ܼ�
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(),getHeight());// �M��ø�ϤW�U�媺���e
            g.drawImage(img,0,0,getWidth(),getHeight(),this);// ø�s�ϧ�
            g.drawImage(fadeImage,10,10,getWidth()-20,getHeight()-20,this);// ø�s�{�{���ϧιﹳ
        }
        public void run() {
            try {
                while (true) { // Ū�����e
                    Thread.sleep(200); // �ثe�u�{��v200�@��
                    if (flag) {// flag��true
                        flag = false;   // �����Ȭ�false
                        fadeImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧιﹳ
                    } else {
                        flag = true;// �����Ȭ�true
                        fadeImage = Toolkit.getDefaultToolkit().getImage(""); // ��o�ϧιﹳ
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