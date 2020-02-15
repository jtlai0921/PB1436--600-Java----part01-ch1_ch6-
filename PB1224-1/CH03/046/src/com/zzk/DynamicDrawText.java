package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.*;

import javax.swing.*;

public class DynamicDrawText extends JFrame {
    private DynamicDrawTextPanel dynamicDrawTextPanel = new DynamicDrawTextPanel();
    public static void main(String args[]) {
        DynamicDrawText frame;
        try {
            frame = new DynamicDrawText();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public DynamicDrawText() {
        setTitle("動態繪製純文字");
        setBounds(100, 100, 262, 179);
        setDefaultCloseOperation(3);
        getContentPane().add(dynamicDrawTextPanel);
        Thread thread = new Thread(dynamicDrawTextPanel); // 建立線程對像
        thread.start(); // 啟動線程
    }
    
    // 建立面板類別
    class DynamicDrawTextPanel extends JPanel implements Runnable {
        private BufferedReader read; // 宣告緩衝流對像
        int x = 20;// 起始點的x座標
        int y = 30;// 起始點的y座標
        String value = "";
        public DynamicDrawTextPanel(){
            String projectPath = System.getProperty("user.dir"); // 獲得目前項目
            String filePath = projectPath + "/src/com/zzk/dyn.txt";// 獲得項目中loadText.java檔案的完整路徑
            InputStream in = null;
            try {
                in = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // 建立輸入流對像
            read = new BufferedReader(new InputStreamReader(in)); // 建立緩衝流對像
        }
        public void paint(Graphics g) {
            Font font = new Font("華文楷體", Font.BOLD, 20);// 建立字體對像
            g.setFont(font);// 指定字體
            g.setColor(Color.RED);// 指定顏色
            g.drawString(value, x, y);// 繪製純文字
        }
        public void run() {
            int len = 0;// 儲存讀取的字符
            try {
                while ((len = read.read()) != -1) { // 讀取內容
                    Thread.sleep(400); // 目前線程休眠400毫秒
                    value = String.valueOf((char) len); // 獲得讀取的內容
                    if (value.equals("\n") || value.equals("\r")) {// 是確認或換行符
                        x = 20;// 下一行起始點的x座標
                        y += 15;// 下一行純文字的y座標
                    } else {// 不是確認或換行符
                        x += 20;// 目前行下一個字的x座標
                    }
                    repaint();// 呼叫paint()方法
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}