package com.zzk;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GainAnyPointColorFrame extends JFrame {
    
    private JTextField tf_blue;
    private JTextField tf_green;
    private JTextField tf_red;
    private JTextField tf_y;
    private JTextField tf_x;
    private static final long serialVersionUID = -486745172657329259L;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GainAnyPointColorFrame frame = new GainAnyPointColorFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public GainAnyPointColorFrame() {
        super();
        GainColor gc = new GainColor();// 建立GainColor對像
        Thread thread = new Thread(gc);// 建立線程對像
        thread.start();// 啟動線程對像
        getContentPane().setLayout(null);
        setTitle("獲得鼠標在任意位置的顏色值");
        setBounds(100, 100, 300, 207);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JLabel label = new JLabel();
        label.setText("鼠標所在點的X座標：");
        label.setBounds(26, 21, 126, 25);
        getContentPane().add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("鼠標所在點的Y座標：");
        label_1.setBounds(26, 45, 126, 25);
        getContentPane().add(label_1);
        
        tf_x = new JTextField();
        tf_x.setBounds(155, 22, 103, 22);
        getContentPane().add(tf_x);
        
        tf_y = new JTextField();
        tf_y.setBounds(155, 46, 103, 22);
        getContentPane().add(tf_y);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("顏色的Red值：");
        label_2.setBounds(26, 79, 97, 18);
        getContentPane().add(label_2);
        
        final JLabel label_3 = new JLabel();
        label_3.setText("顏色的Green值：");
        label_3.setBounds(26, 103, 111, 18);
        getContentPane().add(label_3);
        
        final JLabel label_4 = new JLabel();
        label_4.setText("顏色的Blue值：");
        label_4.setBounds(26, 127, 109, 18);
        getContentPane().add(label_4);
        
        tf_red = new JTextField();
        tf_red.setBounds(155, 77, 103, 22);
        getContentPane().add(tf_red);
        
        tf_green = new JTextField();
        tf_green.setBounds(155, 101, 103, 22);
        getContentPane().add(tf_green);
        
        tf_blue = new JTextField();
        tf_blue.setBounds(155, 125, 103, 22);
        getContentPane().add(tf_blue);
        
    }
    
    class GainColor implements Runnable {
        @Override
        public void run() {
            while (true) {
                PointerInfo mi = MouseInfo.getPointerInfo();// 鼠標指標目前位置的PointerInfo對像
                Point p = mi.getLocation();// 獲得屏幕上表示指標座標的Point對像
                int x = p.x;// 獲得X座標
                int y = p.y;// 獲得Y座標
                try {
                    Robot robot = new Robot();// 建立Robot對像
                    Color color = robot.getPixelColor(x, y);// 獲得屏幕指定位置的顏色對像
                    int r = color.getRed();// 獲得顏色的R值
                    int g = color.getGreen();// 獲得顏色的G值
                    int b = color.getBlue();// 獲得顏色的B值
                    tf_x.setText(String.valueOf(x));// 顯示X座標值
                    tf_y.setText(String.valueOf(y));// 顯示Y座標值
                    tf_red.setText(String.valueOf(r));// 顯示顏色的R值
                    tf_green.setText(String.valueOf(g));// 顯示顏色的G值
                    tf_blue.setText(String.valueOf(b));// 顯示顏色的B值
                    Thread.sleep(10);// 線程休眠10毫秒
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
