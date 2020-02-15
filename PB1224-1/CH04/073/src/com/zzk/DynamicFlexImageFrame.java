package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DynamicFlexImageFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private DynamicFlexPanel imagePanel = null; // �ŧi�ϧέ��O�ﹳ
    private Thread thread = null;// �ŧi�u�{�ﹳ
    
    public static void main(String args[]) {
        DynamicFlexImageFrame frame = new DynamicFlexImageFrame();
        frame.setVisible(true);
    }
    
    public DynamicFlexImageFrame() {
        super();
        URL imgUrl = DynamicFlexImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imagePanel = new DynamicFlexPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 340, 200); // �]�w����j�p�M��m
        this.add(imagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        thread = new Thread(imagePanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��ʺA���i"); // �]�w������D
    }
    
    // �إ߭��O���O
    class DynamicFlexPanel extends JPanel implements Runnable {
        private boolean flag = true;// �аO�ܼ�
        int width = 0;// �վ�ϧμe�ת��ܼ�
        int height = 0;// �վ�ϧΰ��ת��ܼ�
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �M��ø�ϤW�U�媺���e
            g.drawImage(img, 0, 0, width, height, this); // ø�s���w�j�p���Ϥ�
        }
        public void run() {
            while (true) {
                if (flag) {
                    width+=2;// �վ�e�׭�
                    height++;// �վ㰪�׭�
                    if (width >= getWidth() || height >= getHeight()) {
                        flag = false;// �F��ϧΪ��e�שΰ��׮ɡA���ܼаO�ܼƪ���
                    }
                } else {
                    width -= 2;// �վ�e�׭�
                    height--;// �վ㰪�׭�
                    if (width <= 0 || height <= 0) {
                        flag = true;// �ϧΪ��e�שΰ��פp�󵥩�0�ɡA���ܼаO�ܼƪ���
                    }
                }
                repaint();// �I�spaint()��k
                try {
                    Thread.sleep(20);// �u�{��v20�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
