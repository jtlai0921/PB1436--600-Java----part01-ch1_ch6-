package com.zzk;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author 張振坤
 */
public class PigLabel extends JLabel implements Runnable {
    // 隨機產生休眠時間，即野豬移動速度
    private int sleepTime = (int) (Math.random() * 300) + 30;
    private int y = 260;// 控制項的垂直座標
    private int score = 10;// 該角色對應的分數
    private Thread thread;// 內建線程對像
    private Container parent;// 控制項的父容器對像
    
    /**
     * 建構方法
     */
    public PigLabel() {
        super();
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "pig.gif"));// 載入野豬圖片
        setIcon(icon);// 設定本元件的圖標
        // 增加鼠標事件介面卡
        addMouseListener(new MouseAdapter() {
            // 按下鼠標按鍵的處理方法
            public void mousePressed(final MouseEvent e) {
                if (!MainFrame.readyAmmo())
                    return;
                MainFrame.useAmmo();// 消耗子彈
                appScore();// 給遊戲加分
                destory();// 銷毀本元件
            }
        });
        // 增加元件事件介面卡
        addComponentListener(new ComponentAdapter() {
            // 調整元件大小時
            public void componentResized(final ComponentEvent e) {
                thread.start();// 啟動線程
            }
        });
        // 初始化線程對像
        thread = new Thread(this);
    }
    
    @Override
    public void run() {
        parent = null;
        int width = 0;
        while (width <= 0 || parent == null) {// 獲得父容器寬度
            if (parent == null)
                parent = getParent();
            else
                width = parent.getWidth();
        }
        // 從左向右移動本元件
        for (int i = 0; i < width && parent != null; i += 8) {
            setLocation(i, y);
            try {
                Thread.sleep(sleepTime);// 休眠片刻
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (parent != null) {
            MainFrame.appScore(-score * 10); // 自然銷毀將扣分
        }
        destory();
    }
    
    /**
     * 從容器移除本元件的方法
     */
    public void destory() {
        if (parent == null)
            return;
        parent.remove(this);
        parent.repaint();
        parent = null; // 透過該敘述終止線程循環
    }
    
    /**
     * 加分的方法
     */
    private void appScore() {
        MainFrame.appScore(10);
    }
}
