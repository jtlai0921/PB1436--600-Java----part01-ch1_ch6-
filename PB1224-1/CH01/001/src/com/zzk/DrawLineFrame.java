package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawLineFrame extends JFrame {
    DrawLinePanel linePanel = new DrawLinePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawLineFrame frame = new DrawLineFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawLineFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製直線"); // 窗體標題
        setBounds(100, 100, 273, 167); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(linePanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawLinePanel extends JPanel {   // 建立內部面板類別
        public void paint(Graphics g) {    // 重新定義paint()方法
            g.drawLine(70, 50, 180, 50);   // 繪製第一條水平線
            g.drawLine(70, 80, 180, 80);   // 繪製第二條水平線
            g.drawLine(110, 10, 140, 120); // 繪製斜線
        }
    }
}
