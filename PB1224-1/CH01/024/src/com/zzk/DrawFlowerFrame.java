package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFlowerFrame extends JFrame {
    DrawFlowerPanel drawFlowerPanel = new DrawFlowerPanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        DrawFlowerFrame frame = new DrawFlowerFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public DrawFlowerFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("ø�s��ä"); // ������D
        setBounds(100, 100, 338, 230); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(drawFlowerPanel); // �N���O���O����ҼW�[�쵡��e����
    }
    
    class DrawFlowerPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) {     // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D)g; // ��oGraphics2D�ﹳ
            g2.translate(drawFlowerPanel.getWidth() / 2, drawFlowerPanel.getHeight() / 2);// �����y�жb
            // ø�s����ä
            Ellipse2D.Float ellipse = new Ellipse2D.Float(30, 0, 70, 20);// �إ߾��ﹳ
            Color color = new Color(0,255,0);//�إ��C��ﹳ
            g2.setColor(color);//���w�C��
            g2.fill(ellipse);// ø�s���
            int i=0;
            while (i<8){
                g2.rotate(30);// ����e��
                g2.fill(ellipse);// ø�s���
                i++;
            }
            // ø�s�����ä
            ellipse = new Ellipse2D.Float(20, 0, 60, 15);// �إ߾��ﹳ
            color = new Color(255,0,0);//�إ��C��ﹳ
            g2.setColor(color);//���w�C��
            g2.fill(ellipse);// ø�s���
            i=0;
            while (i<15){
                g2.rotate(75);// ����e��
                g2.fill(ellipse);// ø�s���
                i++;
            }
            // ø�s�����ä
            ellipse = new Ellipse2D.Float(10, 0, 50, 15);// �إ߾��ﹳ
            color = new Color(255,255,0);//�إ��C��ﹳ
            g2.setColor(color);//���w�C��
            g2.fill(ellipse);// ø�s���
            i=0;
            while (i<8){
                g2.rotate(30);// ����e��
                g2.fill(ellipse);// ø�s���
                i++;
            }
            // ø�s���⤤���I
            color = new Color(255, 0, 0);// �إ��C��ﹳ
            g2.setColor(color);// ���w�C��
            ellipse = new Ellipse2D.Float(-10, -10, 20, 20);// �إ߾��ﹳ
            g2.fill(ellipse);// ø�s���
        }
    }
}
