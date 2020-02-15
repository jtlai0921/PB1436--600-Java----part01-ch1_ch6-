package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class ZoomImageFrame extends JFrame {
    private Image img = null;  // 宣告圖形對像
    private ZoomImagePanel imagePanel = null;  // 宣告圖形面板對像
    private int imgWidth, imgHeight;// 用於儲存圖片的寬度和高度
    private int newW, newH;// 用於儲存圖片縮放後的寬度和高度
    private JSlider slider = null;// 宣告滑動區塊對像
    public static void main(String args[]) {
        ZoomImageFrame frame = new ZoomImageFrame();
        frame.setVisible(true);
    }
    public ZoomImageFrame() {
        super();
        URL imgUrl = ZoomImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imagePanel = new ZoomImagePanel();  // 建立圖形面板對像
        this.setBounds(200, 160, 355, 253); // 設定窗體大小和位置
        this.add(imagePanel); // 在窗體中部位置增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("縮放圖形"); // 設定窗體標題
        slider = new JSlider();// 建立滑動區塊對像
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                imagePanel.repaint();// 重新呼叫面板類別的paint()方法
            }
        });
        getContentPane().add(slider, BorderLayout.SOUTH);// 在窗體底部位置增加滑動區塊對像
    }
    // 建立面板類別
    class ZoomImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清除繪圖上下文的內容
            imgWidth = img.getWidth(this); // 獲得圖片寬度
            imgHeight = img.getHeight(this); // 獲得圖片高度
            float value = slider.getValue();// 滑動區塊元件的取值
            newW = (int) (imgWidth * value / 100);// 計算圖片縮放後的寬度
            newH = (int) (imgHeight * value / 100);// 計算圖片縮放後的高度
            g.drawImage(img, 0, 0, newW, newH, this);// 繪製指定大小的圖片
        }
    }
}
