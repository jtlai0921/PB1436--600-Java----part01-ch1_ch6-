package com.zzk;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StrokeJoinFrame extends JFrame {
    ChangeStrokeJoinPanel strokeJoinPanel = new ChangeStrokeJoinPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        StrokeJoinFrame frame = new StrokeJoinFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public StrokeJoinFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("設定連接方式"); // 窗體標題
        setBounds(100, 100, 338, 198); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(strokeJoinPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class ChangeStrokeJoinPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            BasicStroke stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL); // 建立寬度是10的平頭斜角連接筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            Rectangle2D.Float rect = new Rectangle2D.Float(20,60,80,50);// 建立矩形對像
            g2.drawString("斜角連接", 35, 50);  // 繪製純文字
            g2.draw(rect);// 繪製矩形
            stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER); // 建立寬度是10的平頭尖角連接筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            rect = new Rectangle2D.Float(120,60,80,50);// 建立矩形對像
            g2.drawString("尖角連接", 135, 50);  // 繪製純文字
            g2.draw(rect);// 繪製矩形
            stroke = new BasicStroke(10,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND); // 建立寬度是10的平頭圓角連接筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            rect = new Rectangle2D.Float(220,60,80,50);// 建立矩形對像
            g2.drawString("圓角連接", 235, 50);  // 繪製純文字
            g2.draw(rect);// 繪製矩形
        }
    }
}
