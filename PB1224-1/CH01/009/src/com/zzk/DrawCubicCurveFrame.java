package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCubicCurveFrame extends JFrame {
    DrawCubicCurvePanel cubicCurvePanel = new DrawCubicCurvePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawCubicCurveFrame frame = new DrawCubicCurveFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawCubicCurveFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�T�����u"); // ������D
        setBounds(100, 100, 297, 199); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(cubicCurvePanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawCubicCurvePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2=(Graphics2D)g;// ��oGraphics2D�ﹳ
            // �إߤT�����u�A�䤤�I140,-140�M�I140,300�O�����I�A�I20,80�O�_�l�I�y�СA�I260,80�O���I�y��
            CubicCurve2D.Double cubicCurve = new CubicCurve2D.Double(20,80,140,-140,140,300,260,80);
            g2.draw(cubicCurve); // ø�s�T�����u
        }
    }
}
