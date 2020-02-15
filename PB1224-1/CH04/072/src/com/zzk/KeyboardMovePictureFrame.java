package com.zzk;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyboardMovePictureFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private ImageIcon icon = null;// 宣告圖形圖標
    final JLabel lb_move = new JLabel();// 透過鍵碟控制的標籤
    
    public static void main(String args[]) {
        KeyboardMovePictureFrame frame = new KeyboardMovePictureFrame();
        frame.setVisible(true);// 顯示窗體
        frame.getContentPane().requestFocus();// 使窗體的內容面板獲得焦點
    }
    
    public KeyboardMovePictureFrame() {
        super();
        getContentPane().addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                int x = lb_move.getLocation().x;// 獲得移動標籤的x座標
                int y = lb_move.getLocation().y;// 獲得移動標籤的y座標
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    lb_move.setLocation(x - 10, y);// 向左移動，x座標減小
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    lb_move.setLocation(x, y - 10);// 向上移動，y座標減小
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    lb_move.setLocation(x + 10, y);// 向右移動，x座標增加
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    lb_move.setLocation(x, y + 10);// 向下移動，y座標增加
                }
            }
        });
        setTitle("透過鍵碟移動圖片");
        getContentPane().setLayout(null);
        setBounds(100, 100, 364, 239);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = KeyboardMovePictureFrame.class
                .getResource("/image/coney.png");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        icon = new ImageIcon(img);// 建立圖形圖標
        lb_move.setIcon(icon);// 指定標籤顯示的圖標
        lb_move.setBounds(35, 30, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // 在圖片上單擊鼠標右鍵退出系統
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
    }
}
