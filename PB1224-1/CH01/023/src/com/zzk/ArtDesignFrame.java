package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArtDesignFrame extends JFrame {
    ArtDesignPanel artDesignPanel = new ArtDesignPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        ArtDesignFrame frame = new ArtDesignFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public ArtDesignFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製藝術圖案"); // 窗體標題
        setBounds(100, 100, 338, 230); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(artDesignPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class ArtDesignPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) {     // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            Ellipse2D.Float ellipse = new Ellipse2D.Float(-80, 5, 160, 10);// 建立橢圓對像
            Random random = new Random();// 建立隨機數對像
            g2.translate(160, 90);// 平移座標軸
            int R = random.nextInt(256);//隨機產生顏色的R值
            int G = random.nextInt(256);//隨機產生顏色的G值
            int B = random.nextInt(256);//隨機產生顏色的B值
            Color color = new Color(R,G,B);//建立顏色對像
            g2.setColor(color);//指定顏色
            g2.draw(ellipse);// 繪製橢圓
            int i=0;
            while (i<100){
                R = random.nextInt(256);//隨機產生顏色的R值
                G = random.nextInt(256);//隨機產生顏色的G值
                B = random.nextInt(256);//隨機產生顏色的B值
                color = new Color(R,G,B);//建立新的顏色對像
                g2.setColor(color);//指定顏色
                g2.rotate(10);// 旋轉畫布
                g2.draw(ellipse);// 繪製橢圓
                i++;
            }
        }
    }
}
