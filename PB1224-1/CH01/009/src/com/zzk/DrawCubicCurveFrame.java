package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCubicCurveFrame extends JFrame {
    DrawCubicCurvePanel cubicCurvePanel = new DrawCubicCurvePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawCubicCurveFrame frame = new DrawCubicCurveFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawCubicCurveFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製三次曲線"); // 窗體標題
        setBounds(100, 100, 297, 199); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(cubicCurvePanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawCubicCurvePanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2=(Graphics2D)g;// 獲得Graphics2D對像
            // 建立三次曲線，其中點140,-140和點140,300是控制點，點20,80是起始點座標，點260,80是終點座標
            CubicCurve2D.Double cubicCurve = new CubicCurve2D.Double(20,80,140,-140,140,300,260,80);
            g2.draw(cubicCurve); // 繪製三次曲線
        }
    }
}
