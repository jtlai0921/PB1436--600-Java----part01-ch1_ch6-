package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MosaicFrame extends JFrame {
    private BufferedImage image;// 宣告緩衝圖形對像
    private Image img = null; // 宣告圖形對像
    private MosaicPanel mosaicPanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        MosaicFrame frame = new MosaicFrame();
        frame.setVisible(true);
    }
    
    public MosaicFrame() {
        super();
        URL imgUrl = MosaicFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        mosaicPanel = new MosaicPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(mosaicPanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片馬賽克特效"); // 設定窗體標題

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int x = 104;// 矩形繪製點的x座標
                int y = 60; // 矩形繪製點的y座標
                Rectangle2D.Float rect = null;
                image = new BufferedImage(getWidth() + 10, getHeight(),
                        BufferedImage.TYPE_INT_ARGB);// 建立緩衝圖形對像
                Graphics2D gs2d = (Graphics2D) image.getGraphics();// 獲得緩衝圖形對象的Graphics2D對像
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.90f);// 獲得表示透明度的AlphaComposite對像
                gs2d.setComposite(alpha);// 設定透明度
                GradientPaint paint = null;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 3; j++) {
                        paint = new GradientPaint(x, y, Color.white, x + 10,
                                y + 10, Color.gray,true);// 建立循環漸層的GraphientPaint對像
                        gs2d.setPaint(paint);// 設定漸層
                        rect = new Rectangle2D.Float(x, y, 20, 20);// 建立矩形對像
                        gs2d.fill(rect);// 在緩衝圖形對像上繪製矩形
                        y = y + 20;// 計算下一個矩形的y座標
                    }
                    y = 60;// 還原y座標
                    x = x + 20;// 計算x座標
                }
                
                for (int i = 0; i < 3; i++) {// 該for循環，實現3次模糊
                    float[] elements = new float[9];// 定義表示像素份量的陣列
                    for (int j = 0; j < 9; j++) {
                        elements[j] = 0.11f;// 為陣列給予值
                    }
                    convolve(elements);// 呼叫方法，實現模糊功能
                }
                mosaicPanel.repaint();// 呼叫paint()方法
            }
        });
        button.setText("增加馬賽克");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// 建立 Kernel對像
        ConvolveOp op = new ConvolveOp(kernel);// 建立ConvolveOp對像
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // 過濾緩衝圖形對像
    }
    
    // 建立面板類別
    class MosaicPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, getWidth(),getHeight(), this);// 繪製圖形對像
            g2.drawImage(image, 0, 0, this);// 繪製緩衝圖形對像
        }
    }
}
