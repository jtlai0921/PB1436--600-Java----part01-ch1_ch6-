package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFlowerFrame extends JFrame {
    DrawFlowerPanel drawFlowerPanel = new DrawFlowerPanel(); // 建立面板類別的實例
    public static void main(String args[]) { // 主方法
        DrawFlowerFrame frame = new DrawFlowerFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawFlowerFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製花瓣"); // 窗體標題
        setBounds(100, 100, 338, 230); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(drawFlowerPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawFlowerPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) {     // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            g2.translate(drawFlowerPanel.getWidth() / 2, drawFlowerPanel.getHeight() / 2);// 平移座標軸
            // 繪製綠色花瓣
            Ellipse2D.Float ellipse = new Ellipse2D.Float(30, 0, 70, 20);// 建立橢圓對像
            Color color = new Color(0,255,0);//建立顏色對像
            g2.setColor(color);//指定顏色
            g2.fill(ellipse);// 繪製橢圓
            int i=0;
            while (i<8){
                g2.rotate(30);// 旋轉畫布
                g2.fill(ellipse);// 繪製橢圓
                i++;
            }
            // 繪製紅色花瓣
            ellipse = new Ellipse2D.Float(20, 0, 60, 15);// 建立橢圓對像
            color = new Color(255,0,0);//建立顏色對像
            g2.setColor(color);//指定顏色
            g2.fill(ellipse);// 繪製橢圓
            i=0;
            while (i<15){
                g2.rotate(75);// 旋轉畫布
                g2.fill(ellipse);// 繪製橢圓
                i++;
            }
            // 繪製黃色花瓣
            ellipse = new Ellipse2D.Float(10, 0, 50, 15);// 建立橢圓對像
            color = new Color(255,255,0);//建立顏色對像
            g2.setColor(color);//指定顏色
            g2.fill(ellipse);// 繪製橢圓
            i=0;
            while (i<8){
                g2.rotate(30);// 旋轉畫布
                g2.fill(ellipse);// 繪製橢圓
                i++;
            }
            // 繪製紅色中心點
            color = new Color(255, 0, 0);// 建立顏色對像
            g2.setColor(color);// 指定顏色
            ellipse = new Ellipse2D.Float(-10, -10, 20, 20);// 建立橢圓對像
            g2.fill(ellipse);// 繪製橢圓
        }
    }
}
