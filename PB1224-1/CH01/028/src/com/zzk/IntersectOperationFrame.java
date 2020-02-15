package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IntersectOperationFrame extends JFrame {
    IntersectOperationPanel intersectOperationPanel = new IntersectOperationPanel(); // 建立面板類別的實例
    public static void main(String args[]) { // 主方法
        IntersectOperationFrame frame = new IntersectOperationFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public IntersectOperationFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("圖形的交運算"); // 窗體標題
        setBounds(100, 100, 395, 240); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(intersectOperationPanel); // 將面板類別的實例增加到窗體容器中
    }
    
    class IntersectOperationPanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) {    // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D)g; // 獲得Graphics2D對像
            Rectangle2D.Float rect = new Rectangle2D.Float(30, 30, 160, 120);// 建立矩形對像
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20, 30, 180, 180);// 建立圓對像
            Area area1 = new Area(rect);   // 建立區域矩形
            Area area2 = new Area(ellipse);   // 建立區域圓
            area1.intersect(area2);// 兩個區域圖形進行交運算
            g2.draw(area1);  // 繪製交運算後的區域圖形
            Ellipse2D.Float ellipse1 = new Ellipse2D.Float(190, 20, 100, 140);// 建立橢圓對像
            Ellipse2D.Float ellipse2 = new Ellipse2D.Float(240, 20, 100, 140);// 建立橢圓對像
            Area area3 = new Area(ellipse1);// 建立區域橢圓
            Area area4 = new Area(ellipse2);// 建立區域橢圓
            area3.intersect(area4);// 兩個區域橢圓進行交運算
            g2.fill(area3);  // 繪製交運算後的區域橢圓
        }
    }
}
