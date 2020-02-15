package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeWidthFrame extends JFrame {
    ChangeStrokeWidthPanel strokeWidthPanel = new ChangeStrokeWidthPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        StrokeWidthFrame frame = new StrokeWidthFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public StrokeWidthFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("設定筆畫的粗細"); // 窗體標題
        setBounds(100, 100, 300, 220); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(strokeWidthPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class ChangeStrokeWidthPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            BasicStroke stroke = new BasicStroke(1); // 建立寬度是1的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20,20,100,60);// 建立橢圓對像
            g2.draw(ellipse);// 繪製橢圓
            stroke = new BasicStroke(4);// 建立寬度是4的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            ellipse = new Ellipse2D.Float(160,20,100,60);// 建立橢圓對像
            g2.draw(ellipse);// 繪製橢圓
            stroke = new BasicStroke(6);// 建立寬度是6的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            ellipse = new Ellipse2D.Float(20,100,100,60);// 建立橢圓對像
            g2.draw(ellipse);// 繪製橢圓
            stroke = new BasicStroke(8);// 建立寬度是8的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            ellipse = new Ellipse2D.Float(160,100,100,60);// 建立橢圓對像
            g2.draw(ellipse);// 繪製橢圓
        }
    }
}
