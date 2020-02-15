package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCachetFrame extends JFrame {
    DrawCachetPanel drawCachetPanel = new DrawCachetPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawCachetFrame frame = new DrawCachetFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawCachetFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s����"); // ������D
        setBounds(100, 100, 340, 240); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(drawCachetPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawCachetPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g; // ��oGraphics2D�ﹳ
            g2.translate(170, 100);// �����y�жb
            BasicStroke stroke = new BasicStroke(6); // �إ߼e�׬O6�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            // ø�s��
            Ellipse2D.Float ellipse = new Ellipse2D.Float(-80, -80, 160, 160);// �إ߶�ﹳ
            Color color = new Color(255, 0, 0);// �إ��C��ﹳ
            g2.setColor(color);// ���w�C��
            g2.draw(ellipse);// ø�s��
            // ø�s���P
            int[] x1 = { 0, 8, 30, 16, 25, 0, -25, -16, -30, -8 }; // �h��Ϊ���y��
            int[] y1 = { -35, -10, -15, 5, 28, 10, 28, 5, -15, -10 }; // �h��Ϊ��a�y��
            int n1 = 10;// �h��Ϊ����
            g2.fillPolygon(x1, y1, n1);// ø�s�h���
            // ø�s�¤�r
            g2.scale(1.8, 1.8);// ��j
            Font font = new Font("�ө���", Font.BOLD, 12);// �إߦr��
            g2.setFont(font);// �]�w�r��
            g2.drawString("�M �� ��", -25, 30);// ø�s�¤�r
            int width = getWidth();// ��o���O�e��
            int height = getHeight();// ��o���O����
            char[] array = " �����ަ������q         ".toCharArray();// ��r���ର�r�Ű}�C
            int len = array.length * 2;// �w�q�b�|
            font = new Font("�ө���", Font.BOLD, 10);// �إ߷s�r��
            g2.setFont(font);// �]�w�r��
            double angle = 0;// ��l����
            for (int i = 0; i < array.length; i++) {// �ˬd�r�ꤤ���r��
                int x = (int) (len * Math.sin(Math.toRadians(angle + 270)));// �p��C�Ӥ�r����m
                int y = (int) (len * Math.cos(Math.toRadians(angle + 270)));// �p��C�Ӥ�r����m
                g2.drawString(array[i] + "", width / 2 + x - 168, height / 2
                        - y - 95);// ø�s�C�Ӧr�šA�䤤168�M95�O�y�Х�����
                angle = angle + 360d / array.length;// ���ܨ���
            }
        }
    }
}
