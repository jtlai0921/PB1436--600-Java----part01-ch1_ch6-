package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RollAdvertisementFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private RollAdvertisementPanel rollAdvertisementPanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        RollAdvertisementFrame frame = new RollAdvertisementFrame();
        frame.setVisible(true);
    }
    
    public RollAdvertisementFrame() {
        super();
        URL imgUrl = RollAdvertisementFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        rollAdvertisementPanel = new RollAdvertisementPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(rollAdvertisementPanel); // �b����W�W�[�ϧέ��O�ﹳ
        Thread thread = new Thread(rollAdvertisementPanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("���ʼs�i�r��"); // �]�w������D
    }
    
    // �إ߭��O���O
    class RollAdvertisementPanel extends JPanel implements Runnable {
        int x = 316;// �x�sø�s�I��x�y��
        int y = 190;// �x�sø�s�I��y�y��
        String value = "����s�{������}�Ghttp://www.mrbccd.com";// �x�sø�s�����e
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
                    Thread.sleep(50); // �ثe�u�{��v1��
                    if (x <= -400) {// �ӱ���i�H�ھڻݭn�ۦ�վ�
                        x = 316;// x�y�Щw���̥k��
                    } else {
                        x -= 2;// x�y�Х���
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