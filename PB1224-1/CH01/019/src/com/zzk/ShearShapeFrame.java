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

public class ShearShapeFrame extends JFrame {
    int flag = 0; // 為0時顯示原圖形，為1時向下斜切，為2時向上斜切
    ShearShapePanel shearShapePanel = new ShearShapePanel(); // 建立面板類別的實例
    public static void main(String args[]) { // 主方法
        ShearShapeFrame frame = new ShearShapeFrame(); // 建立窗體類別的實例
        frame.setVisible(true); // 顯示窗體
    }
    
    public ShearShapeFrame() {
        super(); // 呼叫超類別的建構方法
        setTitle("斜切圖形"); // 窗體標題
        setBounds(100, 100, 338, 230); // 窗體的顯示位置和大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 窗體關閉方式
        add(shearShapePanel); // 將面板類別的實例增加到窗體容器中
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_deasil = new JButton();
        btn_deasil.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // 下斜切標記
                shearShapePanel.repaint();  // 重新呼叫面板的paint()方法
            }
        });
        btn_deasil.setText("下斜切");
        panel.add(btn_deasil);
        
        final JButton btn_widdershins = new JButton();
        btn_widdershins.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // 上斜切標記
                shearShapePanel.repaint();// 重新呼叫面板的paint()方法
            }
        });
        btn_widdershins.setText("上斜切");
        panel.add(btn_widdershins);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // 還原圖形
                shearShapePanel.repaint();// 重新呼叫面板的paint()方法
            }
        });
        btn_restore.setText("還    原");
        panel.add(btn_restore);
    }
    
    class ShearShapePanel extends JPanel { // 建立內部面板類別
        public void paint(Graphics g) { // 重新定義paint()方法
            Graphics2D g2 = (Graphics2D) g; // 獲得Graphics2D對像
            Rectangle2D.Float rect = new Rectangle2D.Float(120, 50, 80, 50);// 建立矩形對像
            BasicStroke stroke = new BasicStroke(10); // 建立寬度是10的筆畫對像
            g2.setStroke(stroke);// 設定筆畫對像
            g2.clearRect(0, 0, 338, 230);  // 清除原有內容
            if (flag == 0) {
                g2.draw(rect);// 繪製原矩形
            } else if (flag == 1) {
                g2.shear(0.2,0.2);// 向下斜切
                g2.draw(rect);// 繪製矩形
            } else if (flag == 2) {
                g2.shear(-0.2,-0.2);// 向上斜切
                g2.draw(rect);// 繪製矩形
            }
        }
    }
}
