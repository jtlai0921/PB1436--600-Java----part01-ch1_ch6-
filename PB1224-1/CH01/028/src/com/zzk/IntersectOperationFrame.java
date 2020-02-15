package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IntersectOperationFrame extends JFrame {
    IntersectOperationPanel intersectOperationPanel = new IntersectOperationPanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        IntersectOperationFrame frame = new IntersectOperationFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public IntersectOperationFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�ϧΪ���B��"); // ������D
        setBounds(100, 100, 395, 240); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(intersectOperationPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class IntersectOperationPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) {    // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            Rectangle2D.Float rect = new Rectangle2D.Float(30, 30, 160, 120);// �إ߯x�ιﹳ
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20, 30, 180, 180);// �إ߶�ﹳ
            Area area1 = new Area(rect);   // �إ߰ϰ�x��
            Area area2 = new Area(ellipse);   // �إ߰ϰ��
            area1.intersect(area2);// ��Ӱϰ�ϧζi���B��
            g2.draw(area1);  // ø�s��B��᪺�ϰ�ϧ�
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(190, 20, 100, 140);// �إ߾��ﹳ
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(240, 20, 100, 140);// �إ߾��ﹳ
            Area area3 = new Area(ellipse1);// �إ߰ϰ���
            Area area4 = new Area(ellipse2);// �إ߰ϰ���
            area3.intersect(area4);// ��Ӱϰ���i���B��
            g2.fill(area3);  // ø�s��B��᪺�ϰ���
        }
    }
}
