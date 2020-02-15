package com.zzk;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawImageFrame extends JFrame {
    private Image img = null;  // �ŧi�ϧιﹳ
    private DrawImagePanel imagePanel = null;  // �ŧi�ϧέ��O�ﹳ
    public static void main(String args[]) {
        DrawImageFrame frame = new DrawImageFrame();
        frame.setVisible(true);
    }
    public DrawImageFrame() {
        super();
        URL imgUrl = DrawImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imagePanel = new DrawImagePanel();  // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(imagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("ø�s�ϧ�"); // �]�w������D
    }
    // �إ߭��O���O
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, this); // ø�s���w���Ϥ�
        }
    }
}
