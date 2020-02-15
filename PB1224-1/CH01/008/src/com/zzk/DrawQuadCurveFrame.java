package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawQuadCurveFrame extends JFrame {
    DrawQuadCurvePanel quadCurvePanel = new DrawQuadCurvePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawQuadCurveFrame frame = new DrawQuadCurveFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawQuadCurveFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�G�����u"); // ������D
        setBounds(100, 100, 269, 175); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(quadCurvePanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawQuadCurvePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2=(Graphics2D)g;// ��oGraphics2D�ﹳ
            // �إߤG�����u�A�䤤�I120,100�O�����I�A�I60,20�O�_�l�I�y�СA�I180,20�O���I�y��
            QuadCurve2D.Double quadCurve1 = new QuadCurve2D.Double(60,20,120,100,180,20);
            g2.draw(quadCurve1); // ø�s�G�����u
            // �إߤG�����u�A�䤤�I120,40�O�����I�A�I60,120�O�_�l�I�y�СA�I180,120�O���I�y��
            QuadCurve2D.Double quadCurve2 = new QuadCurve2D.Double(60,120,120,40,180,120);
            g2.draw(quadCurve2); // ø�s�G�����u
        }
    }
}
