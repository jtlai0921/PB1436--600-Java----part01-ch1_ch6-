package com.zzk;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class TextFontFrame extends JFrame {
    ChangeTextFontPanel changeTextFontPanel = new ChangeTextFontPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        TextFontFrame frame = new TextFontFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    public TextFontFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�]�w�¤�r���r��"); // ������D
        setBounds(100, 100, 333, 199); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(changeTextFontPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    class ChangeTextFontPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            String value = "����s�{�������";
            int x = 40; // �¤�r��m����y��
            int y = 50; // �¤�r��m���a�y��
            Font font = new Font("�ؤ�淢", Font.BOLD + Font.ITALIC, 26); // �إߦr��ﹳ
            g.setFont(font); // �]�w�r��
            g.drawString(value, x, y); // ø�s�¤�r
            value = "http://community.mrbccd.com";
            x = 10; // �¤�r��m����y��
            y = 100; // �¤�r��m���a�y��
            font = new Font("�ө���", Font.BOLD, 20); // �إߦr��ﹳ
            g.setFont(font); // �]�w�r��
            g.drawString(value, x, y); // ø�s�¤�r
        }
    }
}
