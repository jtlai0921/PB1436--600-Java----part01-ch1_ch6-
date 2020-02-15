package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SolidTextFrame extends JFrame {
    private SolidTextPanel solidTextPanel = new SolidTextPanel();
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SolidTextFrame frame = new SolidTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public SolidTextFrame() {
        super();
        setBounds(100, 100, 354, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("����ĪG����r");
        getContentPane().add(solidTextPanel);
    }
    class SolidTextPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            String value = "������";
            int x = 16; // �¤�r��m����y��
            int y = 100; // �¤�r��m���a�y��
            Font font = new Font("�ө���", Font.BOLD, 72); // �إߦr��ﹳ
            g.setFont(font); // �]�w�r��
            g.setColor(Color.GRAY);// �]�w�C�⬰�Ǧ�
            int i = 0;// �`���ܼ�
            while (i<8){
                g.drawString(value, x, y); // ø�s�¤�r
                x+=1;// �վ�ø�s�I����y�Э�
                y+=1;// �վ�ø�s�I���a�y�Э�
                i++;// �վ�`���ܼƪ���
            }
            g.setColor(Color.BLACK);// �]�w�C��¦�
            g.drawString(value, x, y); // ø�s�¤�r
        }
    }
}
