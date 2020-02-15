package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;

/**
 * ���ҽX���O
 * 
 * @author ZhenKun Zhang
 */
public class ChineseCodePanel extends JPanel {
    private static final long serialVersionUID = -3124698225447711692L;
    public static final int WIDTH = 120;// �e��
    public static final int HEIGHT = 35;// ����
    private String num = "";// ���ҽX
    Random random = new Random();// ��Ҥ�Random
    
    public ChineseCodePanel() {
        this.setVisible(true);// ��ܭ��O
        setLayout(null);// �ŧG��
    }
    public void paint(Graphics g) {
        String hanZi = "�s�{���嶰�Ǭd�άɭ��]�p���ض}�o�����e��@��";// �w�q���ҽX�ϥΪ�����r
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);// ��Ҥ�BufferedImage
        Graphics gs = image.getGraphics(); // ��oGraphics���O���ﹳ
        if (!num.isEmpty()) {
            num = "";// �M�����ҽX
        }
        Font font = new Font("����", Font.BOLD, 20); // �z�LFont�غc�r��
        gs.setFont(font);// �]�w�r��
        gs.fillRect(0, 0, WIDTH, HEIGHT);// ��R�@�ӯx��
        // ��X�H�������Ҥ�r
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(hanZi.length());// �H����o����r�����ޭ�
            String ctmp  = hanZi.substring(index,index+1);// ��o���w���޳B���@�Ӥ���r
            num += ctmp;// ��s���ҽX
            Color color = new Color(20 + random.nextInt(120), 20 + random
                    .nextInt(120), 20 + random.nextInt(120));// �����H���C��
            gs.setColor(color); // �]�w�C��
            Graphics2D gs2d = (Graphics2D) gs;// �N��r������w����
            AffineTransform trans = new AffineTransform();// ��Ҥ�AffineTransform
            trans.rotate(random.nextInt(45) * 3.14 / 180, 22 * i + 8, 7);
            float scaleSize = random.nextFloat() + 0.8f;// �Y���r
            if (scaleSize > 1f)
                scaleSize = 1f;// �p�GscaleSize�j��1,�h����1
            trans.scale(scaleSize, scaleSize); // �i���Y��
            gs2d.setTransform(trans);// �]�wAffineTransform�ﹳ
            gs.drawString(ctmp, WIDTH / 6 * i + 28, HEIGHT / 2);// �e�X���ҽX
        }
        g.drawImage(image, 0, 0, null);// �b���O���e�X���ҽX
    }
    
    // �������ҽX����k
    public void draw() {
        repaint();// �I�spaint()��k
    }
    
    public String getNum() {
        return num;// �Ǧ^���ҽX
    }
}