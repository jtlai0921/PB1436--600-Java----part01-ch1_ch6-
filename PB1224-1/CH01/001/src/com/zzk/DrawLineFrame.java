package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawLineFrame extends JFrame {
    DrawLinePanel linePanel = new DrawLinePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawLineFrame frame = new DrawLineFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawLineFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s���u"); // ������D
        setBounds(100, 100, 273, 167); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(linePanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawLinePanel extends JPanel {   // �إߤ������O���O
        public void paint(Graphics g) {    // ���s�w�qpaint()��k
            g.drawLine(70, 50, 180, 50);   // ø�s�Ĥ@�������u
            g.drawLine(70, 80, 180, 80);   // ø�s�ĤG�������u
            g.drawLine(110, 10, 140, 120); // ø�s�׽u
        }
    }
}
