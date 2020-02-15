package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShadowTextFrame extends JFrame {
    private ShadowTextPanel shadowTextPanel = new ShadowTextPanel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShadowTextFrame frame = new ShadowTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ShadowTextFrame() {
        super();
        setBounds(100, 100, 354, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("���v�ĪG����r");
        getContentPane().add(shadowTextPanel);
    }
    
    class ShadowTextPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            String value = "�s�{����";
            int x = 16; // �¤�r��m����y��
            int y = 100; // �¤�r��m���a�y��
            Font font = new Font("�ؤ�淢", Font.BOLD, 72); // �إߦr��ﹳ
            g.setFont(font); // �]�w�r��
            g.setColor(Color.GRAY);// �]�w�C�⬰�Ǧ�
            int i = 0;// �`���ܼ�
            g.drawString(value, x, y); // ø�s�¤�r
            x -= 3;// �վ�ø�s�I����y�Э�
            y -= 3;// �վ�ø�s�I���a�y�Э�
            g.setColor(Color.BLACK);// �]�w�C��¦�
            g.drawString(value, x, y); // ø�s�¤�r
        }
    }
}
