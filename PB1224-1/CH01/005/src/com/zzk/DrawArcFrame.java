package com.zzk;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawArcFrame extends JFrame {
    DrawArcPanel arcPanel = new DrawArcPanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        DrawArcFrame frame = new DrawArcFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    public DrawArcFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�꩷"); // ������D
        setBounds(100, 100, 269, 184); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(arcPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    class DrawArcPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            g.drawArc(20, 20, 80, 80, 0, 120);    // ø�s�꩷
            g.drawArc(20, 40, 80, 80, 0, -120);   // ø�s�꩷
            g.drawArc(150, 20, 80, 80, 180, -120);// ø�s�꩷
            g.drawArc(150, 40, 80, 80, 180, 120); // ø�s�꩷
        }
    }
}
