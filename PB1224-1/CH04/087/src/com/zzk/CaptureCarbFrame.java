package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CaptureCarbFrame extends JFrame implements Runnable {
    private JLabel[] carb; // 存放顯示螃蟹的標籤陣列
    private ImageIcon imgCarb; // 螃蟹圖片對像
    private MouseCrab mouseCrab;// 標籤的事件監聽器
    
    /**
     * 顯示螃蟹的標籤控制項的鼠標事件監聽器
     * @author 張振坤
     */
    private final class MouseCrab implements MouseListener {
        private final Cursor cursor1;// 鼠標圖標1
        private final Cursor cursor2;// 鼠標圖標2
        
        /**
         * 建構方法
         * 
         * @param cursor1
         * @param cursor2
         */
        private MouseCrab(Cursor cursor1, Cursor cursor2) {
            this.cursor1 = cursor1;
            this.cursor2 = cursor2;
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(cursor1);// 鼠標按鍵釋放時設定光標為cursor1
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            setCursor(cursor2);// 鼠標按鍵按下時設定光標為cursor2
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(cursor1);// 鼠標離開控制項區域時設定光標為cursor1
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
    
    /**
     * 鼠標單擊事件監聽器，用於改變鼠標的圖標。
     * 
     * @author 張振坤
     */
    private final class Catcher extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // 獲得事件源，即螃蟹標籤
            if (source instanceof JLabel) { // 如果事件源是標籤元件
                JLabel carb = (JLabel) source; // 強制轉為JLabel標籤
                if (carb.getIcon() != null)
                    carb.setIcon(imgCarb2); // 為該標籤增加螃蟹圖片
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // 獲得事件源，即螃蟹標籤
            if (source instanceof JLabel) { // 如果事件源是標籤元件
                JLabel carb = (JLabel) source; // 強制轉為JLabel標籤
                carb.setIcon(null);// 清除標籤中的螃蟹圖片
            }
        }
    }
    
    private ImageIcon imgCarb2;
    
    /**
     * 程式入口方法
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 建立程式窗體
                    CaptureCarbFrame frame = new CaptureCarbFrame();
                    frame.setVisible(true);// 顯示窗體
                    new Thread(frame).start();// 建立線程並啟動
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * 建構方法
     */
    public CaptureCarbFrame() {
        super();
        // 建立第一個鼠標圖標
        ImageIcon icon = new ImageIcon(getClass().getResource("hand.jpg"));
        // 建立第二個鼠標圖標
        ImageIcon icon2 = new ImageIcon(getClass().getResource("hand2.jpg"));
        // 獲得每個圖標的圖片
        Image image = icon.getImage();
        Image image2 = icon2.getImage();
        // 使用圖片建立2個鼠標光標對像
        final Cursor cursor1 = getToolkit().createCustomCursor(image,
                new Point(0, 0), "hand1");
        final Cursor cursor2 = getToolkit().createCustomCursor(image2,
                new Point(0, 0), "hand2");
        // 初始化顯示螃蟹標籤元件的事件監聽器
        mouseCrab = new MouseCrab(cursor1, cursor2);
        setResizable(false); // 禁止調整窗體大小
        getContentPane().setLayout(null); // 窗體不使用佈局管理器
        setTitle("海灘捉螃蟹"); // 設定窗體標題
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 初始化背景圖片對像
        ImageIcon img = new ImageIcon(getClass().getResource("background.jpg"));
        // 初始化螃蟹圖片對像
        imgCarb = new ImageIcon(getClass().getResource("crab.png"));
        imgCarb2 = new ImageIcon(getClass().getResource("crab2.png"));
        carb = new JLabel[6]; // 建立顯示螃蟹的標籤陣列
        Catcher catcher = new Catcher();// 標籤的鼠標單擊事件監聽器
        for (int i = 0; i < 6; i++) { // 檢查陣列
            carb[i] = new JLabel(); // 初始化每一個陣列元素
            // 設定標籤與螃蟹圖片相同大小
            carb[i].setSize(imgCarb.getIconWidth(), imgCarb.getIconHeight());
            // 為標籤增加事件監聽器
            carb[i].addMouseListener(catcher);
            carb[i].addMouseListener(mouseCrab);
            getContentPane().add(carb[i]); // 增加顯示螃蟹的標籤到窗體
        }
        
        carb[0].setLocation(253, 315); // 設定每個標籤的位置
        carb[1].setLocation(333, 265);
        carb[2].setLocation(388, 311);
        carb[3].setLocation(362, 379);
        carb[4].setLocation(189, 368);
        carb[5].setLocation(240, 428);
        
        final JLabel backLabel = new JLabel(); // 建立顯示背景的標籤
        // 設定標籤與背景圖片相同大小
        backLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        // 設定窗體近似背景圖片大小
        setBounds(100, 100, img.getIconWidth(), img.getIconHeight() + 30);
        backLabel.setIcon(img); // 增加背景到標籤
        getContentPane().add(backLabel); // 增加背景標籤到窗體
        setCursor(cursor1);// 設定預設使用第一個鼠標光標
        addMouseListener(mouseCrab);// 為面板增加鼠標事件監聽器
    }
    
    /**
     * 線程的核心方法
     */
    public void run() {
        while (true) { // 使用無限循環
            try {
                Thread.sleep(1000); // 使線程休眠1秒
                int index = (int) (Math.random() * 6);// 產生隨機的螃蟹索引
                if (carb[index].getIcon() == null) {// 如果螃蟹標籤沒有設定圖片
                    carb[index].setIcon(imgCarb);// 為該標籤增加螃蟹圖片
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
