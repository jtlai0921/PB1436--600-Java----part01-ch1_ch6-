package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPolygonFrame extends JFrame {
    DrawPolygonPanel polygonPanel = new DrawPolygonPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawPolygonFrame frame = new DrawPolygonFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawPolygonFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製多邊形"); // 窗體標題
        setBounds(100, 100, 352, 259); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(polygonPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawPolygonPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            int[] x1 = { 100,120,180,140,150,100,50,60,20,80 }; // 多邊形的橫座標
            int[] y1 = { 20,85,90,120,180,140,180,120,90,85 }; // 多邊形的縱座標
            int n1 = 10;// 多邊形的邊數
            g.fillPolygon(x1, y1, n1);// 繪製多邊形
            int[] x2 = { 210, 270, 310, 270, 210, 170 }; // 多邊形的橫座標
            int[] y2 = { 20, 20, 65, 110, 110, 65 }; // 多邊形的縱座標
            int n2 = 6;// 多邊形的邊數
            g.fillPolygon(x2, y2, n2);// 繪製實心多邊形
            int[] x3 = { 180, 220, 260, 240, 260, 220, 180, 200 }; // 多邊形的橫座標
            int[] y3 = { 120, 140, 120, 160, 200, 180, 200, 160 }; // 多邊形的縱座標
            int n3 = 8;// 多邊形的邊數
            g.drawPolygon(x3, y3, n3);// 繪製多邊形
        }
    }
}
