package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RandomMovePictureFrame extends JFrame {
    private final int winWIDTH = 450;
    private final int winHEIGHT = 300;
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RandomMovePictureFrame frame = new RandomMovePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public RandomMovePictureFrame() {
        super();
        setTitle("�H�����ʪ��Ϥ�");
        setBounds(100, 100, winWIDTH, winHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RandomMovePicturePanel panel = new RandomMovePicturePanel();// �إ߭��O�ﹳ
        getContentPane().add(panel);// �N���O�W�[�쵡��e��
        Thread thread = new Thread(panel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
    }
    
    /**
     * @author zzk
     * �إ߹�{Runnable���f���������O���O
     */
    private class RandomMovePicturePanel extends JPanel implements Runnable {
        Random random = new Random();// �إ�Random�ﹳ
        int x = 0;// �w�q�Ϥ����ʦ�m��x�y��
        int y = 0;// �w�q�Ϥ����ʦ�m��y�y��
        URL imgUrl = RandomMovePictureFrame.class
                .getResource("/image/picture.png");// ��o�Ϥ��귽�����|
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl);// ��o�ϧιﹳ
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �M�����O�W�����e
            g.drawImage(img, x, y, this);// �b���O�����w��mø�s�ϧ�
        }
        public void run() {
            while (true) {
                x = random.nextInt(winWIDTH - 110);// �H����o�Ϥ����ʦ�m��x�y��
                y = random.nextInt(winHEIGHT - 140);// �H����o�Ϥ����ʦ�m��y�y��
                try {
                    Thread.sleep(200);// ��v200�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// �I�spaint()��k
            }
        }
    }
}
