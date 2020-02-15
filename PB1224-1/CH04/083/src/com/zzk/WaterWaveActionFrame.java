package com.zzk;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WaterWaveActionFrame extends JFrame {
    
    private Thread waveThread; // 宣告圖片倒影線程
    private WaterWaveActionPanel panel = new WaterWaveActionPanel();
    
    public WaterWaveActionFrame() {
        super();
        setBounds(100, 100, 356, 225);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("水波動畫特效");
        getContentPane().add(panel);
        waveThread = new Thread(panel); // 建立線程對像
        waveThread.start(); // 啟動線程
    }
    
    public static void main(String[] args) {
        WaterWaveActionFrame frame = new WaterWaveActionFrame();
        frame.setVisible(true);
    }
    
    class WaterWaveActionPanel extends JPanel implements Runnable {
        private Graphics graphics;// Graphics對像
        private Graphics waveGraphics;// 倒影的Graphics對像
        private Image image;// 原Image對像
        private Image waveImage;// 表示倒影的Image對像
        private int currentIndex;// 目前圖形索引
        private int imageWidth;// 圖形的寬度
        private int imageHeight;// 圖形的高度
        private boolean imageLoaded;// 表示圖片是否被載入的標記
        
        public void paint(Graphics g) {
            if (waveImage != null) {
                g.drawImage(waveImage, -currentIndex * imageWidth, 0, this);// 繪製圖形
            }
            g.clearRect(imageWidth, 0, imageWidth * 4, imageHeight);// 清除顯示區域右側的內容
        }
        
        public void run() {
            currentIndex = 0;
            while (!imageLoaded) {// 如果圖片未載入
                repaint();// 重繪屏幕
                graphics = getGraphics();// 獲得Graphics對像
                MediaTracker mediatracker = new MediaTracker(this);// 建立媒體追蹤對像
                URL imgUrl = WaterWaveActionFrame.class
                        .getResource("/img/image.jpg");// 獲得圖片資源的路徑
                image = Toolkit.getDefaultToolkit().getImage(imgUrl);// 獲得圖形資源
                mediatracker.addImage(image, 0);// 增加圖片
                try {
                    mediatracker.waitForAll();// 載入圖片
                    imageLoaded = !mediatracker.isErrorAny();// 是否有錯誤發生
                } catch (InterruptedException ex) {
                }
                if (!imageLoaded) {// 載入圖片失敗
                    graphics.drawString("載入圖片錯誤", 10, 40);// 輸出錯誤資訊
                    continue;
                }
                imageWidth = image.getWidth(this);// 得到圖形寬度
                imageHeight = image.getHeight(this);// 得到圖形高度
                createWave();// 呼叫方法,實現動畫效果
                break;
            }
            try {
                while (true) {
                    repaint();// 重繪屏幕
                    currentIndex++;// 調整目前圖形索引
                    if (currentIndex == 12) {// 如果目前圖形索引為12
                        currentIndex = 0;// 設定目前圖形索引為0
                    }
                    Thread.sleep(50);// 線程休眠
                }
            } catch (InterruptedException ex) {
            }
        }
        
        public void createWave() {
            Image img = createImage(imageWidth, imageHeight);// 以圖形高度建立Image實例
            Graphics g = null;
            if (img != null) {
                g = img.getGraphics();// 得到Image對象的Graphics對像
                g.drawImage(image, 0, 0, this);// 繪製Image
                for (int i = 0; i < imageHeight; i++) {
                    g.copyArea(0, imageHeight - 1 - i, imageWidth, 1, 0,
                            -imageHeight + 1 + (i * 2));// 拷貝圖形區域
                }
            }
            waveImage = createImage(13 * imageWidth, imageHeight);// 得到波浪效果的Image實例
            if (waveImage != null) {
                waveGraphics = waveImage.getGraphics();// 得到波浪效果的Graphics實例
                waveGraphics.drawImage(img, 12 * imageWidth, 0, this);// 繪製圖形
                int j = 0;
                while (j < 12) {
                    simulateWaves(waveGraphics, j);// 呼叫方法
                    j++;
                }
                g.drawImage(image, 0, 0, this);// 繪製圖形
            }
        }
        
        public void simulateWaves(Graphics g, int i) {// 波浪效果類比
            double d = (6.0 * i) / 12;
            int j = (12 - i) * imageWidth;// 計算水平移動的距離
            int waveHeight = imageHeight / 16;// 用於計算水波的高度
            for (int m = 0; m < imageHeight; m++) {
                int k = (int) ((waveHeight * (m + 28) * Math.sin(waveHeight
                        * (imageHeight - m) / (m + 1) + d)) / imageHeight);// 用於控制要拷貝矩形區域的寬度
                if (m < -k)
                    g.copyArea(12 * imageWidth, m, imageWidth, 1, -j, 0);// 拷貝圖形區域,形成波浪
                else
                    g.copyArea(12 * imageWidth, m + k, imageWidth, 1, -j, -k);// 拷貝圖形區域,形成波浪
            }
        }
    }
}