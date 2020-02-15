package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PictureMatchingFrame extends JFrame implements MouseListener,
        MouseMotionListener {
    private JLabel img[] = new JLabel[3];// 顯示圖標的標籤
    private JLabel targets[] = new JLabel[3];// 窗體下面顯示文字的標籤
    private Point pressPoint; // 鼠標按下時的起始座標
    
    public static void main(String args[]) {
        PictureMatchingFrame frame = new PictureMatchingFrame(); // 建立本類別對像
        frame.setVisible(true); // 設定窗體為可視狀態
    }
    
    public PictureMatchingFrame() {
        super();
        getContentPane().setLayout(new BorderLayout());
        setBounds(100, 100, 364, 312);
        setTitle("圖片配對遊戲");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(null);
        imagePanel.setOpaque(false);
        setGlassPane(imagePanel);
        getGlassPane().setVisible(true);
        ImageIcon icon[] = new ImageIcon[3];
        icon[0] = new ImageIcon(getClass().getResource("screen.png"));
        icon[1] = new ImageIcon(getClass().getResource("clothing.png"));
        icon[2] = new ImageIcon(getClass().getResource("bike.png"));
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        for (int i = 0; i < 3; i++) {
            img[i] = new JLabel(icon[i]); // 建立圖形標籤
            img[i].setSize(50, 50); // 設定標籤大小
            img[i].setBorder(new LineBorder(Color.GRAY)); // 設定線性邊框
            int x = (int) (Math.random() * (getWidth() - 50)); // 隨機產生X座標
            int y = (int) (Math.random() * (getHeight() - 150));// 隨機產生Y座標
            img[i].setLocation(x, y); // 設定隨機座標
            img[i].addMouseListener(this); // 為每個圖形標籤增加鼠標事件監聽器
            img[i].addMouseMotionListener(this);
            imagePanel.add(img[i]); // 增加圖形標籤到圖形面板
            targets[i] = new JLabel(); // 建立比對位置標籤
            targets[i].setOpaque(true); // 使標籤不透明，以設定背景色
            targets[i].setBackground(Color.ORANGE); // 設定標籤背景色
            targets[i].setHorizontalTextPosition(SwingConstants.CENTER); // 設定純文字與圖形水平居中
            targets[i].setVerticalTextPosition(SwingConstants.BOTTOM); // 設定純文字顯示在圖形下方
            targets[i].setPreferredSize(new Dimension(80, 80)); // 設定標籤首先大小
            targets[i].setHorizontalAlignment(SwingConstants.CENTER); // 文字居中對齊
            bottomPanel.add(targets[i]); // 增加標籤到底部面板
        }
        targets[0].setText("顯示器"); // 設定比對位置的純文字
        targets[1].setText("衣服");
        targets[2].setText("自行車");
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
        pressPoint = e.getPoint(); // 儲存拖放圖片標籤時的起始座標
    }
    
    public void mouseReleased(MouseEvent e) {
        if (checkPosition()) { // 如果配對正確
            getGlassPane().setVisible(false);
            for (int i = 0; i < 3; i++) { // 檢查所有比對位置的標籤
                targets[i].setText("比對成功"); // 設定正確提示
                targets[i].setIcon(img[i].getIcon()); // 設定比對的圖標
            }
        }
    }
    
    /**
     * 鼠標拖動控制項時的事件處理方法
     */
    public void mouseDragged(MouseEvent e) {
        JLabel source = (JLabel) e.getSource(); // 獲得事件源控制項
        Point imgPoint = source.getLocation(); // 獲得控制項座標
        Point point = e.getPoint(); // 獲得鼠標座標
        source.setLocation(imgPoint.x + point.x - pressPoint.x, imgPoint.y
                + point.y - pressPoint.y); // 設定控制項新座標
    }
    
    private boolean checkPosition() {// 檢查配對是否正確
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            Point location = img[i].getLocationOnScreen(); // 獲得每個圖形標籤的位置
            Point seat = targets[i].getLocationOnScreen(); // 獲得每個對應位置的座標
            targets[i].setBackground(Color.GREEN); // 設定比對後的顏色
            // 如果配對錯誤
            if (location.x < seat.x || location.y < seat.y
                    || location.x > seat.x + 80 || location.y > seat.y + 80) {
                targets[i].setBackground(Color.ORANGE); // 回覆對應位置的顏色
                result = false; // 檢測結果為false
            }
        }
        return result; // 傳回檢測結果
    }
}
