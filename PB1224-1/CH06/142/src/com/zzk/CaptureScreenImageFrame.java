package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class CaptureScreenImageFrame extends JFrame {
    private PartZoomInPanel partZoomInPanel = null;// 宣告圖形面板對像
    private int pressPanelX = 0, pressPanelY = 0;// 鼠標按下點的X、Y座標
    private int pressX = 0, pressY = 0;// 鼠標按下點在屏幕上的X、Y座標
    private int releaseX = 0, releaseY = 0;// 鼠標釋放點在屏幕上的X、Y座標
    private Robot robot = null;// 宣告Robot對像
    private BufferedImage buffImage = null;// 宣告緩衝圖形對像
    private boolean flag = false;// 宣告標記變數，為true時顯示選擇區域的矩形，否則不顯示
    private Rectangle rect = null;
    
    public static void main(String args[]) {
        CaptureScreenImageFrame frame;
        try {
            frame = new CaptureScreenImageFrame("屏幕抓圖程式");
            frame.setVisible(true);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public CaptureScreenImageFrame(String title) throws AWTException {
        super(title);
        setAlwaysOnTop(true);
        partZoomInPanel = new PartZoomInPanel(); // 建立圖形面板對像
        Toolkit toolkit = getToolkit();
        Dimension dim = toolkit.getScreenSize();
        setBounds(0, 0, dim.width, dim.height - 30); // 設定窗體大小和位置
        setUndecorated(true);// 取消窗體修飾
        AWTUtilities.setWindowOpacity(this, 0.01f);// 設定窗體透明
        getContentPane().add(partZoomInPanel, BorderLayout.CENTER);
        robot = new Robot();// 建立Robot對像
        rect = new Rectangle(0, 0, dim.width, dim.height);// 建立Rectangle對像
        buffImage = robot.createScreenCapture(rect);// 獲得緩衝圖形對像
        partZoomInPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { // 鼠標鍵按下事件
                buffImage = robot.createScreenCapture(rect);// 獲得緩衝圖形對像
                partZoomInPanel.repaint();
                AWTUtilities.setWindowOpacity(CaptureScreenImageFrame.this, 1f);// 設定窗體為不透明
                pressPanelX = e.getX(); // 獲得鼠標按下點的X座標
                pressPanelY = e.getY();// 獲得鼠標按下點的Y座標
                pressX = e.getXOnScreen() + 1;// 鼠標按下點在屏幕上的X座標加1，即去除選擇線
                pressY = e.getYOnScreen() + 1;// 鼠標按下點在屏幕上的Y座標加1，即去除選擇線
                flag = true;// 為標記變數給予值為true
            }
            
            public void mouseReleased(final MouseEvent e) { // 鼠標鍵釋放事件
                releaseX = e.getXOnScreen() - 1;// 鼠標釋放點在屏幕上的X座標減1，即去除選擇線
                releaseY = e.getYOnScreen() - 1;// 鼠標釋放點在屏幕上的Y座標減1，即去除選擇線
                try {
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// 建立Rectangle對像
                        buffImage = robot.createScreenCapture(rect);// 獲得緩衝圖形對像
                        FileOutputStream out = new FileOutputStream("c:/zzkkee.jpg");// 儲存位置的輸出流對像
                        ImageIO.write(buffImage, "jpg", out);// 寫入磁碟
                        out.flush();
                        out.close();
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                flag = false;   // 為標記變數給予值為false
                if (e.getButton() == MouseEvent.BUTTON1) {// 單擊鼠標右鍵
                    CaptureScreenImageFrame.this.dispose();
                    CaptureScreenImageFrame frame;
                    try {
                        frame = new CaptureScreenImageFrame("屏幕抓圖程式");
                        AWTUtilities.setWindowOpacity(frame, 0.01f);
                        frame.setVisible(true);
                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            
            public void mouseClicked(final MouseEvent e) { // 鼠標鍵單擊事件
                if (e.getButton() == MouseEvent.BUTTON3) {// 單擊鼠標右鍵
                    System.exit(0);// 退出系統
                }
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
    }
    
    class PartZoomInPanel extends JPanel {// 建立繪製原圖形的面板類別
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(buffImage, 0, 0, PartZoomInPanel.this);
            g2.setColor(Color.BLACK);
            if (flag) {
                float[] arr = { 5.0f }; // 建立虛線模式的陣列
                BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // 建立寬度是1的平頭虛線筆畫對像
                g2.setStroke(stroke);// 設定筆畫對像
                g2.drawRect(pressPanelX, pressPanelY, releaseX - pressX,
                        releaseY - pressY);// 繪製矩形選區
            }
        }
    }
    
}
