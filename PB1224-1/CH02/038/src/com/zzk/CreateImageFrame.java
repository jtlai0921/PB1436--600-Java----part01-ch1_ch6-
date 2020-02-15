package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreateImageFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateImageFrame frame = new CreateImageFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CreateImageFrame() {
        super();
        setTitle("使用像素值產生圖形");
        setBounds(100, 100, 300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new CreateImagePanel());
    }
    
    class CreateImagePanel extends JPanel {// 建立用像素值產生圖形的面板類別
        public void paint(Graphics g) {
            int w = 300;// 寬度
            int h = 220;// 高度
            int pix[] = new int[w * h];// 儲存像素值的陣列
            int index = 0;// 儲存陣列的索引
            for (int y = 0; y < h; y++) {// 在垂直進行調整，從黑色漸層到紅色
                int red = (y * 255) / (h - 1);// 計算垂直的顏色值
                for (int x = 0; x < w; x++) {// 在水平進行調整，從黑色漸層到藍色
                    int blue = (x * 255) / (w - 1);// 計算水平的顏色值
                    // 透過移位運算和邏輯或運算計算像素值，並給予值給像素陣列
                    pix[index++] = (255 << 24) | (red << 16) | blue;
                }
            }
            // 建立使用陣列為Image產生像素值的ImageProducer對像
            ImageProducer imageProducer = new MemoryImageSource(w, h, pix, 0, w);
            Image img = createImage(imageProducer);// 建立圖形對像
            g.drawImage(img, 0, 0,getWidth(),getHeight(), this);// 繪製圖形
        }
    }
}
