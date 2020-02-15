package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawRectangleFrame extends JFrame {
    DrawRectanglePanel rectPanel = new DrawRectanglePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawRectangleFrame frame = new DrawRectangleFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawRectangleFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�x��"); // ������D
        setBounds(100, 100, 269, 184); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(rectPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawRectanglePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) {       // ���s�w�qpaint()��k
            g.drawRect(30, 40, 80, 60);       // ø�s�Ť߯x��
            g.fillRect(140, 40, 80, 60);      // ø�s��߯x��
        }
    }
}
