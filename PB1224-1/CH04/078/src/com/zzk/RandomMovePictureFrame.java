package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RandomMovePictureFrame extends JFrame {
    private final int winWIDTH = 450;
    private final int winHEIGHT = 300;
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RandomMovePictureFrame frame = new RandomMovePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public RandomMovePictureFrame() {
        super();
        setTitle("隨機移動的圖片");
        setBounds(100, 100, winWIDTH, winHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RandomMovePicturePanel panel = new RandomMovePicturePanel();// 建立面板對像
        getContentPane().add(panel);// 將面板增加到窗體容器
        Thread thread = new Thread(panel);// 建立線程對像
        thread.start();// 啟動線程對像
    }
    
    /**
     * @author zzk
     * 建立實現Runnable接口的內部面板類別
     */
    private class RandomMovePicturePanel extends JPanel implements Runnable {
        Random random = new Random();// 建立Random對像
        int x = 0;// 定義圖片移動位置的x座標
        int y = 0;// 定義圖片移動位置的y座標
        URL imgUrl = RandomMovePictureFrame.class
                .getResource("/image/picture.png");// 獲得圖片資源的路徑
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl);// 獲得圖形對像
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// 清除面板上的內容
            g.drawImage(img, x, y, this);// 在面板的指定位置繪製圖形
        }
        public void run() {
            while (true) {
                x = random.nextInt(winWIDTH - 110);// 隨機獲得圖片移動位置的x座標
                y = random.nextInt(winHEIGHT - 140);// 隨機獲得圖片移動位置的y座標
                try {
                    Thread.sleep(200);// 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// 呼叫paint()方法
            }
        }
    }
}
