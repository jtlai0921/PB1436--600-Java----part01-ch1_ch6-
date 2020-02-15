package com.zzk;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WatermarkTextFrame extends JFrame {
    private Image img = null;  // �ŧi�ϧιﹳ
    private WatermarkTextPanel watermarkTextPanel = null;  // �ŧi�ϧέ��O�ﹳ
    public static void main(String args[]) {
        WatermarkTextFrame frame = new WatermarkTextFrame();
        frame.setVisible(true);
    }
    public WatermarkTextFrame() {
        super();
        URL imgUrl = WatermarkTextFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        watermarkTextPanel = new WatermarkTextPanel();  // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(watermarkTextPanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("���L��r�S��"); // �]�w������D
    }
    // �إ߭��O���O
    class WatermarkTextPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;// ��oGraphics2D�ﹳ
            g2.drawImage(img, 0, 0, 300, 237, this);// ø�s�ϧ�
            g2.rotate(Math.toRadians(-30));// ����ø�ϤW�U��ﹳ
            Font font = new Font("����",Font.BOLD,60);// �إߦr��ﹳ
            g2.setFont(font);//���w�r��
            g2.setColor(Color.WHITE);// ���w�C��
            AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.3f);// ��o��ܳz���ת�AlphaComposite�ﹳ
            g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
            g2.drawString("�s�{����", -60, 180);// ø�s�¤�r,��{���L
        }
    }
}
