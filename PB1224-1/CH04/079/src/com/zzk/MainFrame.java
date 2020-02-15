package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Cursor;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private BackgroundPanel backgroundPanel = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame thisClass = new MainFrame();
                thisClass.setVisible(true);
            }
        });
    }
    
    public MainFrame() {
        super();
        setTitle("雪花飄落動畫");
        setSize(628, 441);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = new ImageIcon(getClass().getResource("/image/cursor.png")).getImage();// 建立圖形對像
        Cursor cursor = getToolkit().createCustomCursor(image, new Point(),"魔棒");// 建立鼠標光標對像
        setCursor(cursor);// 指定鼠標光標
        setResizable(false);// 不允許改變窗體大小
        backgroundPanel = new BackgroundPanel();// 建立背景面板
        // 為背景面板指定圖形
        backgroundPanel.setImage(new ImageIcon(getClass().getResource("/image/bg.jpg")).getImage());
        backgroundPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {// 鼠標移動事件
                SnowFlakeLabel snow = new SnowFlakeLabel();// 建立雪花飄落標籤
                Point point = e.getPoint();// 獲得鼠標位置
                snow.setLocation(point);// 指定雪花在背景面板上的位置
                backgroundPanel.add(snow);// 將雪花增加到背景面板上
            }
        });
        getContentPane().setLayout(new BorderLayout());// 指定窗體內容面板為邊界佈局
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);// 在窗體內容面板上增加背景面板
    }
}