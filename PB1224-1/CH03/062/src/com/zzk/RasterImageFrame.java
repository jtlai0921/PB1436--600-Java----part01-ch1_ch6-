package com.zzk;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class RasterImageFrame extends JFrame {
    private static final double XMIN = -2;// 宣告在x方向的最小值
    private static final double XMAX = 2;// 宣告在x方向的最大值
    private static final double YMIN = -2;// 宣告在y方向的最小值
    private static final double YMAX = 2;// 宣告在y方向的最大值
    private static final int MAX_ITERATIONS = 16;// 宣告最大迭代次數
    
    public RasterImageFrame() {
        setTitle("光柵圖形");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage image = makeRasterImage(300, 240);
        add(new JLabel(new ImageIcon(image)));
    }
    
    public static void main(String[] args) {
        JFrame frame = new RasterImageFrame();
        frame.setVisible(true);
    }
    
    private BufferedImage makeRasterImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);// 建立緩衝圖形對像
        WritableRaster raster = image.getRaster();// 獲得提供像素寫入功能的WritableRaster對像
        ColorModel model = image.getColorModel();// 獲得緩衝圖形的顏色模型
        Color fractalColor = Color.RED;// 定義表示紅色的顏色對像
        int argb = fractalColor.getRGB();// 獲得表示顏色的RGB值
        Object colorData = model.getDataElements(argb, null);// 傳回ColorModel中指定像素的陣列表示形式
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // 計算點（i,j）是否導致與點(a,b)上的像素收斂
                double a = XMIN + i * (XMAX - XMIN) / width;
                double b = YMIN + j * (YMAX - YMIN) / height;
                if (!isOrNotConvergence(a, b)) {// 如果點（i,j）導致與點(a,b)上的像素收斂，escapesToInfinity()方法傳回false，則進行光柵繪製
                    raster.setDataElements(i, j, colorData);// 為型態TransferType基本陣列中的單一像素設定資料
                }
            }
        }
        return image;// 傳回繪製有光柵圖形的緩衝圖形對像
    }
    
    private boolean isOrNotConvergence(double a, double b) {// 判斷數字序列上的點(a,b)是收斂的，還是發散的
        double x = 0.0D;// 如果x大於2，數字序列就是發散的
        double y = 0.0D;// 如果y大於2，數字序列也是發散的
        int iterations = 0;// 循環變數
        while (x <= 2 && y <= 2 && iterations < MAX_ITERATIONS) {
            double xNew = x * x - y * y + a;// 計算每個點的x值
            double yNew = 2 * x * y + b;// 計算每個點的y值
            x = xNew;// 給予值給變數x，用於判斷是收斂還是發散
            y = yNew;// 給予值給變數y，用於判斷是收斂還是發散
            iterations++; // 調整迭代器變數的值
        }
        return x > 2 || y > 2;// 傳回false表示收斂則進行繪製，為true表示發散則透明
    }
}