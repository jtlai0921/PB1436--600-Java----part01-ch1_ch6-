package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawCachetFrame extends JFrame {
    DrawCachetPanel drawCachetPanel = new DrawCachetPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawCachetFrame frame = new DrawCachetFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawCachetFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製公章"); // 窗體標題
        setBounds(100, 100, 340, 240); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(drawCachetPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawCachetPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D) g; // 獲得Graphics2D對像
            g2.translate(170, 100);// 平移座標軸
            BasicStroke stroke = new BasicStroke(6); // 建立寬度是6的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            // 繪製圓
            Ellipse2D.Float ellipse = new Ellipse2D.Float(-80, -80, 160, 160);// 建立圓對像
            Color color = new Color(255, 0, 0);// 建立顏色對像
            g2.setColor(color);// 指定顏色
            g2.draw(ellipse);// 繪製圓
            // 繪製五星
            int[] x1 = { 0, 8, 30, 16, 25, 0, -25, -16, -30, -8 }; // 多邊形的橫座標
            int[] y1 = { -35, -10, -15, 5, 28, 10, 28, 5, -15, -10 }; // 多邊形的縱座標
            int n1 = 10;// 多邊形的邊數
            g2.fillPolygon(x1, y1, n1);// 繪製多邊形
            // 繪製純文字
            g2.scale(1.8, 1.8);// 放大
            Font font = new Font("細明體", Font.BOLD, 12);// 建立字體
            g2.setFont(font);// 設定字體
            g2.drawString("專 用 章", -25, 30);// 繪製純文字
            int width = getWidth();// 獲得面板寬度
            int height = getHeight();// 獲得面板高度
            char[] array = " 明日科技有限公司         ".toCharArray();// 把字串轉為字符陣列
            int len = array.length * 2;// 定義半徑
            font = new Font("細明體", Font.BOLD, 10);// 建立新字體
            g2.setFont(font);// 設定字體
            double angle = 0;// 初始角度
            for (int i = 0; i < array.length; i++) {// 檢查字串中的字符
                int x = (int) (len * Math.sin(Math.toRadians(angle + 270)));// 計算每個文字的位置
                int y = (int) (len * Math.cos(Math.toRadians(angle + 270)));// 計算每個文字的位置
                g2.drawString(array[i] + "", width / 2 + x - 168, height / 2
                        - y - 95);// 繪製每個字符，其中168和95是座標平移值
                angle = angle + 360d / array.length;// 改變角度
            }
        }
    }
}
