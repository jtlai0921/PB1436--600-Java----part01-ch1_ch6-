package com.zzk;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImgLabel extends JLabel {
    public ImgLabel() {
        super();
    }
    @Override
    public void paint(Graphics g) {
        try {
            int width = this.getWidth(); // 獲得圖片寬度
            int height = this.getHeight(); // 獲得圖片高度
            ImageIcon icon = (ImageIcon) getIcon(); // 獲得ImageIcon對像
            if (icon != null) {// 圖片不為空
                g.drawImage(icon.getImage(), 0, 0, width, height, this); // 繪製圖片
            }
        } catch (Exception e) {
            e.printStackTrace(); // 輸出例外資訊
        }
    }
}
