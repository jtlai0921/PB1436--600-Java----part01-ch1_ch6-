package com.zzk;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author 張振坤
 */
public class BirdLabel extends JLabel implements Runnable {
    /**
     * 控制項的控制項事件監聽器
     * @author 張振坤
     */
    private final class ComponentAction extends ComponentAdapter {
        public void componentResized(final ComponentEvent e) {
            thread.start();// 線程啟動
        }
    }
    
    /**
     * 控制項的鼠標事件監聽器
     * 
     * @author 張振坤
     */
    private final class MouseAction extends MouseAdapter {
        public void mousePressed(final MouseEvent e) {
            if (!MainFrame.readyAmmo())// 如果子彈沒有準備好
                return;// 什麼也不做
            MainFrame.useAmmo();// 消耗子彈
            appScore();// 加分
            destory();// 銷毀本元件
        }
    }
    
    // 隨機產生線程的休眠時間，即控制小鳥移動速度
    private int sleepTime = (int) (Math.random() * 300) + 5;
    private int y = 100;
    private Thread thread;// 將線程作為成員變數
    private Container parent;
    private int score = 15;// 該類別角色對應的分數
    
    /**
     * 建構方法
     */
    public BirdLabel() {
        super();
        // 建立小鳥圖標對像
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "bird.gif"));
        setIcon(icon);// 設定控制項圖標
        addMouseListener(new MouseAction());// 增加鼠標事件監聽器
        // 增加控制項事件監聽器
        addComponentListener(new ComponentAction());
        thread = new Thread(this);// 建立線程對像
    }
    
    @Override
    public void run() {
        parent = null;
        int width = 0;
        try {
            while (width <= 0 || parent == null) {
                if (parent == null){
                    parent = getParent();// 獲得父容器
                } else {
                    width = parent.getWidth();// 獲得父容器的寬度
                }
                Thread.sleep(10);
            }
            for (int i = width; i > 0 && parent != null; i -= 8) {
                setLocation(i, y);// 從右向左移動本元件位置
                Thread.sleep(sleepTime);// 休眠片刻
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (parent != null) {
            MainFrame.appScore(-score * 10); // 自然銷毀將扣分
        }
        destory();// 移動完畢，銷毀本元件
    }
    
    /**
     * 從容器移除本元件的方法
     */
    public void destory() {
        if (parent == null)
            return;
        parent.remove(this);// 從父容器中移除本逐漸
        parent.repaint();
        parent = null; // 透過該敘述終止線程循環
    }
    
    /**
     * 加分的方法
     */
    private void appScore() {
        MainFrame.appScore(15);
    }
}
