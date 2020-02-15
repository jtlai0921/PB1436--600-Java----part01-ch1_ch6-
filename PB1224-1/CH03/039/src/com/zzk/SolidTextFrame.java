package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SolidTextFrame extends JFrame {
    private SolidTextPanel solidTextPanel = new SolidTextPanel();
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SolidTextFrame frame = new SolidTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public SolidTextFrame() {
        super();
        setBounds(100, 100, 354, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("立體效果的文字");
        getContentPane().add(solidTextPanel);
    }
    class SolidTextPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            String value = "明日科技";
            int x = 16; // 純文字位置的橫座標
            int y = 100; // 純文字位置的縱座標
            Font font = new Font("細明體", Font.BOLD, 72); // 建立字體對像
            g.setFont(font); // 設定字體
            g.setColor(Color.GRAY);// 設定顏色為灰色
            int i = 0;// 循環變數
            while (i<8){
                g.drawString(value, x, y); // 繪製純文字
                x+=1;// 調整繪製點的橫座標值
                y+=1;// 調整繪製點的縱座標值
                i++;// 調整循環變數的值
            }
            g.setColor(Color.BLACK);// 設定顏色黑色
            g.drawString(value, x, y); // 繪製純文字
        }
    }
}
