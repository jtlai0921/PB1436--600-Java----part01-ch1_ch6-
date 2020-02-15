package com.zzk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;

/**
 * 驗證碼面板
 * 
 * @author ZhenKun Zhang
 */
public class ChineseCodePanel extends JPanel {
    private static final long serialVersionUID = -3124698225447711692L;
    public static final int WIDTH = 120;// 寬度
    public static final int HEIGHT = 35;// 高度
    private String num = "";// 驗證碼
    Random random = new Random();// 實例化Random
    
    public ChineseCodePanel() {
        this.setVisible(true);// 顯示面板
        setLayout(null);// 空佈局
    }
    public void paint(Graphics g) {
        String hanZi = "編程詞典集學查用界面設計項目開發等內容於一體";// 定義驗證碼使用的中文字
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);// 實例化BufferedImage
        Graphics gs = image.getGraphics(); // 獲得Graphics類別的對像
        if (!num.isEmpty()) {
            num = "";// 清空驗證碼
        }
        Font font = new Font("黑體", Font.BOLD, 20); // 透過Font建構字體
        gs.setFont(font);// 設定字體
        gs.fillRect(0, 0, WIDTH, HEIGHT);// 填充一個矩形
        // 輸出隨機的驗證文字
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(hanZi.length());// 隨機獲得中文字的索引值
            String ctmp  = hanZi.substring(index,index+1);// 獲得指定索引處的一個中文字
            num += ctmp;// 更新驗證碼
            Color color = new Color(20 + random.nextInt(120), 20 + random
                    .nextInt(120), 20 + random.nextInt(120));// 產生隨機顏色
            gs.setColor(color); // 設定顏色
            Graphics2D gs2d = (Graphics2D) gs;// 將文字旋轉指定角度
            AffineTransform trans = new AffineTransform();// 實例化AffineTransform
            trans.rotate(random.nextInt(45) * 3.14 / 180, 22 * i + 8, 7);
            float scaleSize = random.nextFloat() + 0.8f;// 縮放文字
            if (scaleSize > 1f)
                scaleSize = 1f;// 如果scaleSize大於1,則等於1
            trans.scale(scaleSize, scaleSize); // 進行縮放
            gs2d.setTransform(trans);// 設定AffineTransform對像
            gs.drawString(ctmp, WIDTH / 6 * i + 28, HEIGHT / 2);// 畫出驗證碼
        }
        g.drawImage(image, 0, 0, null);// 在面板中畫出驗證碼
    }
    
    // 產生驗證碼的方法
    public void draw() {
        repaint();// 呼叫paint()方法
    }
    
    public String getNum() {
        return num;// 傳回驗證碼
    }
}