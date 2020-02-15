package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlusOperationFrame extends JFrame {
    PlusOperationPanel plusOperationPanel = new PlusOperationPanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        PlusOperationFrame frame = new PlusOperationFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public PlusOperationFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�ϧΪ��[�B��"); // ������D
        setBounds(100, 100, 395, 240); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(plusOperationPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class PlusOperationPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) {    // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(20, 70, 160, 60);// �إ߾��ﹳ
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(120, 20, 60, 160);// �إ߾��ﹳ
            Area area1 = new Area(ellipse1);   // �إ߰ϰ���
            Area area2 = new Area(ellipse2);   // �إ߰ϰ���
            area1.add(area2);// ��Ӱϰ���i��[�B��
            g2.draw(area1);  // ø�s�[�B��᪺�ϰ���
            Ellipse2D.Float ellipse3 = new Ellipse2D.Float(200, 70, 160, 60);// �إ߾��ﹳ
            Ellipse2D.Float ellipse4 = new Ellipse2D.Float(250, 20, 60, 160);// �إ߾��ﹳ
            Area area3 = new Area(ellipse3);// �إ߰ϰ���
            Area area4 = new Area(ellipse4);// �إ߰ϰ���
            area3.add(area4);// ��Ӱϰ���i��[�B��
            g2.draw(area3);  // ø�s�[�B��᪺�ϰ���
        }
    }
}
