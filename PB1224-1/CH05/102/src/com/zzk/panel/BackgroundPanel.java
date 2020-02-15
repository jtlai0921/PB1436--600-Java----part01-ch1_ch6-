package com.zzk.panel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
public class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image image; // 定義圖形對像
    public BackgroundPanel(Image image) {
        super(); // 呼叫超類別的建構方法
        this.image = image; // 為圖形對像給予值
        initialize();
    }
    /*
     * 重新定義paintComponent方法
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 呼叫父類別的方法
        Graphics2D g2 = (Graphics2D) g; // 建立Graphics2D對像
        if (image != null) {
            int width = getWidth(); // 獲得面板的寬度
            int height = getHeight(); // 獲得面板的高度
            // 繪製圖形
            g2.drawImage(image, 0, 0, width, height, this);
        }
    }
    private void initialize() {
        this.setSize(300, 200);
    }
}