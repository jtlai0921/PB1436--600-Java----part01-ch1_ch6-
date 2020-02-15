package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlusOperationFrame extends JFrame {
    PlusOperationPanel plusOperationPanel = new PlusOperationPanel(); // 建立面板類別的實例
    public static void main(String args[]) { // 主方法
        PlusOperationFrame frame = new PlusOperationFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public PlusOperationFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("圖形的加運算"); // 窗體標題
        setBounds(100, 100, 395, 240); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(plusOperationPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class PlusOperationPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) {    // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(20, 70, 160, 60);// 建立橢圓對像
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(120, 20, 60, 160);// 建立橢圓對像
            Area area1 = new Area(ellipse1);   // 建立區域橢圓
            Area area2 = new Area(ellipse2);   // 建立區域橢圓
            area1.add(area2);// 兩個區域橢圓進行加運算
            g2.draw(area1);  // 繪製加運算後的區域橢圓
            Ellipse2D.Float ellipse3 = new Ellipse2D.Float(200, 70, 160, 60);// 建立橢圓對像
            Ellipse2D.Float ellipse4 = new Ellipse2D.Float(250, 20, 60, 160);// 建立橢圓對像
            Area area3 = new Area(ellipse3);// 建立區域橢圓
            Area area4 = new Area(ellipse4);// 建立區域橢圓
            area3.add(area4);// 兩個區域橢圓進行加運算
            g2.draw(area3);  // 繪製加運算後的區域橢圓
        }
    }
}
