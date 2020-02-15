package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSquareFrame extends JFrame {
    DrawSquarePanel squarePanel = new DrawSquarePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawSquareFrame frame = new DrawSquareFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawSquareFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製正方形"); // 窗體標題
        setBounds(100, 100, 280, 180); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(squarePanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawSquarePanel extends JPanel {// 建立內部面板類別
        public void paint(Graphics g) {   // 重新定義paint()方法
            g.drawRect(20, 20, 100, 100); // 繪製空心正方形
            g.drawRect(40, 40, 60, 60);   // 繪製空心正方形
            g.drawRect(140, 20, 100, 100);   // 繪製空心正方形
            g.fillRect(160, 40, 60, 60);  // 繪製實心正方形
        }
    }
}
