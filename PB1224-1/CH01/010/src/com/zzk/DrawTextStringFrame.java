package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTextStringFrame extends JFrame {
    DrawTextStringPanel textStringPanel = new DrawTextStringPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        DrawTextStringFrame frame = new DrawTextStringFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawTextStringFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�¤�r"); // ������D
        setBounds(100, 100, 308, 186); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(textStringPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawTextStringPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            String value = "�R�]��";
            int x = 120;  // �¤�r��m����y��
            int y = 30;  // �¤�r��m���a�y��
            g.drawString(value, x, y);   // ø�s�¤�r
            value = "����";
            x = 170;  // �¤�r��m����y��
            y = 50;  // �¤�r��m���a�y��
            g.drawString(value, x, y);   // ø�s�¤�r
            value = "�ɫe������A";
            x = 70;  // �¤�r��m����y��
            y = 80;  // �¤�r��m���a�y��
            g.drawString(value, x, y);   // ø�s�¤�r
            value = "�ìO�a�W���C";
            x = 145;  // �¤�r��m����y��
            y = 80;  // �¤�r��m���a�y��
            g.drawString(value, x, y);   // ø�s�¤�r
            value = "�|�Y�����A";
            x = 70;  // �¤�r��m����y��
            y = 110;  // �¤�r��m���a�y��
            g.drawString(value, x, y);   // ø�s�¤�r
            value = "�C�Y��G�m�C";
            x = 145;  // �¤�r��m����y��
            y = 110;  // �¤�r��m���a�y��
            g.drawString(value, x, y);   // ø�s�¤�r
        }
    }
}
