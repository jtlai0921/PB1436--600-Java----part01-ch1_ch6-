package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GradientTextFrame extends JFrame {
    private GradientTextPanel gradientTextPanel = new GradientTextPanel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GradientTextFrame frame = new GradientTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public GradientTextFrame() {
        super();
        setBounds(100, 100, 365, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("漸層效果的文字");
        getContentPane().add(gradientTextPanel);
    }
    
    class GradientTextPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D) g;// 轉為Graphics2D型態
            String value = "Java全能";// 繪製的純文字
            int x = 15; // 純文字位置的橫座標
            int y = 60; // 純文字位置的縱座標
            Font font = new Font("楷體", Font.BOLD, 60); // 建立字體對像
            g2.setFont(font); // 設定字體
            // 建立循環漸層的GraphientPaint對像
            GradientPaint paint = new GradientPaint(20, 20, Color.BLUE, 100,120, Color.RED, true);
            g2.setPaint(paint);// 設定漸層
            g2.drawString(value, x, y); // 繪製純文字
            font = new Font("華文行楷", Font.BOLD, 60); // 建立新的字體對像
            g2.setFont(font); // 設定字體
            x = 80; // 純文字位置的橫座標
            y = 130; // 純文字位置的縱座標
            value = "編程詞典";// 繪製的純文字
            g2.drawString(value, x, y); // 繪製純文字
        }
    }
}
