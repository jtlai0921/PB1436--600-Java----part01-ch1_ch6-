package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawEllipseFrame extends JFrame {
    DrawEllipsePanel ellipsePanel = new DrawEllipsePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawEllipseFrame frame = new DrawEllipseFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawEllipseFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製橢圓"); // 窗體標題
        setBounds(100, 100, 269, 222); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(ellipsePanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawEllipsePanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) {     // 重新定義paint()方法
            g.drawOval(30, 20, 80, 50);     // 繪製空心橢圓
            g.drawOval(150, 10, 50, 80);    // 繪製空心橢圓
            g.fillOval(40, 90, 50, 80);     // 繪製實心橢圓
            g.fillOval(140, 110, 80, 50);   // 繪製實心橢圓
        }
    }
}
