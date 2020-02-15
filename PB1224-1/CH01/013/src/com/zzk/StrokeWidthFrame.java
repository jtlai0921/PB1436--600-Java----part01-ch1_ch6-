package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeWidthFrame extends JFrame {
    ChangeStrokeWidthPanel strokeWidthPanel = new ChangeStrokeWidthPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        StrokeWidthFrame frame = new StrokeWidthFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public StrokeWidthFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�]�w���e���ʲ�"); // ������D
        setBounds(100, 100, 300, 220); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(strokeWidthPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class ChangeStrokeWidthPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            BasicStroke stroke = new BasicStroke(1); // �إ߼e�׬O1�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20,20,100,60);// �إ߾��ﹳ
            g2.draw(ellipse);// ø�s���
            stroke = new BasicStroke(4);// �إ߼e�׬O4�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            ellipse = new Ellipse2D.Float(160,20,100,60);// �إ߾��ﹳ
            g2.draw(ellipse);// ø�s���
            stroke = new BasicStroke(6);// �إ߼e�׬O6�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            ellipse = new Ellipse2D.Float(20,100,100,60);// �إ߾��ﹳ
            g2.draw(ellipse);// ø�s���
            stroke = new BasicStroke(8);// �إ߼e�׬O8�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            ellipse = new Ellipse2D.Float(160,100,100,60);// �إ߾��ﹳ
            g2.draw(ellipse);// ø�s���
        }
    }
}
