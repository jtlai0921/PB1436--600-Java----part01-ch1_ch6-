package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DashedModelFrame extends JFrame {
    DashedModelPanel dashedModelPanel = new DashedModelPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DashedModelFrame frame = new DashedModelFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DashedModelFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�]�w��u�Ҧ�"); // ������D
        setBounds(100, 100, 326, 220); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(dashedModelPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DashedModelPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            float[] arr = {30.0f,30.0f};  // �إߵ�u�Ҧ����}�C
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,1.0f,arr,0); // �إ߼e�׬O10�����Y��u���e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            Line2D.Float line = new Line2D.Float(20,50,300,50);// �إߪ��u�ﹳ
            g2.drawString("���Y�˦���u�Ҧ�", 110, 40);  // ø�s�¤�r
            g2.draw(line);// ø�s���u
            stroke = new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL,1.0f,arr,0); // �إ߼e�׬O10�����Y��u���e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            line = new Line2D.Float(20,90,300,90);// �إߪ��u�ﹳ
            g2.drawString("���Y�˦���u�Ҧ�", 110, 80);  // ø�s�¤�r
            g2.draw(line);// ø�s���u
            stroke = new BasicStroke(10,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL,1.0f,arr,0); // �إ߼e�׬O10�����Y��u���e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            line = new Line2D.Float(20,130,300,130);// �إߪ��u�ﹳ
            g2.drawString("���Y�˦���u�Ҧ�", 110, 120);  // ø�s�¤�r
            g2.draw(line);// ø�s���u
        }
    }
}
