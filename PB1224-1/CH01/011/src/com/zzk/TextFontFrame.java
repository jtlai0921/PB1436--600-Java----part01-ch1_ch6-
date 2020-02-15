package com.zzk;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class TextFontFrame extends JFrame {
    ChangeTextFontPanel changeTextFontPanel = new ChangeTextFontPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        TextFontFrame frame = new TextFontFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    public TextFontFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("設定純文字的字體"); // 窗體標題
        setBounds(100, 100, 333, 199); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(changeTextFontPanel); // 將面板類別的實例增加到窗體容器中
    }
    class ChangeTextFontPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            String value = "明日編程詞典社區";
            int x = 40; // 純文字位置的橫座標
            int y = 50; // 純文字位置的縱座標
            Font font = new Font("華文行楷", Font.BOLD + Font.ITALIC, 26); // 建立字體對像
            g.setFont(font); // 設定字體
            g.drawString(value, x, y); // 繪製純文字
            value = "http://community.mrbccd.com";
            x = 10; // 純文字位置的橫座標
            y = 100; // 純文字位置的縱座標
            font = new Font("細明體", Font.BOLD, 20); // 建立字體對像
            g.setFont(font); // 設定字體
            g.drawString(value, x, y); // 繪製純文字
        }
    }
}
