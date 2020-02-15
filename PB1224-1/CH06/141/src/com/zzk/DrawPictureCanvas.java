package com.zzk;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 * @author 張振坤
 *         建立畫布類別
 */
public class DrawPictureCanvas extends Canvas {
    private Image image = null; // 定義Image對象的參考
    public DrawPictureCanvas() {
        super();
    }
    public void setImage(Image image) {
        this.image = image; // 為成員變數給予值
    }
    /*
     * 重新定義paint()方法,在畫布上繪製圖形
     */
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null); // 在畫布上繪製圖形
    }
    /*
     * 重新定義update()方法，這樣可以解決屏幕閃耀的問題
     */
    public void update(Graphics g) {
        paint(g); // 呼叫paint方法
    }
}
