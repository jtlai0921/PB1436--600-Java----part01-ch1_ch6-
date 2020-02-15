package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class ClockwiseTextFrame extends JFrame {
    private JTextField textField;
    ClockwiseTextPanel clockwiseTextPanel = new ClockwiseTextPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        ClockwiseTextFrame frame = new ClockwiseTextFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public ClockwiseTextFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("順時針旋轉文字"); // 窗體標題
        setBounds(100, 100, 340, 240); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(clockwiseTextPanel); // 將面板類別的實例增加到窗體容器中
        textField = new JTextField();
        textField.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent arg0) {
                String text = textField.getText();// 獲得純文字框字串
                clockwiseTextPanel.setText(text);// 為面板中的text變數給予值
            }
        });
        getContentPane().add(textField, BorderLayout.SOUTH);
    }
    
    class ClockwiseTextPanel extends JPanel { // 建立內部面板類別
        private String text;
        public ClockwiseTextPanel() {
            setOpaque(false);// 設定面板為透明
            setLayout(null);// 設定為絕對佈局
        }
        public String getText() {
            return text; // 獲得成員變數的值
        }
        public void setText(String text) {
            this.text = text;// 為成員變數給予值
            repaint();// 調整paint()方法
        }
        public void paint(Graphics g) {// 重新定義paint()方法
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D的實例
            int width = getWidth();// 獲得面板的寬度
            int height = getHeight();// 獲得面板的高度
            if (text != null) {
                char[] array = text.toCharArray();// 將純文字轉為字符陣列
                int len = array.length * 5;// 定義圓的半徑，同時可以調整文字的距離
                Font font = new Font("細明體", Font.BOLD, 22);// 建立字體
                g2.setFont(font);// 設定字體
                double angle = 0;// 定義初始角度
                for (int i = 0; i < array.length; i++) {// 檢查字串中的字符
                    if (i == 0) {
                        g2.setColor(Color.BLUE);// 第一個字符用藍色
                    } else {
                        g2.setColor(Color.BLACK);// 其他字符用黑色
                    }
                    int x = (int) (len * Math.sin(Math.toRadians(angle + 270)));// 計算每個文字的橫座標位置
                    int y = (int) (len * Math.cos(Math.toRadians(angle + 270)));// 計算每個文字的縱座標位置
                    g2.drawString(array[i] + "", width / 2 + x, height / 2 - y);// 繪製字符
                    angle = angle + 360d / array.length;// 改變角度
                }
            }
        }
    }
}
