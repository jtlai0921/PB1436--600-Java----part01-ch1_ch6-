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
import javax.swing.JSplitPane;
public class CutImageFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private OldImagePanel oldImagePanel = null; // 宣告圖形面板對像
    private int pressPanelX = 0, pressPanelY = 0;// 鼠標按下點的X、Y座標 
    private int pressX = 0, pressY = 0;// 鼠標按下點在屏幕上的X、Y座標
    private int releaseX = 0, releaseY = 0;// 鼠標釋放點在屏幕上的X、Y座標
    private Robot robot = null;  // 宣告Robot對像
    private BufferedImage buffImage = null; // 宣告緩衝圖形對像
    private CutImagePanel cutImagePanel = new CutImagePanel(); // 建立繪製裁剪結果的面板
    private boolean flag = false;  // 宣告標記變數，為true時顯示選擇區域的矩形，否則不顯示
    public static void main(String args[]) {
        CutImageFrame frame = new CutImageFrame();
        frame.setVisible(true);
    }
    public CutImageFrame() {
        super();
        URL imgUrl = CutImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        oldImagePanel = new OldImagePanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 355, 276); // 設定窗體大小和位置
        final JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation((this.getWidth() / 2) - 10);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setLeftComponent(oldImagePanel);
        splitPane.setRightComponent(cutImagePanel);
        oldImagePanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {  // 鼠標鍵按下事件
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
                    robot = new Robot();// 建立Robot對像
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// 建立Rectangle對像
                        buffImage = robot.createScreenCapture(rect);// 獲得緩衝圖形對像
                        cutImagePanel.repaint(); // 呼叫CutImagePanel面板的paint()方法
                    }
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                flag = false;// 為標記變數給予值為false
            }
        });
        oldImagePanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {// 鼠標拖動事件
                if (flag) {
                    releaseX = e.getXOnScreen();// 獲得鼠標釋放點在屏幕上的X座標
                    releaseY = e.getYOnScreen();// 獲得鼠標釋放點在屏幕上的Y座標
                    oldImagePanel.repaint();// 呼叫OldImagePanel面板的paint()方法
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("裁剪圖片"); // 設定窗體標題
    }
    
    class OldImagePanel extends JPanel {// 建立繪製原圖形的面板類別
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
        }
    }
    
    class CutImagePanel extends JPanel {// 建立繪製裁剪結果的面板類別
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清除繪圖上下文的內容
            g.drawImage(buffImage, 0, 0, releaseX - pressX, releaseY - pressY,
                    this);// 繪製圖形
        }
    }
}
