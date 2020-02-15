package com.zzk;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShearTextFrame extends JFrame {
    private ShearTextPanel shearTextPanel = new ShearTextPanel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShearTextFrame frame = new ShearTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ShearTextFrame() {
        super();
        setBounds(100, 100, 365, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("�ɱ׮ĪG����r");
        getContentPane().add(shearTextPanel);
    }
    
    class ShearTextPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g;// �ରGraphics2D���A
            String value = "�s�{����";// ø�s���¤�r
            int x = 10; // �¤�r��m����y��
            int y = 170; // �¤�r��m���a�y��
            Font font = new Font("�ؤ�淢", Font.BOLD, 72); // �إߦr��ﹳ
            g2.setFont(font); // �]�w�r��
            g2.shear(0.1, -0.4);// �ɱ׵e��
            g2.drawString(value, x, y); // ø�s�¤�r
        }
    }
}
