package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GradientTextFrame extends JFrame {
    private GradientTextPanel gradientTextPanel = new GradientTextPanel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GradientTextFrame frame = new GradientTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public GradientTextFrame() {
        super();
        setBounds(100, 100, 365, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("���h�ĪG����r");
        getContentPane().add(gradientTextPanel);
    }
    
    class GradientTextPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g;// �ରGraphics2D���A
            String value = "Java����";// ø�s���¤�r
            int x = 15; // �¤�r��m����y��
            int y = 60; // �¤�r��m���a�y��
            Font font = new Font("����", Font.BOLD, 60); // �إߦr��ﹳ
            g2.setFont(font); // �]�w�r��
            // �إߴ`�����h��GraphientPaint�ﹳ
            GradientPaint paint = new GradientPaint(20, 20, Color.BLUE, 100,120, Color.RED, true);
            g2.setPaint(paint);// �]�w���h
            g2.drawString(value, x, y); // ø�s�¤�r
            font = new Font("�ؤ�淢", Font.BOLD, 60); // �إ߷s���r��ﹳ
            g2.setFont(font); // �]�w�r��
            x = 80; // �¤�r��m����y��
            y = 130; // �¤�r��m���a�y��
            value = "�s�{����";// ø�s���¤�r
            g2.drawString(value, x, y); // ø�s�¤�r
        }
    }
}
