package com.zzk;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShearTextFrame extends JFrame {
    private ShearTextPanel shearTextPanel = new ShearTextPanel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShearTextFrame frame = new ShearTextFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ShearTextFrame() {
        super();
        setBounds(100, 100, 365, 205);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("傾斜效果的文字");
        getContentPane().add(shearTextPanel);
    }
    
    class ShearTextPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g;// 轉為Graphics2D型態
            String value = "編程詞典";// 繪製的純文字
            int x = 10; // 純文字位置的橫座標
            int y = 170; // 純文字位置的縱座標
            Font font = new Font("華文行楷", Font.BOLD, 72); // 建立字體對像
            g2.setFont(font); // 設定字體
            g2.shear(0.1, -0.4);// 傾斜畫布
            g2.drawString(value, x, y); // 繪製純文字
        }
    }
}
