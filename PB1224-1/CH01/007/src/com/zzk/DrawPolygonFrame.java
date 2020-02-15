package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPolygonFrame extends JFrame {
    DrawPolygonPanel polygonPanel = new DrawPolygonPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawPolygonFrame frame = new DrawPolygonFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawPolygonFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�h���"); // ������D
        setBounds(100, 100, 352, 259); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(polygonPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawPolygonPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            int[] x1 = { 100,120,180,140,150,100,50,60,20,80 }; // �h��Ϊ���y��
            int[] y1 = { 20,85,90,120,180,140,180,120,90,85 }; // �h��Ϊ��a�y��
            int n1 = 10;// �h��Ϊ����
            g.fillPolygon(x1, y1, n1);// ø�s�h���
            int[] x2 = { 210, 270, 310, 270, 210, 170 }; // �h��Ϊ���y��
            int[] y2 = { 20, 20, 65, 110, 110, 65 }; // �h��Ϊ��a�y��
            int n2 = 6;// �h��Ϊ����
            g.fillPolygon(x2, y2, n2);// ø�s��ߦh���
            int[] x3 = { 180, 220, 260, 240, 260, 220, 180, 200 }; // �h��Ϊ���y��
            int[] y3 = { 120, 140, 120, 160, 200, 180, 200, 160 }; // �h��Ϊ��a�y��
            int n3 = 8;// �h��Ϊ����
            g.drawPolygon(x3, y3, n3);// ø�s�h���
        }
    }
}
