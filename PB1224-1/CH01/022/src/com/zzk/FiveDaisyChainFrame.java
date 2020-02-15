package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FiveDaisyChainFrame extends JFrame {
    FiveDaisyChainPanel fivePanel = new FiveDaisyChainPanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        FiveDaisyChainFrame frame = new FiveDaisyChainFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public FiveDaisyChainFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("繪製五環圖案"); // 窗體標題
        setBounds(100, 100, 269, 222); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(fivePanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class FiveDaisyChainPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) {     // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            BasicStroke stroke = new BasicStroke(3); // 建立寬度是3的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            Color color = new Color(0,162,232);// 建立顏色對像
            g2.setColor(color);// 設定顏色
            g2.drawOval(30, 40, 60, 60);     // 繪製第一個圓
            color = new Color(233,123,16);   // 建立新的顏色對像
            g2.setColor(color);// 設定顏色
            g2.drawOval(60, 70, 60, 60);     // 繪製第二個圓
            color = new Color(28,20,100);// 建立新的顏色對像
            g2.setColor(color);// 設定顏色
            g2.drawOval(92, 40, 60, 60);     // 繪製第三個圓
            color = new Color(0,255,0);// 建立新的顏色對像
            g2.setColor(color);// 設定顏色
            g2.drawOval(122, 70, 60, 60);    // 繪製第四個圓
            color = new Color(255,0,0);// 建立新的顏色對像
            g2.setColor(color);// 設定顏色
            g2.drawOval(154, 40, 60, 60);    // 繪製第五個圓
        }
    }
}
