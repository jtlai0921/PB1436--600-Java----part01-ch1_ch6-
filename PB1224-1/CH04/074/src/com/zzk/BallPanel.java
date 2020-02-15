package com.zzk;
import java.awt.*;
import javax.swing.JLabel;
/**
 * @author 張振坤
 */
public class BallPanel extends JLabel implements Runnable {
    private int r = 10;// 小球半徑
    private int width = r * 2;// 球寬度
    private int height = r * 2;// 球高度
    private Color ballColor = Color.BLUE;// 預設顏色
    public BallPanel() {
        setPreferredSize(new Dimension(width, height));// 初始化大小
        new Thread(this).start();// 啟動小球跳躍線程
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ballColor);// 設定預設顏色
        g.fillOval(0, 0, width, height);// 在標籤上繪製球體
    }
    @Override
    public void run() {
        Container parent = getParent();/// 獲得目前標籤的父級容器對像
        Point myPoint = getLocation();// 獲得初始位置
        while (true) {// 循環讀取父容器對像
            if (parent == null) {
                try {
                    Thread.sleep(50);// 線程休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myPoint = getLocation();// 獲得初始位置
                parent = getParent();// 獲得目前標籤的父級容器對像
            } else {// 如果已經獲得到父容器
                break;// 跳出循環
            }
        }
        int sx = myPoint.x;// X座標
        int sy = myPoint.y;// y座標
        int step = (int) (Math.random() * 10) % 3 + 1;// 移動步進
        int dx = (Math.random() * 100) >= 50 ? step : -step;// 水平步進值
        step = (int) (Math.random() * 10) % 3 + 1;// 移動步進
        int dy = (Math.random() * 100) >= 50 ? step : -step;// 垂直步進值
        int stime = (int) (Math.random() * 80 + 10);// 隨機移動速度
        while (parent.isVisible()) {
            int parentWidth = parent.getWidth();// 容器寬度
            int parentHeight = parent.getHeight();// 容器高度
            setLocation(sx, sy);// 指定小球的位置
            try {
                Thread.sleep(stime);// 透過休眠改變移動速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sx = sx + dx * 5;// 水平座標偏移5個像素
            sy = sy + dy * 5;// 垂直座標偏移5個像素
            // 檢測垂直邊界
            if (sy > parentHeight - height || sy < 0)
                dy = -dy;// 防止座標超出垂直邊界
            // 檢測水平邊界
            if (sx > parentWidth - width || sx < 0)
                dx = -dx;// 防止座標超出水平邊界
        }
    }
}
