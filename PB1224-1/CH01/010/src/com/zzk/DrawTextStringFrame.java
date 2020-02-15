package com.zzk;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawTextStringFrame extends JFrame {
    DrawTextStringPanel textStringPanel = new DrawTextStringPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        DrawTextStringFrame frame = new DrawTextStringFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public DrawTextStringFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製純文字"); // 窗體標題
        setBounds(100, 100, 308, 186); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(textStringPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class DrawTextStringPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            String value = "靜夜思";
            int x = 120;  // 純文字位置的橫座標
            int y = 30;  // 純文字位置的縱座標
            g.drawString(value, x, y);   // 繪製純文字
            value = "李白";
            x = 170;  // 純文字位置的橫座標
            y = 50;  // 純文字位置的縱座標
            g.drawString(value, x, y);   // 繪製純文字
            value = "床前明月光，";
            x = 70;  // 純文字位置的橫座標
            y = 80;  // 純文字位置的縱座標
            g.drawString(value, x, y);   // 繪製純文字
            value = "疑是地上霜。";
            x = 145;  // 純文字位置的橫座標
            y = 80;  // 純文字位置的縱座標
            g.drawString(value, x, y);   // 繪製純文字
            value = "舉頭望明月，";
            x = 70;  // 純文字位置的橫座標
            y = 110;  // 純文字位置的縱座標
            g.drawString(value, x, y);   // 繪製純文字
            value = "低頭思故鄉。";
            x = 145;  // 純文字位置的橫座標
            y = 110;  // 純文字位置的縱座標
            g.drawString(value, x, y);   // 繪製純文字
        }
    }
}
