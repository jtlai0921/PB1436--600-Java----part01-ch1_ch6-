package com.zzk;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawSectorFrame extends JFrame {
    DrawSectorPanel sectorPanel = new DrawSectorPanel(); // 建立面板類別的實例
    public static void main(String args[]) { // 主方法
        DrawSectorFrame frame = new DrawSectorFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    public DrawSectorFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製填充扇形"); // 窗體標題
        setBounds(100, 100, 278, 184); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(sectorPanel); // 將面板類別的實例增加到窗體容器中
    }
    class DrawSectorPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            g.fillArc(40, 20, 80, 80, 0, 150);    // 繪製填充扇形
            g.fillArc(140, 20, 80, 80, 180, -150);// 繪製填充扇形
            g.fillArc(40, 40, 80, 80, 0, -110);   // 繪製填充扇形
            g.fillArc(140, 40, 80, 80, 180, 110); // 繪製填充扇形
        }
    }
}
