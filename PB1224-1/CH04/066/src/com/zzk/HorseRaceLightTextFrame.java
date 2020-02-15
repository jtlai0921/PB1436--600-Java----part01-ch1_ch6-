package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HorseRaceLightTextFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private HorseRaceLightTextPanel horseRaceLightTextPanel = new HorseRaceLightTextPanel();
    public static void main(String args[]) {
                    HorseRaceLightTextFrame frame = new HorseRaceLightTextFrame();
                    frame.setVisible(true);
    }
    
    public HorseRaceLightTextFrame() {
        super();
        setTitle("��r�]���O�S��");
        setBounds(200, 160, 360, 237);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = HorseRaceLightTextFrame.class
                .getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        getContentPane().add(horseRaceLightTextPanel);
        Thread thread = new Thread(horseRaceLightTextPanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
    }
    
    class HorseRaceLightTextPanel extends JPanel implements Runnable { // �إߤ������O���O
        String value = "����s�{����A�ڪ��ǲ߱M�a�C";// �x�sø�s�����e
        char[] drawChar = value.toCharArray();// �Nø�s���e�ର�r�Ű}�C
        int[] x = new int[drawChar.length];// �x�s�C�Ӧr��ø�s�Ix�y�Ъ��}�C
        int y = 100;// �x�sø�s�I��y�y��
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �M��ø�ϤW�U�媺���e
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ø�s�ϧ�
            Font font = new Font("�ؤ巢��", Font.BOLD, 20);// �إߦr��ﹳ
            g.setFont(font);// ���w�r��
            g.setColor(Color.RED);// ���w�C��
            for (int j = drawChar.length-1; j >=0; j--){
               g.drawString(drawChar[drawChar.length-1-j]+"",x[j] , y);// ø�s�¤�r
            }
        }
        public void run() {
            try {
                boolean flag = false;// ��false�ɪ�ܲĤ@������Ax�y�жi�浥�񻼼W�A�_�h�i�浥�t���W
                while (true) { // Ū�����e
                    Thread.sleep(300); // �ثe�u�{��v300�@��
                    for (int i = drawChar.length-1; i >=0 ; i--) {
                        if (!flag){
                            x[i]=x[i]+20*i;// x�y�жi�浥�񻼼W
                        } else {
                            x[i]=x[i]+20;// x�y�жi�浥�t���W
                        }
                        if (x[i]>=360 - 20){// �j����e�״�20���Ȯ�
                            x[i] = 0;       // x�y�Ь�0
                        }
                    }
                    repaint();// �I�spaint()��k
                    if (!flag) {
                        flag = true;// �����Ȭ�true
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
