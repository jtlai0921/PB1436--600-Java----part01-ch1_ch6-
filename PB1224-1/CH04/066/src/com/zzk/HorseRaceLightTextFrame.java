package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HorseRaceLightTextFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private HorseRaceLightTextPanel horseRaceLightTextPanel = new HorseRaceLightTextPanel();
    public static void main(String args[]) {
                    HorseRaceLightTextFrame frame = new HorseRaceLightTextFrame();
                    frame.setVisible(true);
    }
    
    public HorseRaceLightTextFrame() {
        super();
        setTitle("文字跑馬燈特效");
        setBounds(200, 160, 360, 237);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = HorseRaceLightTextFrame.class
                .getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        getContentPane().add(horseRaceLightTextPanel);
        Thread thread = new Thread(horseRaceLightTextPanel);// 建立線程對像
        thread.start();// 啟動線程對像
    }
    
    class HorseRaceLightTextPanel extends JPanel implements Runnable { // 建立內部面板類別
        String value = "全能編程詞典，我的學習專家。";// 儲存繪製的內容
        char[] drawChar = value.toCharArray();// 將繪製內容轉為字符陣列
        int[] x = new int[drawChar.length];// 儲存每個字符繪製點x座標的陣列
        int y = 100;// 儲存繪製點的y座標
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// 清除繪圖上下文的內容
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);// 繪製圖形
            Font font = new Font("華文楷體", Font.BOLD, 20);// 建立字體對像
            g.setFont(font);// 指定字體
            g.setColor(Color.RED);// 指定顏色
            for (int j = drawChar.length-1; j >=0; j--){
               g.drawString(drawChar[drawChar.length-1-j]+"",x[j] , y);// 繪製純文字
            }
        }
        public void run() {
            try {
                boolean flag = false;// 為false時表示第一次執行，x座標進行等比遞增，否則進行等差遞增
                while (true) { // 讀取內容
                    Thread.sleep(300); // 目前線程休眠300毫秒
                    for (int i = drawChar.length-1; i >=0 ; i--) {
                        if (!flag){
                            x[i]=x[i]+20*i;// x座標進行等比遞增
                        } else {
                            x[i]=x[i]+20;// x座標進行等差遞增
                        }
                        if (x[i]>=360 - 20){// 大於窗體寬度減20的值時
                            x[i] = 0;       // x座標為0
                        }
                    }
                    repaint();// 呼叫paint()方法
                    if (!flag) {
                        flag = true;// 給予值為true
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
