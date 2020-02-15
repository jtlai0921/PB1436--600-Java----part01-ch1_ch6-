package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureInvertedFrame extends JFrame {
    private PictureInvertedPanel invertedPanel = new PictureInvertedPanel();
    public PictureInvertedFrame() {
        super();
        setTitle("�Ϥ��˼v�ĪG");
        setBounds(100, 100, 356, 438);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(invertedPanel);
    }
    
    public static void main(String[] args) {
        PictureInvertedFrame frame = new PictureInvertedFrame();
        frame.setVisible(true);
    }
    
    class PictureInvertedPanel extends JPanel {
        private Graphics graphics; // Graphics�ﹳ
        private Graphics waveGraphics; // ø�s���i��Graphics�ﹳ
        private Image oldImage; // ��ϧιﹳ
        private Image waveImage; // �ŧi��ܤ��i�ĪG���ϧιﹳ
        private int currentImage, imageWidth, imageHeight;
        private boolean imageLoaded; // ��ܹϤ��O�_�Q���J���аO
        public void paint(Graphics g) {
            drawWaterWave();
            if (waveImage != null) {
                g.drawImage(waveImage, -currentImage * imageWidth,
                        imageHeight, this); // ø�s�ϧ�
            }
            g.drawImage(oldImage, 0, 1, this);// ø�s��Ϥ�
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight*2);// �M����ܰϰ�k�������e
        }
        public void drawWaterWave() {
            currentImage = 0;
            if (!imageLoaded) { // �p�G�����J�Ϥ�
                graphics = getGraphics(); // ��oø�ϤW�U��ﹳ
                MediaTracker mediatracker = new MediaTracker(this); // �إߴC��l�ܹﹳ
                URL imgUrl = PictureInvertedFrame.class
                        .getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
                oldImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
                mediatracker.addImage(oldImage, 0); // �W�[�Ϥ�
                try {
                    mediatracker.waitForAll(); // ���J�Ϥ�
                    imageLoaded = !mediatracker.isErrorAny(); // �O�_�����~�o��
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) { // �Ϥ����J����
                    graphics.drawString("�Ϥ����J���~", 10, 40); // ø�s���~��T
                    return;
                }
                imageWidth = oldImage.getWidth(this); // �o��ϧμe��
                imageHeight = oldImage.getHeight(this); // �o��ϧΰ���
                createWave(); // �إߤ��i�ĪG
            }
        }
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight); // �H�ϧμe�שM���׫إ߹ϧιﹳ
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics(); // �o��Image��H��Graphics�ﹳ
                g.drawImage(oldImage, 0, 0, this); // ø�s��ϧιﹳ
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2)); // �����ϧΰϰ�
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight); // �o����i�ĪG���ϧιﹳ
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics(); // �o����i�ĪG��ø�ϤW�U��ﹳ
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this); // ø�s�ϧ�
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// �I�s��k�A������i�ĪG
                    j++;
                }
            }
        }
        public void simulateWaves(Graphics g, int i) { // ���i�ĪG����
            int j = (12 - i) * imageWidth;// �p��ƻs�����������Z��
            int waveHeight = imageHeight / 16;// �p����i����
            for (int l = 0; l < imageHeight; l++) {
                int k = (int) ((waveHeight * (l + 28) * Math.sin(waveHeight
                        * (imageHeight - l) / (l + 1))) / imageHeight);// �p��ƻs�����������Z��
                if (l < -k){
                    g.copyArea(12 * imageWidth, l, imageWidth, 1, -j, 0); // �����ϧΰϰ�,�Φ����i
                } else {
                    g.copyArea(12 * imageWidth, l + k, imageWidth, 1, -j, -k);// �����ϧΰϰ�,�Φ����i
                }
            }
        }
    }
}