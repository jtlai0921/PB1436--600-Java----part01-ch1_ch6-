package com.zzk;

import static java.lang.Math.random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 張振坤
 */
public class MainFrame extends JFrame {
    private static long score = 0;// 分數
    private static Integer ammoNum = 5;// 子彈數量
    private static JLabel scoreLabel;// 分數
    private BackgroundPanel backgroundPanel;
    private static JLabel ammoLabel;
    private static JPanel infoPane;
    
    /**
     * 加分方法
     * 
     * @param score
     */
    public synchronized static void appScore(int num) {
        score += num;
        scoreLabel.setText("分數：" + score);
    }
    
    /**
     * 消耗子彈的方法
     * 
     * @param num
     */
    public synchronized static void useAmmo() {
        synchronized (ammoNum) {
            ammoNum--;// 子彈數量遞減
            ammoLabel.setText("子彈數量：" + ammoNum);
            if (ammoNum <= 0) {// 判斷子彈是否小於0
                new Thread(new Runnable() {
                    public void run() {
                        // 顯示提示資訊面板
                        infoPane.setVisible(true);
                        try {
                            // 1秒鐘加載子彈的時間
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ammoNum = 5;// 恢復子彈數量
                        // 修改子彈數量標籤的純文字
                        ammoLabel.setText("子彈數量：" + ammoNum);
                        infoPane.setVisible(false);// 隱藏提示資訊面板
                    }
                }).start();
            }
        }
    }
    
    /**
     * 判斷子彈是否夠用
     * 
     * @return
     */
    public synchronized static boolean readyAmmo() {
        synchronized (ammoNum) {
            return ammoNum > 0;
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                    frame.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * 建構方法
     */
    public MainFrame() {
        super();
        setResizable(false);// 進位調整窗體大小
        setTitle("荒山打獵遊戲");
        infoPane = (JPanel) getGlassPane();// 獲得玻璃面板
        JLabel label = new JLabel("加載子彈……");// 建立提示標籤元件
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("楷體", Font.BOLD, 32));
        label.setForeground(Color.ORANGE);
        infoPane.setLayout(new BorderLayout());
        infoPane.add(label);// 增加提示標籤元件到玻璃面板
        
        setAlwaysOnTop(true);// 是窗體保持在最頂層
        setBounds(100, 100, 573, 411);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgroundPanel = new BackgroundPanel();// 建立帶背景的面板
        backgroundPanel.setImage(new ImageIcon(getClass()
                .getResource("background.jpg")).getImage());// 設定背景圖片
        getContentPane().add(backgroundPanel,
                BorderLayout.CENTER);
        // 增加鼠標事件介面卡
        addMouseListener(new FrameMouseListener());
        scoreLabel = new JLabel();// 顯示分數的標籤元件
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.ORANGE);
        scoreLabel.setText("分數：");
        scoreLabel.setBounds(25, 15, 120, 18);
        backgroundPanel.add(scoreLabel);
        ammoLabel = new JLabel();// 顯示自動數量的標籤元件
        ammoLabel.setForeground(Color.ORANGE);
        ammoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ammoLabel.setText("子彈數量：" + ammoNum);
        ammoLabel.setBounds(422, 15, 93, 18);
        backgroundPanel.add(ammoLabel);
    }
    
    /**
     * 啟動遊戲的方法
     */
    public void start() {
        new PigThread().start();
        new BirdThread().start();
    }
    
    /**
     * 窗體的鼠標事件監聽器
     * 
     * @author 張振坤
     */
    private final class FrameMouseListener extends MouseAdapter {
        public void mousePressed(final MouseEvent e) {
            Component at = backgroundPanel.getComponentAt(e
                    .getPoint());
            if (at instanceof BackgroundPanel) {// 如果點到面板也扣除子彈
                MainFrame.useAmmo();// 消耗子彈
            }
        }
    }
    
    /**
     * 產生豬角色的線程
     * 
     * @author 張振坤
     */
    class PigThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // 建立代表野豬的標籤控制項
                PigLabel pig = new PigLabel();
                pig.setSize(120, 80);// 設定控制項初始大小
                backgroundPanel.add(pig);// 增加控制項到背景面板
                try {
                    // 線程隨機休眠一段時間
                    sleep((long) (random() * 3000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 產生鳥角色的線程
     * 
     * @author 張振坤
     */
    class BirdThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // 建立代表小鳥的標籤控制項
                BirdLabel bird = new BirdLabel();
                bird.setSize(50, 50);// 設定控制項初始大小
                backgroundPanel.add(bird);// 增加控制項到背景面板
                try {
                    // 線程隨機休眠一段時間
                    sleep((long) (Math.random() * 3000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
