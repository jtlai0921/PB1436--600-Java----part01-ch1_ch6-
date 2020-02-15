package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class PolicemanGraspThief extends JFrame {
    private JLabel label;
    private JButton button;
    final JLabel lb_thief = new JLabel(); // 顯示小偷的標籤
    final JLabel lb_policeman = new JLabel(); // 顯示警察的標籤
    // 建立線程對像
    private Thread thread = new Thread(new GraspThiefThread());
    private boolean stop = false; // 為true時，顯示提示純文字為「打中了」的標籤，為false時不顯示
    private final JLabel lb_tip = new JLabel("打中了");
    
    public PolicemanGraspThief() {
        super();
        setTitle("警察抓小偷");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 80, 808, 584);
        
        final BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setFocusable(false);
        backgroundPanel.setImage(SwingResourceManager.getImage(
                PolicemanGraspThief.class, "/image/background.png"));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        lb_thief.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/小偷.png"));
        lb_thief.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
                stop = true; // stop為true時，顯示提示純文字為「打中了」的標籤
                JOptionPane.showMessageDialog(null, "打中了..."); // 顯示訊息框
                lb_tip.setVisible(false);
            }
        });
        lb_thief.setBounds(350, 150, 50, 50);
        backgroundPanel.add(lb_thief);
        
        lb_tip.setBounds(350, 210, 66, 50);
        lb_tip.setForeground(new Color(0, 0, 255));
        lb_tip.setFont(new Font("", Font.BOLD, 16));
        backgroundPanel.add(lb_tip);
        
        lb_policeman.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/警察.png"));
        lb_policeman.setBounds(0, 131, 95, 88);
        backgroundPanel.add(lb_policeman);
        lb_tip.setVisible(false);
        backgroundPanel.add(getButton());
        backgroundPanel.add(getLabel());
        thread.start();
        
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.setIcon(SwingResourceManager.getIcon(
                    PolicemanGraspThief.class, "/icon/zailai.png"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent arg0) {
                    if (thread == null) { // 線程對像為空
                        thread = new Thread(new GraspThiefThread()); // 建立線程對像
                    }
                    if (!thread.isAlive()) { // 不是活動線程
                        stop = false; // stop為false時法顯示提示純文字為「打中了」的標籤
                        lb_tip.setVisible(false); // 隱藏提示純文字為「打中了」的標籤
                        thread.start(); // 重新啟動線程對像
                    }
                }
            });
            button.setBounds(616, 485, 149, 47);
        }
        return button;
    }
    
    /**
     * @author 張振坤
     *         使小偷運動的線程
     */
    private class GraspThiefThread implements Runnable {
        boolean flag = false; // 標誌小偷向左運動還是向右運動的變數
        int x = 400; // 小偷標籤左側邊界的橫座標
        
        public void run() {
            while (true) {
                if (stop) { // stop為true時，顯示提示純文字為「打中了」標籤
                    int x = lb_thief.getX(); // 獲得小偷標籤的橫座標
                    int y = lb_thief.getY(); // 獲得小偷標籤的縱座標
                    lb_tip.setBounds(x, y + 60, 50, 50); // 設定提示純文字為「打中了」標籤的顯示位置和大小
                    lb_tip.setVisible(true); // 顯示提示純文字為「打中了」的標籤
                    thread = null; // 釋放線程資源
                    break; // 退出循環，結束線程的執行
                }
                if (flag == false) { // flag為false向右運動
                    x += 20; // x的值增加表示向右運動
                    if (x == 640) { // 當小偷標籤左側邊界的橫座標是640時
                        flag = true; // 將flag給予值為true
                    }
                } else { // flag為true向左運動
                    x -= 20; // x的值減少表示向左運動
                    if (x == 100) { // 當小偷標籤左側邊界的橫座標是100時
                        flag = false; // 將flag給予值為false
                    }
                }
                // 產生100-200之間的隨機整數，用於設定小偷標籤上邊界的縱座標
                int y = (int) (Math.random() * 100) + 100;
                lb_thief.setLocation(x, y); // 設定小偷標籤的顯示位置
                try {
                    Thread.sleep(200); // 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        PolicemanGraspThief frame = new PolicemanGraspThief();
        frame.setVisible(true);
    }
    
    /**
     * @return
     */
    protected JLabel getLabel() {
        if (label == null) {
            label = new JLabel();
            label.setForeground(new Color(255, 0, 0));
            label.setFont(new Font("", Font.BOLD, 26));
            label.setText("注意：請使用鼠標當槍，單擊小偷。");
            label.setBounds(40, 428, 468, 80);
        }
        return label;
    }
    
}
