package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawEllipseFrame extends JFrame {
    DrawEllipsePanel ellipsePanel = new DrawEllipsePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawEllipseFrame frame = new DrawEllipseFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawEllipseFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s���"); // ������D
        setBounds(100, 100, 269, 222); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(ellipsePanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawEllipsePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) {     // ���s�w�qpaint()��k
            g.drawOval(30, 20, 80, 50);     // ø�s�Ť߾��
            g.drawOval(150, 10, 50, 80);    // ø�s�Ť߾��
            g.fillOval(40, 90, 50, 80);     // ø�s��߾��
            g.fillOval(140, 110, 80, 50);   // ø�s��߾��
        }
    }
}
