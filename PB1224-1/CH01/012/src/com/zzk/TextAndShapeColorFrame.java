package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextAndShapeColorFrame extends JFrame {
    TextAndShapeColorPanel colorPanel = new TextAndShapeColorPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        TextAndShapeColorFrame frame = new TextAndShapeColorFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public TextAndShapeColorFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�]�w�¤�r�M�ϧΪ��C��"); // ������D
        setBounds(100, 100, 300, 193); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(colorPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class TextAndShapeColorPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            String value = "�u�n�V�O�X�X�X�X";
            int x = 60;    // �¤�r��m����y��
            int y = 60;     // �¤�r��m���a�y��
            Color color = new Color(255,0,0); // �إ��C��ﹳ
            g.setColor(color); // �]�w�C��
            g.drawString(value, x, y);   // ø�s�¤�r
            value = "�@���Ҧ��i��";
            x = 140;    // �¤�r��m����y��
            y = 100;     // �¤�r��m���a�y��
            color = new Color(0,0,255);// �إ��C��ﹳ
            g.setColor(color);// �]�w�C��
            g.drawString(value, x, y);   // ø�s�¤�r
            color = Color.ORANGE; // �z�LColor���O���r�q��o�C��ﹳ
            g.setColor(color);// �]�w�C��
            g.drawRoundRect(40,30,200,100,40,30);// ø�s�ꨤ�x��
            g.drawRoundRect(45,35,190,90,36,26);// ø�s�ꨤ�x��
        }
    }
}
