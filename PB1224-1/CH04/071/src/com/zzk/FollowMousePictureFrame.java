package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FollowMousePictureFrame extends JFrame {
    private Image img = null;
    private ImageIcon icon = null;
    final JLabel lb_move = new JLabel();
    final JLabel lb_tip = new JLabel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FollowMousePictureFrame frame = new FollowMousePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FollowMousePictureFrame() {
        super();
        setTitle("隨鼠標移動的圖片");
        getContentPane().setLayout(null);
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = FollowMousePictureFrame.class
                .getResource("/image/coney.png");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        icon = new ImageIcon(img);// 建立圖形圖標
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent e) {
                int x = e.getX(); // 獲得鼠標在窗體容器中的橫座標值
                int y = e.getY(); // 獲得鼠標在窗體容器中的縱座標值
                int w = lb_move.getWidth(); // 獲得隨鼠標移動的圖形所在標籤的寬度
                int h = lb_move.getHeight(); // 獲得隨鼠標移動的圖形所在標籤的高度
                int x1 = x - w / 2; // 計算出隨鼠標移動的圖形所在標籤的橫座標值
                int y1 = y - h / 2; // 計算出隨鼠標移動的圖形所在標籤的縱座標值
                lb_move.setLocation(x1, y1); // 設定隨鼠標移動的圖形所在標籤的顯示位置
                int x2 = x - 52; // 計算顯示文字的標籤的橫座標值
                int y2 = y1 + 120; // 計算顯示文字的標籤的縱座標值
                lb_tip.setLocation(x2, y2); // 設定顯示文字的標籤的顯示位置
            }
        });
        lb_move.setIcon(icon);
        lb_move.setBounds(191, 61, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // 在圖片上單擊鼠標右鍵退出系統
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
        lb_tip.setText("我就跟著你。。。");
        lb_tip.setBounds(180, 191, 104, 18);
        getContentPane().add(lb_tip);
        Thread thread = new Thread(new FlareThread()); // 建立線程對像
        thread.start(); // 啟動線程對像
    }
    
    /**
     * @author Administrator
     *         動態改變文字顏色的線程
     */
    class FlareThread implements Runnable {
        public void run() {
            while (true) {
                int red = (int) (Math.random() * 256); // 隨機產生RGB顏色中的R，即紅色
                int green = (int) (Math.random() * 256); // 隨機產生RGB顏色中的G，即綠色
                int blue = (int) (Math.random() * 256); // 隨機產生RGB顏色中的B，即藍色
                lb_tip.setForeground(new Color(red, green, blue)); // 設定標籤上文字的前景色
                try {
                    Thread.sleep(500); // 線程休眠500毫秒
                } catch (Exception e) {
                    
                }
            }
        }
    }
}
