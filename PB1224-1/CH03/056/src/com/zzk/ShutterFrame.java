package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShutterFrame extends JFrame {
    private BufferedImage image;// 宣告緩衝圖形對像
    private Image img = null; // 宣告圖形對像
    private ShutterPanel shutterPanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        ShutterFrame frame = new ShutterFrame();
        frame.setVisible(true);
    }
    
    public ShutterFrame() {
        super();
        URL imgUrl = ShutterFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        shutterPanel = new ShutterPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(shutterPanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片百葉窗特效"); // 設定窗體標題
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// 建立 Kernel對像
        ConvolveOp op = new ConvolveOp(kernel);// 建立ConvolveOp對像
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // 過濾緩衝圖形對像
        repaint();// 呼叫paint()方法
    }
    
    // 建立面板類別
    class ShutterPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, this);// 繪製圖形對像
            int y = 5;  // 直線繪製點的y座標
            int space = 10;// 下一條直線的偏移量
            Line2D.Float line = null;
            image = new BufferedImage(getWidth() + 10, getHeight(),
                    BufferedImage.TYPE_INT_ARGB);// 建立緩衝圖形對像
            Graphics2D gs2d = (Graphics2D) image.getGraphics();// 獲得緩衝圖形對象的Graphics2D對像
            BasicStroke stroke = new BasicStroke(7); // 建立寬度是7的筆畫對像
            gs2d.setStroke(stroke);// 設定筆畫對像
            gs2d.setColor(Color.WHITE);// 指定顏色
            while (y <= getHeight()) {
                line = new Line2D.Float(0, y, getWidth(), y);// 建立直線對像
                gs2d.draw(line);// 在緩衝圖形對像上繪製直線
                y = y + space;// 計算下一條直線的y座標
            }
            for (int i = 0; i < 3; i++) {// 該for循環，實現3次模糊
                float[] elements = new float[9];// 定義表示像素份量的陣列
                for (int j = 0; j < 9; j++) {
                    elements[j] = 0.11f;// 為陣列給予值
                }
                convolve(elements);// 呼叫方法，實現模糊功能
            }
            g2.drawImage(image, 0, 0, this);// 繪製緩衝圖形對像
        }
    }
}
