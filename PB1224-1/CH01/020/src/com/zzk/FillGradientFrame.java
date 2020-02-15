package com.zzk;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FillGradientFrame extends JFrame {
    FillGradientPanel fillGradientPanel = new FillGradientPanel(); // 建立面板類別的實例
    public static void main(String args[]) { // 主方法
        FillGradientFrame frame = new FillGradientFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public FillGradientFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("為圖形填充漸層色"); // 窗體標題
        setBounds(100, 100, 338, 220); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(fillGradientPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class FillGradientPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D) g; // 獲得Graphics2D對像
            Rectangle2D.Float rect = new Rectangle2D.Float(20, 20, 280, 140);// 建立矩形對像
            // 建立循環漸層的GraphientPaint對像
            GradientPaint paint = new GradientPaint(20,20,Color.BLUE,100,80,Color.RED,true);
            g2.setPaint(paint);// 設定漸層
            g2.fill(rect);// 繪製矩形
        }
    }
}
