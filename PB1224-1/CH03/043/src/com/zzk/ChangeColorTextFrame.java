package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ChangeColorTextFrame extends JFrame {
    private ChangeColorTextPanel changeColorTextPanel = new ChangeColorTextPanel();
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangeColorTextFrame frame = new ChangeColorTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ChangeColorTextFrame() {
        super();
        setBounds(100, 100, 400, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("�|�ܦ⪺��r");
        getContentPane().add(changeColorTextPanel);
        Thread thread = new Thread(changeColorTextPanel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
    }
    
    class ChangeColorTextPanel extends JPanel implements Runnable { // �إߤ������O���O
        Color color = new Color(0,0,255);
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g;// �ରGraphics2D���A
            String value = "�m���T��Java�s�{�n";// ø�s���¤�r
            int x = 2; // �¤�r��m����y��
            int y = 90; // �¤�r��m���a�y��
            Font font = new Font("����", Font.BOLD, 40); // �إߦr��ﹳ
            g2.setFont(font); // �]�w�r��
            g2.setColor(color);// �]�w�C��
            g2.drawString(value, x, y); // ø�s�¤�r
        }
        public void run() {
            Random random = new Random();// �إ��H���ƹﹳ
            while(true){
                int R = random.nextInt(256);// �H�������C�⪺R��
                int G = random.nextInt(256);// �H�������C�⪺G��
                int B = random.nextInt(256);// �H�������C�⪺B��
                color = new Color(R,G,B);// �إ��C��ﹳ
                repaint();// �I�spaint()��k
                try {
                    Thread.sleep(300);// ��v300�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
