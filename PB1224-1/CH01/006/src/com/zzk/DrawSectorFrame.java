package com.zzk;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawSectorFrame extends JFrame {
    DrawSectorPanel sectorPanel = new DrawSectorPanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        DrawSectorFrame frame = new DrawSectorFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    public DrawSectorFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s��R����"); // ������D
        setBounds(100, 100, 278, 184); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(sectorPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    class DrawSectorPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            g.fillArc(40, 20, 80, 80, 0, 150);    // ø�s��R����
            g.fillArc(140, 20, 80, 80, 180, -150);// ø�s��R����
            g.fillArc(40, 40, 80, 80, 0, -110);   // ø�s��R����
            g.fillArc(140, 40, 80, 80, 180, 110); // ø�s��R����
        }
    }
}
