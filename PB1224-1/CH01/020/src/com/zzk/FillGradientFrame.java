package com.zzk;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FillGradientFrame extends JFrame {
    FillGradientPanel fillGradientPanel = new FillGradientPanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        FillGradientFrame frame = new FillGradientFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public FillGradientFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("���ϧζ�R���h��"); // ������D
        setBounds(100, 100, 338, 220); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(fillGradientPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class FillGradientPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g; // ��oGraphics2D�ﹳ
            Rectangle2D.Float rect = new Rectangle2D.Float(20, 20, 280, 140);// �إ߯x�ιﹳ
            // �إߴ`�����h��GraphientPaint�ﹳ
            GradientPaint paint = new GradientPaint(20,20,Color.BLUE,100,80,Color.RED,true);
            g2.setPaint(paint);// �]�w���h
            g2.fill(rect);// ø�s�x��
        }
    }
}
