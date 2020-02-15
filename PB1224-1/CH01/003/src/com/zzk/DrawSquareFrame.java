package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSquareFrame extends JFrame {
    DrawSquarePanel squarePanel = new DrawSquarePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawSquareFrame frame = new DrawSquareFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawSquareFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�����"); // ������D
        setBounds(100, 100, 280, 180); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(squarePanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawSquarePanel extends JPanel {// �إߤ������O���O
        public void paint(Graphics g) {   // ���s�w�qpaint()��k
            g.drawRect(20, 20, 100, 100); // ø�s�Ťߥ����
            g.drawRect(40, 40, 60, 60);   // ø�s�Ťߥ����
            g.drawRect(140, 20, 100, 100);   // ø�s�Ťߥ����
            g.fillRect(160, 40, 60, 60);  // ø�s��ߥ����
        }
    }
}
