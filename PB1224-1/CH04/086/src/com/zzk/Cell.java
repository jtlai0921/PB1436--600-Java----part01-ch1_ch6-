package com.zzk;

import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * 單元圖片
 * 
 * @author 張振坤
 */
public class Cell extends JButton {
    private static final long serialVersionUID = 718623114650657819L;
    public static final int IMAGEWIDTH = 117;// 圖片寬度
    private int place;// 圖片位置
    
    public Cell(Icon icon, int place) {
        this.setSize(IMAGEWIDTH, IMAGEWIDTH);// 單元圖片的大小
        this.setIcon(icon);// 單元圖片的圖標
        this.place = place;// 單元圖片的位置
    }
    
    public void move(Direction dir) {// 移動單元圖片的方法
        Rectangle rec = this.getBounds();// 獲得圖片的Rectangle對像
        switch (dir) {// 判斷方向
            case UP:// 向上移動
                this.setLocation(rec.x, rec.y - IMAGEWIDTH);
                break;
            case DOWN:// 向下移動
                this.setLocation(rec.x, rec.y + IMAGEWIDTH);
                break;
            case LEFT:// 向左移動
                this.setLocation(rec.x - IMAGEWIDTH, rec.y);
                break;
            case RIGHT:// 向右移動
                this.setLocation(rec.x + IMAGEWIDTH, rec.y);
                break;
        }
    }
    
    public int getX() {
        return this.getBounds().x;// 獲得單元圖片的x座標
    }
    
    public int getY() {
        return this.getBounds().y;// 獲得單元圖片的y座標
    }
    
    public int getPlace() {
        return place;// 獲得單元圖片的位置
    }
}
