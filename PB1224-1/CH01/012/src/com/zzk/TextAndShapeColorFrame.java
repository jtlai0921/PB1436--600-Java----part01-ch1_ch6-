package com.zzk;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TextAndShapeColorFrame extends JFrame {
    TextAndShapeColorPanel colorPanel = new TextAndShapeColorPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        TextAndShapeColorFrame frame = new TextAndShapeColorFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public TextAndShapeColorFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("設定純文字和圖形的顏色"); // 窗體標題
        setBounds(100, 100, 300, 193); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(colorPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class TextAndShapeColorPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            String value = "只要努力————";
            int x = 60;    // 純文字位置的橫座標
            int y = 60;     // 純文字位置的縱座標
            Color color = new Color(255,0,0); // 建立顏色對像
            g.setColor(color); // 設定顏色
            g.drawString(value, x, y);   // 繪製純文字
            value = "一切皆有可能";
            x = 140;    // 純文字位置的橫座標
            y = 100;     // 純文字位置的縱座標
            color = new Color(0,0,255);// 建立顏色對像
            g.setColor(color);// 設定顏色
            g.drawString(value, x, y);   // 繪製純文字
            color = Color.ORANGE; // 透過Color類別的字段獲得顏色對像
            g.setColor(color);// 設定顏色
            g.drawRoundRect(40,30,200,100,40,30);// 繪製圓角矩形
            g.drawRoundRect(45,35,190,90,36,26);// 繪製圓角矩形
        }
    }
}
