package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PartZoomInImageFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private PartZoomInPanel partZoomInPanel = null; // 宣告圖形面板對像
    private int pressPanelX = 0, pressPanelY = 0;// 鼠標按下點的X、Y座標
    private int pressX = 0, pressY = 0;// 鼠標按下點在屏幕上的X、Y座標
    private int releaseX = 0, releaseY = 0;// 鼠標釋放點在屏幕上的X、Y座標
    private Robot robot = null; // 宣告Robot對像
    private BufferedImage buffImage = null; // 宣告緩衝圖形對像
    private boolean flag = false; // 宣告標記變數，為true時顯示選擇區域的矩形，否則不顯示
    private boolean mouseFlag = false;// 進行局部放大圖形的標記變數，為true時進行局部放大
    
    public static void main(String args[]) {
        PartZoomInImageFrame frame = new PartZoomInImageFrame();
        frame.setVisible(true);
    }
    
    public PartZoomInImageFrame() {
        super();
        URL imgUrl = PartZoomInImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        partZoomInPanel = new PartZoomInPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 355, 276); // 設定窗體大小和位置
        getContentPane().add(partZoomInPanel, BorderLayout.CENTER);
        
        partZoomInPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { // 鼠標鍵按下事件
                pressPanelX = e.getX(); // 獲得鼠標按下點的X座標
                pressPanelY = e.getY();// 獲得鼠標按下點的Y座標
                pressX = e.getXOnScreen() + 1;// 鼠標按下點在屏幕上的X座標加1，即去除選擇線
                pressY = e.getYOnScreen() + 1;// 鼠標按下點在屏幕上的Y座標加1，即去除選擇線
                flag = true;// 為標記變數給予值為true
                mouseFlag = false;// 將標記設定為false
            }
            
            public void mouseReleased(final MouseEvent e) { // 鼠標鍵釋放事件
                releaseX = e.getXOnScreen() - 1;// 鼠標釋放點在屏幕上的X座標減1，即去除選擇線
                releaseY = e.getYOnScreen() - 1;// 鼠標釋放點在屏幕上的Y座標減1，即去除選擇線
                try {
                    robot = new Robot();// 建立Robot對像
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// 建立Rectangle對像
                        buffImage = robot.createScreenCapture(rect);// 獲得緩衝圖形對像
                    }
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                flag = false;// 為標記變數給予值為false
                mouseFlag = true;// 標記為true，進行局部放大
                repaint();// 呼叫paint()方法，實現局部放大
            }
            
            public void mouseClicked(final MouseEvent e) { // 鼠標鍵單擊事件
                mouseFlag = false;// 將標記設定為false，不放大圖形
            }
        });
        partZoomInPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {// 鼠標拖動事件
                if (flag) {
                    releaseX = e.getXOnScreen();// 獲得鼠標釋放點在屏幕上的X座標
                    releaseY = e.getYOnScreen();// 獲得鼠標釋放點在屏幕上的Y座標
                    partZoomInPanel.repaint();// 呼叫PartZoomInPanel面板的paint()方法
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("局部圖形放大"); // 設定窗體標題
    }
    
    class PartZoomInPanel extends JPanel {// 建立繪製原圖形的面板類別
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);// 繪製圖形
            g2.setColor(Color.WHITE);
            if (flag) {
                float[] arr = { 5.0f }; // 建立虛線模式的陣列
                BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // 建立寬度是1的平頭虛線筆畫對像
                g2.setStroke(stroke);// 設定筆畫對像
                g2.drawRect(pressPanelX, pressPanelY, releaseX - pressX,
                        releaseY - pressY);// 繪製矩形選區
            }
            
            if (mouseFlag) {// 條件為真
                int zoomX = pressPanelX - (releaseX - pressX) / 4;// 放大圖形繪製點的x座標
                int zoomY = pressPanelY - (releaseY - pressY) / 4;// 放大圖形繪製點的y座標
                if (zoomX <= 0) {
                    zoomX = 0;// 座標值小於等於0，讓座標值為0
                }
                if (zoomY <= 0) {
                    zoomY = 0;// 座標值小於等於0，讓座標值為0
                }
                g.drawImage(buffImage, zoomX, zoomY,
                        (int) ((releaseX - pressX) * 1.5f),
                        (int) ((releaseY - pressY) * 1.5f), this);// 繪製放大後的局部圖形
            }
        }
    }
    
}
