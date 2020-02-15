package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawQuadCurveFrame extends JFrame {
    DrawQuadCurvePanel quadCurvePanel = new DrawQuadCurvePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawQuadCurveFrame frame = new DrawQuadCurveFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawQuadCurveFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製二次曲線"); // 窗體標題
        setBounds(100, 100, 269, 175); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(quadCurvePanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawQuadCurvePanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2=(Graphics2D)g;// 獲得Graphics2D對像
            // 建立二次曲線，其中點120,100是控制點，點60,20是起始點座標，點180,20是終點座標
            QuadCurve2D.Double quadCurve1 = new QuadCurve2D.Double(60,20,120,100,180,20);
            g2.draw(quadCurve1); // 繪製二次曲線
            // 建立二次曲線，其中點120,40是控制點，點60,120是起始點座標，點180,120是終點座標
            QuadCurve2D.Double quadCurve2 = new QuadCurve2D.Double(60,120,120,40,180,120);
            g2.draw(quadCurve2); // 繪製二次曲線
        }
    }
}
