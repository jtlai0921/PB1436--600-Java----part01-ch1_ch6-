package com.zzk;
import java.awt.*;
import javax.swing.*;
/**
 * @author 張振坤
 */
public class SnowFlakeLabel extends JLabel implements Runnable {
    private final static ImageIcon snow = new ImageIcon(SnowFlakeLabel.class
            .getResource("/image/snowflake.png"));
    private int width = snow.getIconWidth();// 寬度
    private int height = snow.getIconHeight();// 高度
    /**
     * 建構方法
     */
    public SnowFlakeLabel() {
        setSize(new Dimension(width, height));// 初始化大小
        setIcon(snow);// 指定圖標
        new Thread(this).start();// 建立並啟動線程
    }
    public void run() {
        Container parent = getParent();// 獲得父容器對像
        Point myPoint = getLocation();// 獲得初始位置
        while (true) {// 循環讀取父容器對像
            if (parent == null) {
                try {
                    Thread.sleep(50);// 線程休眠
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myPoint = getLocation();// 獲得初始位置
                parent = getParent();// 獲得父容器對像
            } else {// 如果已經獲得到父容器
                break;// 跳出循環
            }
        }
        int sx = myPoint.x;// X座標
        int sy = myPoint.y;// Y座標
        int stime = (int) (Math.random() * 30 + 10);// 隨機移動速度
        int parentHeight = parent.getHeight();// 容器高度
        while (parent.isVisible() && sy < parentHeight - height) {
            setLocation(sx, sy);// 指定位置
            try {
                Thread.sleep(stime);// 線程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sy += 2;// 垂直偏移2個像素
        }
    }
}
