package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextureFillFrame extends JFrame {
    private TextureFillPanel textureFillPanel = null;  // �ŧi���O�ﹳ
    public static void main(String args[]) {
        TextureFillFrame frame = new TextureFillFrame();
        frame.setVisible(true);
    }
    public TextureFillFrame() {
        super();
        textureFillPanel = new TextureFillPanel();  // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 308, 230); // �]�w����j�p�M��m
        this.add(textureFillPanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("���z��R�S��"); // �]�w������D
    }
    // �إ߭��O���O
    class TextureFillPanel extends JPanel {
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
            Graphics2D g2 = image.createGraphics();// ��o�w�Ĺϧι�H��ø�ϤW�U��ﹳ
            g2.setColor(Color.BLUE);// �]�w�C��
            g2.fillRect(0,0,90,90);// ø�s��R�x��
            g2.setColor(Color.RED);// �]�w�C��
            g2.fillOval(95,95,90,90);// ø�s�a��R�⪺���
            Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// �إ�Rectangle2D�ﹳ
            TexturePaint textPaint = new TexturePaint(image,rect);// �إ߯��z��R�ﹳ
            Graphics2D graphics2 = (Graphics2D)g;// �ഫpaint()��k��ø�ϤW�U��ﹳ
            graphics2.setPaint(textPaint);// ��ø�ϤW�U��ﹳ�]�w���z��R�ﹳ
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(45, 25, 200, 140);// �إ߯x�ιﹳ
            graphics2.fill(ellipse2);// ø�s��R���z���x��
        }
    }
}
