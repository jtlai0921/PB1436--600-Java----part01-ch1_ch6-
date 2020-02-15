package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FiveDaisyChainFrame extends JFrame {
    FiveDaisyChainPanel fivePanel = new FiveDaisyChainPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        FiveDaisyChainFrame frame = new FiveDaisyChainFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public FiveDaisyChainFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s�����Ϯ�"); // ������D
        setBounds(100, 100, 269, 222); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(fivePanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class FiveDaisyChainPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) {     // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            BasicStroke stroke = new BasicStroke(3); // �إ߼e�׬O3�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            Color color = new Color(0,162,232);// �إ��C��ﹳ
            g2.setColor(color);// �]�w�C��
            g2.drawOval(30, 40, 60, 60);     // ø�s�Ĥ@�Ӷ�
            color = new Color(233,123,16);   // �إ߷s���C��ﹳ
            g2.setColor(color);// �]�w�C��
            g2.drawOval(60, 70, 60, 60);     // ø�s�ĤG�Ӷ�
            color = new Color(28,20,100);// �إ߷s���C��ﹳ
            g2.setColor(color);// �]�w�C��
            g2.drawOval(92, 40, 60, 60);     // ø�s�ĤT�Ӷ�
            color = new Color(0,255,0);// �إ߷s���C��ﹳ
            g2.setColor(color);// �]�w�C��
            g2.drawOval(122, 70, 60, 60);    // ø�s�ĥ|�Ӷ�
            color = new Color(255,0,0);// �إ߷s���C��ﹳ
            g2.setColor(color);// �]�w�C��
            g2.drawOval(154, 40, 60, 60);    // ø�s�Ĥ��Ӷ�
        }
    }
}
