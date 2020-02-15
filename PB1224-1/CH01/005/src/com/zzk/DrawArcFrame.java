package com.zzk;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawArcFrame extends JFrame {
    DrawArcPanel arcPanel = new DrawArcPanel(); // 建立面板類別的實例
    public static void main(String args[]) { // 主方法
        DrawArcFrame frame = new DrawArcFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    public DrawArcFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製圓弧"); // 窗體標題
        setBounds(100, 100, 269, 184); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(arcPanel); // 將面板類別的實例增加到窗體容器中
    }
    class DrawArcPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            g.drawArc(20, 20, 80, 80, 0, 120);    // 繪製圓弧
            g.drawArc(20, 40, 80, 80, 0, -120);   // 繪製圓弧
            g.drawArc(150, 20, 80, 80, 180, -120);// 繪製圓弧
            g.drawArc(150, 40, 80, 80, 180, 120); // 繪製圓弧
        }
    }
}
