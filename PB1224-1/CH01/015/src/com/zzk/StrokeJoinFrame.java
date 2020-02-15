package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeJoinFrame extends JFrame {
    ChangeStrokeJoinPanel strokeJoinPanel = new ChangeStrokeJoinPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        StrokeJoinFrame frame = new StrokeJoinFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public StrokeJoinFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�]�w�s���覡"); // ������D
        setBounds(100, 100, 338, 198); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(strokeJoinPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class ChangeStrokeJoinPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); // �إ߼e�׬O10�����Y�ר��s�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            Rectangle2D.Float rect = new Rectangle2D.Float(20,60,80,50);// �إ߯x�ιﹳ
            g2.drawString("�ר��s��", 35, 50);  // ø�s�¤�r
            g2.draw(rect);// ø�s�x��
            stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER); // �إ߼e�׬O10�����Y�y���s�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            rect = new Rectangle2D.Float(120,60,80,50);// �إ߯x�ιﹳ
            g2.drawString("�y���s��", 135, 50);  // ø�s�¤�r
            g2.draw(rect);// ø�s�x��
            stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND); // �إ߼e�׬O10�����Y�ꨤ�s�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            rect = new Rectangle2D.Float(220,60,80,50);// �إ߯x�ιﹳ
            g2.drawString("�ꨤ�s��", 235, 50);  // ø�s�¤�r
            g2.draw(rect);// ø�s�x��
        }
    }
}
