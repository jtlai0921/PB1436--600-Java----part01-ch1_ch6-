package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawRectangleFrame extends JFrame {
    DrawRectanglePanel rectPanel = new DrawRectanglePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawRectangleFrame frame = new DrawRectangleFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawRectangleFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製矩形"); // 窗體標題
        setBounds(100, 100, 269, 184); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(rectPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawRectanglePanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) {       // 重新定義paint()方法
            g.drawRect(30, 40, 80, 60);       // 繪製空心矩形
            g.fillRect(140, 40, 80, 60);      // 繪製實心矩形
        }
    }
}
