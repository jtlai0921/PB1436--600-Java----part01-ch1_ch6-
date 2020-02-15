package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShadowTextFrame extends JFrame {
    private ShadowTextPanel shadowTextPanel = new ShadowTextPanel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShadowTextFrame frame = new ShadowTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ShadowTextFrame() {
        super();
        setBounds(100, 100, 354, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("陰影效果的文字");
        getContentPane().add(shadowTextPanel);
    }
    
    class ShadowTextPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            String value = "編程詞典";
            int x = 16; // 純文字位置的橫座標
            int y = 100; // 純文字位置的縱座標
            Font font = new Font("華文行楷", Font.BOLD, 72); // 建立字體對像
            g.setFont(font); // 設定字體
            g.setColor(Color.GRAY);// 設定顏色為灰色
            int i = 0;// 循環變數
            g.drawString(value, x, y); // 繪製純文字
            x -= 3;// 調整繪製點的橫座標值
            y -= 3;// 調整繪製點的縱座標值
            g.setColor(Color.BLACK);// 設定顏色黑色
            g.drawString(value, x, y); // 繪製純文字
        }
    }
}
