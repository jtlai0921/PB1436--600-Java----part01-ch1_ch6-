package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WaterWaveActionFrame extends JFrame {
    
    private Thread waveThread; // �ŧi�Ϥ��˼v�u�{
    private WaterWaveActionPanel panel = new WaterWaveActionPanel();
    
    public WaterWaveActionFrame() {
        super();
        setBounds(100, 100, 356, 225);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("���i�ʵe�S��");
        getContentPane().add(panel);
        waveThread = new Thread(panel); // �إ߽u�{�ﹳ
        waveThread.start(); // �Ұʽu�{
    }
    
    public static void main(String[] args) {
        WaterWaveActionFrame frame = new WaterWaveActionFrame();
        frame.setVisible(true);
    }
    
    class WaterWaveActionPanel extends JPanel implements Runnable {
        private Graphics graphics;// Graphics�ﹳ
        private Graphics waveGraphics;// �˼v��Graphics�ﹳ
        private Image image;// ��Image�ﹳ
        private Image waveImage;// ��ܭ˼v��Image�ﹳ
        private int currentIndex;// �ثe�ϧί���
        private int imageWidth;// �ϧΪ��e��
        private int imageHeight;// �ϧΪ�����
        private boolean imageLoaded;// ��ܹϤ��O�_�Q���J���аO
        
        public void paint(Graphics g) {
            if (waveImage != null) {
                g.drawImage(waveImage, -currentIndex * imageWidth, 0, this);// ø�s�ϧ�
            }
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight);// �M����ܰϰ�k�������e
        }
        
        public void run() {
            currentIndex = 0;
            while (!imageLoaded) {// �p�G�Ϥ������J
                repaint();// ��ø�̹�
                graphics = getGraphics();// ��oGraphics�ﹳ
                MediaTracker mediatracker = new MediaTracker(this);// �إߴC��l�ܹﹳ
                URL imgUrl = WaterWaveActionFrame.class
                        .getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
                image = Toolkit.getDefaultToolkit().getImage(imgUrl);// ��o�ϧθ귽
                mediatracker.addImage(image, 0);// �W�[�Ϥ�
                try {
                    mediatracker.waitForAll();// ���J�Ϥ�
                    imageLoaded = !mediatracker.isErrorAny();// �O�_�����~�o��
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) {// ���J�Ϥ�����
                    graphics.drawString("���J�Ϥ����~", 10, 40);// ��X���~��T
                    continue;
                }
                imageWidth = image.getWidth(this);// �o��ϧμe��
                imageHeight = image.getHeight(this);// �o��ϧΰ���
                createWave();// �I�s��k,��{�ʵe�ĪG
                break;
            }
            try {
                while (true) {
                    repaint();// ��ø�̹�
                    currentIndex++;// �վ�ثe�ϧί���
                    if (currentIndex == 12) {// �p�G�ثe�ϧί��ެ�12
                        currentIndex = 0;// �]�w�ثe�ϧί��ެ�0
                    }
                    Thread.sleep(50);// �u�{��v
                }
            } catch (InterruptedException ex) {
            }
        }
        
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight);// �H�ϧΰ��׫إ�Image���
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics();// �o��Image��H��Graphics�ﹳ
                g.drawImage(image, 0, 0, this);// ø�sImage
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2));// �����ϧΰϰ�
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight);// �o��i���ĪG��Image���
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics();// �o��i���ĪG��Graphics���
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this);// ø�s�ϧ�
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// �I�s��k
                    j++;
                }
                g.drawImage(image, 0, 0, this);// ø�s�ϧ�
            }
        }
        
        public void simulateWaves(Graphics g, int i) {// �i���ĪG����
            double d = (6.0 * i) / 12;
            int j = (12 - i) * imageWidth;// �p��������ʪ��Z��
            int waveHeight = imageHeight / 16;// �Ω�p����i������
            for (int m = 0; m < imageHeight; m++) {
                int k = (int) ((waveHeight * (m + 28) * Math.sin(waveHeight
                        * (imageHeight - m) / (m + 1) + d)) / imageHeight);// �Ω󱱨�n�����x�ΰϰ쪺�e��
                if (m < -k)
                    g.copyArea(12 * imageWidth, m, imageWidth, 1, -j, 0);// �����ϧΰϰ�,�Φ��i��
                else
                    g.copyArea(12 * imageWidth, m + k, imageWidth, 1, -j, -k);// �����ϧΰϰ�,�Φ��i��
            }
        }
    }
}