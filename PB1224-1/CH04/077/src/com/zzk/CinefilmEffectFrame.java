package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class CinefilmEffectFrame extends JFrame {
    Thread thread = new Thread(new CinefileThread());
    private final JLabel ggLabel = new JLabel();
    private final JLabel label_1 = new JLabel(); // 顯示圖片的第1個標籤
    private final JLabel label_2 = new JLabel(); // 顯示圖片的第2個標籤
    private final JLabel label_3 = new JLabel(); // 顯示圖片的第3個標籤
    private final JLabel label_4 = new JLabel(); // 顯示圖片的第4個標籤
    private final JLabel label_5 = new JLabel(); // 顯示圖片的第5個標籤
    private final JLabel ffLabel = new JLabel();
    int x1 = 0; // 第1個標籤顯示位置的變數
    int x2 = 98; // 第2個標籤顯示位置的變數
    int x3 = 196; // 第3個標籤顯示位置的變數
    int x4 = 294; // 第4個標籤顯示位置的變數
    int x5 = 392; // 第5個標籤顯示位置的變數
    boolean indexFlag = false; // 標誌標籤是否換圖的變數
    
    public CinefilmEffectFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // 啟動線程對像
            }
        });
        setTitle("電影膠片特效"); // 設定窗體的標題
        setBounds(260, 240, 400, 280); // 圖片的寬度和高度
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 設定窗體的關閉方式
        
        ggLabel.setOpaque(true);
        ggLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/膠片.JPG"));
        ggLabel.setText("  ");
        getContentPane().add(ggLabel, BorderLayout.NORTH);
        
        ffLabel.setText("  ");
        ffLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/膠片.JPG"));
        ffLabel.setOpaque(true);
        getContentPane().add(ffLabel, BorderLayout.SOUTH);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        label_1.setBounds(0, 0, 98, 210);
        label_1.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/1.jpg"));
        label_1.setText("New JLabelfdbb");
        panel.add(label_1);
        label_2.setBounds(98, 0, 98, 210);
        label_2.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/2.jpg"));
        label_2.setText("22222222222222");
        panel.add(label_2);
        label_3.setBounds(196, 0, 98, 210);
        label_3.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/3.jpg"));
        label_3.setText("11111111111111");
        panel.add(label_3);
        label_4.setBounds(294, 0, 98, 210);
        label_4.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/4.jpg"));
        label_4.setText("New JLabelfdww");
        panel.add(label_4);
        label_5.setBounds(392, 0, 98, 210);
        label_5.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/5.jpg"));
        label_5.setText("33333333333333");
        panel.add(label_5);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        CinefilmEffectFrame frame = new CinefilmEffectFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author 張振坤
     *         用於實現動畫的線程
     */
    private class CinefileThread implements Runnable {
        public void run() {
            while (true) {
                x1 = x1 - 7; // 第1個標籤的左邊界減7，使其左移
                x2 = x2 - 7; // 第2個標籤的左邊界減7，使其左移
                x3 = x3 - 7; // 第3個標籤的左邊界減7，使其左移
                x4 = x4 - 7; // 第4個標籤的左邊界減7，使其左移
                x5 = x5 - 7; // 第5個標籤的左邊界減7，使其左移
                label_1.setBounds(x1, 0, 98, 210); // 設定第1個標籤的顯示位置
                label_2.setBounds(x2, 0, 98, 210); // 設定第1個標籤的顯示位置
                label_3.setBounds(x3, 0, 98, 210); // 設定第1個標籤的顯示位置
                label_4.setBounds(x4, 0, 98, 210); // 設定第1個標籤的顯示位置
                label_5.setBounds(x5, 0, 98, 210); // 設定第1個標籤的顯示位置
                
                if (x1 == -98) { // 當第1個標籤的顯示位置是-98時執行
                    indexFlag = !indexFlag; // 改變indexFlag的值
                    x1 = 392; // 設定第1個標籤的顯示位置
                    if (indexFlag) {
                        // indexFlag為true時改變的圖片
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/6.jpg"));
                    } else {
                        // indexFlag為false時改變的圖片
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/1.jpg"));
                    }
                }
                if (x2 == -98) { // 當第2個標籤的顯示位置是-98時執行
                    indexFlag = !indexFlag; // 改變indexFlag的值
                    x2 = 392; // 設定第2個標籤的顯示位置
                    if (indexFlag) {
                        // indexFlag為true時改變的圖片
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/7.jpg"));
                    } else {
                        // indexFlag為false時改變的圖片
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/2.jpg"));
                    }
                }
                if (x3 == -98) { // 當第3個標籤的顯示位置是-98時執行
                    indexFlag = !indexFlag; // 改變indexFlag的值
                    x3 = 392; // 設定第3個標籤的顯示位置
                    if (indexFlag) {
                        // indexFlag為true時改變的圖片
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/8.jpg"));
                    } else {
                        // indexFlag為false時改變的圖片
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/3.jpg"));
                    }
                }
                if (x4 == -98) { // 當第4個標籤的顯示位置是-98時執行
                    indexFlag = !indexFlag; // 改變indexFlag的值
                    x4 = 392; // 設定第4個標籤的顯示位置
                    if (indexFlag) {
                        // indexFlag為true時改變的圖片
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/9.jpg"));
                    } else {
                        // indexFlag為false時改變的圖片
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/4.jpg"));
                    }
                }
                if (x5 == -98) { // 當第5個標籤的顯示位置是-98時執行
                    indexFlag = !indexFlag; // 改變indexFlag的值
                    x5 = 392; // 設定第5個標籤的顯示位置
                    if (indexFlag) {
                        // indexFlag為true時改變的圖片
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/10.jpg"));
                    } else {
                        // indexFlag為false時改變的圖片
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/5.jpg"));
                    }
                }
                try {
                    Thread.sleep(150); // 線程睡眠150毫秒
                } catch (Exception ex) {
                    
                }
            }
        }
    }
}
