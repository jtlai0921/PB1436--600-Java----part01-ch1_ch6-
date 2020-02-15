package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeStyleFrame extends JFrame {
    ChangeStrokeStylePanel strokeStylePanel = new ChangeStrokeStylePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        StrokeStyleFrame frame = new StrokeStyleFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public StrokeStyleFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("設定筆畫樣式"); // 窗體標題
        setBounds(100, 100, 306, 220); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(strokeStylePanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class ChangeStrokeStylePanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); // 建立寬度是10的平頭筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            Line2D.Float line = new Line2D.Float(50,50,240,50);// 建立直線對像
            g2.drawString("平頭樣式", 120, 40);  // 繪製純文字
            g2.draw(line);// 繪製直線
            stroke = new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL); // 建立寬度是10的圓頭筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            line = new Line2D.Float(50,90,240,90);// 建立直線對像
            g2.drawString("圓頭樣式", 120, 80);  // 繪製純文字
            g2.draw(line);// 繪製直線
            stroke = new BasicStroke(10,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL); // 建立寬度是10的方頭筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            line = new Line2D.Float(50,130,240,130);// 建立直線對像
            g2.drawString("方頭樣式", 120, 120);  // 繪製純文字
            g2.draw(line);// 繪製直線
        }
    }
}
