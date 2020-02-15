package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeStyleFrame extends JFrame {
    ChangeStrokeStylePanel strokeStylePanel = new ChangeStrokeStylePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        StrokeStyleFrame frame = new StrokeStyleFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public StrokeStyleFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�]�w���e�˦�"); // ������D
        setBounds(100, 100, 306, 220); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(strokeStylePanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class ChangeStrokeStylePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); // �إ߼e�׬O10�����Y���e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            Line2D.Float line = new Line2D.Float(50,50,240,50);// �إߪ��u�ﹳ
            g2.drawString("���Y�˦�", 120, 40);  // ø�s�¤�r
            g2.draw(line);// ø�s���u
            stroke = new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL); // �إ߼e�׬O10�����Y���e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            line = new Line2D.Float(50,90,240,90);// �إߪ��u�ﹳ
            g2.drawString("���Y�˦�", 120, 80);  // ø�s�¤�r
            g2.draw(line);// ø�s���u
            stroke = new BasicStroke(10,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL); // �إ߼e�׬O10�����Y���e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            line = new Line2D.Float(50,130,240,130);// �إߪ��u�ﹳ
            g2.drawString("���Y�˦�", 120, 120);  // ø�s�¤�r
            g2.draw(line);// ø�s���u
        }
    }
}
