package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArtDesignFrame extends JFrame {
    ArtDesignPanel artDesignPanel = new ArtDesignPanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        ArtDesignFrame frame = new ArtDesignFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public ArtDesignFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s���N�Ϯ�"); // ������D
        setBounds(100, 100, 338, 230); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(artDesignPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class ArtDesignPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) {     // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            Ellipse2D.Float ellipse = new Ellipse2D.Float(-80, 5, 160, 10);// �إ߾��ﹳ
            Random random = new Random();// �إ��H���ƹﹳ
            g2.translate(160, 90);// �����y�жb
            int R = random.nextInt(256);//�H�������C�⪺R��
            int G = random.nextInt(256);//�H�������C�⪺G��
            int B = random.nextInt(256);//�H�������C�⪺B��
            Color color = new Color(R,G,B);//�إ��C��ﹳ
            g2.setColor(color);//���w�C��
            g2.draw(ellipse);// ø�s���
            int i=0;
            while (i<100){
                R = random.nextInt(256);//�H�������C�⪺R��
                G = random.nextInt(256);//�H�������C�⪺G��
                B = random.nextInt(256);//�H�������C�⪺B��
                color = new Color(R,G,B);//�إ߷s���C��ﹳ
                g2.setColor(color);//���w�C��
                g2.rotate(10);// ����e��
                g2.draw(ellipse);// ø�s���
                i++;
            }
        }
    }
}
