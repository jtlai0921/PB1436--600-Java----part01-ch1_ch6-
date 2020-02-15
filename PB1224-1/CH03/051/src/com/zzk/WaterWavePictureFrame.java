package com.zzk;

import java.awt.*;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WaterWavePictureFrame extends JFrame {
    private WaterWavePicturePanel wavePanel = new WaterWavePicturePanel();
    
    public WaterWavePictureFrame() {
        super();
        setTitle("���i�ĪG���Ϥ�");
        setBounds(100, 100, 356, 235);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(wavePanel);
    }
    
    public static void main(String[] args) {
        WaterWavePictureFrame frame = new WaterWavePictureFrame();
        frame.setVisible(true);
    }
    
    class WaterWavePicturePanel extends JPanel {
        private Graphics graphics; // Graphics�ﹳ
        private Graphics waveGraphics; // ø�s���i��Graphics�ﹳ
        private Image oldImage; // ��ϧιﹳ
        private Image waveImage; // �ŧi��ܤ��i�ĪG���ϧιﹳ
        private int currentImage, imageWidth, imageHeight;
        private boolean isImageLoaded; // ��ܹϤ��O�_�Q���J���аO
        
        public void paint(Graphics g) {
            drawWaterWave();
            if (waveImage != null) {
                g.drawImage(waveImage, -currentImage * imageWidth, 0, this); // ø�s�ϧ�
            }
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight * 2);// �M����ܰϰ�k�������e
        }
        
        public void drawWaterWave() {
            currentImage = 0;
            if (!isImageLoaded) { // �p�G�����J�Ϥ�
                graphics = getGraphics(); // ��oø�ϤW�U��ﹳ
                MediaTracker mediatracker = new MediaTracker(this); // �إߴC��l�ܹﹳ
                URL imgUrl = WaterWavePictureFrame.class
                        .getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
                oldImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
                mediatracker.addImage(oldImage, 0); // �W�[�Ϥ�
                try {
                    mediatracker.waitForAll(); // ���J�Ϥ�
                    isImageLoaded = !mediatracker.isErrorAny(); // �O�_�����~�o��
                } catch (InterruptedException ex) {
                }
                if (!isImageLoaded) { // �Ϥ����J����
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
            for (int h = 0; h < imageHeight; h++) {
                int k = (int) ((waveHeight * (h + 28) * Math.sin(waveHeight
                        * (imageHeight - h) / (h + 1))) / imageHeight);// �p��ƻs�����������Z��
                if (h < -k) {
                    g.copyArea(12 * imageWidth, h, imageWidth, 1, -j, 0); // �����ϧΰϰ�,�Φ����i
                } else {
                    g.copyArea(12 * imageWidth, h + k, imageWidth, 1, -j, -k);// �����ϧΰϰ�,�Φ����i
                }
            }
        }
    }
}