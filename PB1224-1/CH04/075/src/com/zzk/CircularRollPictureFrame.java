package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircularRollPictureFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CircularRollPictureFrame frame = new CircularRollPictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CircularRollPictureFrame() {
        super();
        setTitle("循環捲動圖片");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CircularRollPicturePanel panel = new CircularRollPicturePanel();// 建立面板對像
        getContentPane().add(panel);// 將面板增加到窗體容器
        Thread thread = new Thread(panel);// 建立線程對像
        thread.start();// 啟動線程對像
    }
    
    /**
     * @author 張振坤
     * 建立實現Runnable接口的內部面板類別
     */
    private class CircularRollPicturePanel extends JPanel implements Runnable {
        int x = 0;// 定義圖片移動位置的x座標
        int y = 30;// 定義圖片移動位置的y座標
        URL imgUrl = CircularRollPictureFrame.class.getResource("/image/picture.png");// 獲得圖片資源的路徑
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形對像
        public void paint(Graphics g){
            g.clearRect(0, 0, getWidth(), getHeight());// 清除面板上的內容
            g.drawImage(img, x, y, this);// 在面板的指定位置繪製圖形
        }
        public void run() {
            boolean flag = true;// 宣告標記變數
            while (true){
                if (flag) {// 標記變數為true
                    x = x + 10;// 圖片x座標值加10
                    if (x >= getWidth() - img.getWidth(this)) {// 圖片的x座標值大於等於面板與圖片寬度的差
                        x = getWidth() - img.getWidth(this); // 圖片的x座標值等於面板與圖片寬度的差
                        flag = false;// 設定標記變數為false
                    }
                }else {// 標記變數為false
                    x = x - 10;// 圖片x座標值減10
                    if ( x <= 0 ) {// 圖片的x座標值小於等於0
                        x = 0;// 圖片的x座標值等於0
                        flag = true;// 設定標記變數為true
                    }
                }
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