package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ZoomShapeFrame extends JFrame {
    int flag = 0; // 為0時顯示原大小，為1時放大圖形，為2時縮小圖形
    ZoomShapePanel zoomPanel = new ZoomShapePanel(); // 建立面板類別的實例
    
    public static void main(String args[]) { // 主方法
        ZoomShapeFrame frame = new ZoomShapeFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public ZoomShapeFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("縮放圖形"); // 窗體標題
        setBounds(100, 100, 338, 220); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(zoomPanel); // 將面板類別的實例增加到窗體容器中
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_zoomin = new JButton();
        btn_zoomin.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // 放大圖形標記
                zoomPanel.repaint();  // 重新呼叫面板的paint()方法
            }
        });
        btn_zoomin.setText("放    大");
        panel.add(btn_zoomin);
        
        final JButton btn_zoomout = new JButton();
        btn_zoomout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // 縮小圖形標記
                zoomPanel.repaint();// 重新呼叫面板的paint()方法
            }
        });
        btn_zoomout.setText("縮    小");
        panel.add(btn_zoomout);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // 還原圖形
                zoomPanel.repaint();// 重新呼叫面板的paint()方法
            }
        });
        btn_restore.setText("還    原");
        panel.add(btn_restore);
    }
    
    class ZoomShapePanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D) g; // 獲得Graphics2D對像
            Rectangle2D.Float rect = new Rectangle2D.Float(120, 50, 80, 50);// 建立矩形對像
            BasicStroke stroke = new BasicStroke(10); // 建立寬度是10的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            g2.clearRect(0, 0, 338, 220);  // 清除原有內容
            if (flag == 0) {
                g2.draw(rect);// 繪製原矩形
            } else if (flag == 1) {
                g2.scale(1.3, 1.3);// 放大1.3倍
                g2.draw(rect);// 繪製矩形
            } else if (flag == 2) {
                g2.scale(0.5, 0.5);// 縮小0.5倍
                g2.draw(rect);// 繪製矩形
            }
        }
    }
}
