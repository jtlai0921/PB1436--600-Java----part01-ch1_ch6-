package com.zzk;

import javax.swing.*;

/**
 * @author 張振坤
 */
public class BallFrame extends JFrame {
    private JPanel panel = null;// 背景面板
    private BallPanel ballPanel = null;// 窗體提供一個小球
    
    public static void main(String[] args) {
        BallFrame thisClass = new BallFrame();
        thisClass.setVisible(true);
    }
    
    /**
     * 建構方法
     */
    public BallFrame() {
        super();
        setSize(320, 223);// 設定窗體大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);// 禁止調整窗體大小
        setTitle("桌面彈球動畫");// 設定窗體標題純文字
        ballPanel = new BallPanel();// 建立小球
        ballPanel.setBounds(121, 67, 20, 20);// 設定小球位置與大小
        panel = (JPanel) getContentPane();// 獲得窗體的內容面板
        panel.setLayout(null);// 窗體內容面板使用null佈局
        panel.add(ballPanel, null);// 增加小球到窗體的內容面板
    }
}
