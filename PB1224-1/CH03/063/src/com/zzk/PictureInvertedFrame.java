package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PictureInvertedFrame extends JFrame {
    private PictureInvertedPanel invertedPanel = new PictureInvertedPanel();
    public PictureInvertedFrame() {
        super();
        setTitle("圖片倒影效果");
        setBounds(100, 100, 356, 438);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(invertedPanel);
    }
    
    public static void main(String[] args) {
        PictureInvertedFrame frame = new PictureInvertedFrame();
        frame.setVisible(true);
    }
    
    class PictureInvertedPanel extends JPanel {
        private Graphics graphics; // Graphics對像
        private Graphics waveGraphics; // 繪製水波的Graphics對像
        private Image oldImage; // 原圖形對像
        private Image waveImage; // 宣告表示水波效果的圖形對像
        private int currentImage, imageWidth, imageHeight;
        private boolean imageLoaded; // 表示圖片是否被載入的標記
        public void paint(Graphics g) {
            drawWaterWave();
            if (waveImage != null) {
                g.drawImage(waveImage, -currentImage * imageWidth,
                        imageHeight, this); // 繪製圖形
            }
            g.drawImage(oldImage, 0, 1, this);// 繪製原圖片
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight*2);// 清除顯示區域右側的內容
        }
        public void drawWaterWave() {
            currentImage = 0;
            if (!imageLoaded) { // 如果未載入圖片
                graphics = getGraphics(); // 獲得繪圖上下文對像
                MediaTracker mediatracker = new MediaTracker(this); // 建立媒體追蹤對像
                URL imgUrl = PictureInvertedFrame.class
                        .getResource("/img/image.jpg");// 獲得圖片資源的路徑
                oldImage = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
                mediatracker.addImage(oldImage, 0); // 增加圖片
                try {
                    mediatracker.waitForAll(); // 載入圖片
                    imageLoaded = !mediatracker.isErrorAny(); // 是否有錯誤發生
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) { // 圖片載入失敗
                    graphics.drawString("圖片載入錯誤", 10, 40); // 繪製錯誤資訊
                    return;
                }
                imageWidth = oldImage.getWidth(this); // 得到圖形寬度
                imageHeight = oldImage.getHeight(this); // 得到圖形高度
                createWave(); // 建立水波效果
            }
        }
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight); // 以圖形寬度和高度建立圖形對像
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics(); // 得到Image對象的Graphics對像
                g.drawImage(oldImage, 0, 0, this); // 繪製原圖形對像
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2)); // 拷貝圖形區域
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight); // 得到水波效果的圖形對像
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics(); // 得到水波效果的繪圖上下文對像
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this); // 繪製圖形
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// 呼叫方法，類比水波效果
                    j++;
                }
            }
        }
        public void simulateWaves(Graphics g, int i) { // 水波效果類比
            int j = (12 - i) * imageWidth;// 計算複製像素的水平距離
            int waveHeight = imageHeight / 16;// 計算水波高度
            for (int l = 0; l < imageHeight; l++) {
                int k = (int) ((waveHeight * (l + 28) * Math.sin(waveHeight
                        * (imageHeight - l) / (l + 1))) / imageHeight);// 計算複製像素的垂直距離
                if (l < -k){
                    g.copyArea(12 * imageWidth, l, imageWidth, 1, -j, 0); // 拷貝圖形區域,形成水波
                } else {
                    g.copyArea(12 * imageWidth, l + k, imageWidth, 1, -j, -k);// 拷貝圖形區域,形成水波
                }
            }
        }
    }
}