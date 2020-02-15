package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ImageBrightenFrame extends JFrame {
    private BufferedImage image;// 用於調整亮度的緩衝圖形對像
    private BufferedImage oldImage;// 用於存放調整亮度之前的原緩衝圖形對像
    private ImageBrightenPanel imageBrightenPanel = new ImageBrightenPanel();
    
    public static void main(String args[]) {
        ImageBrightenFrame frame = new ImageBrightenFrame();
        frame.setVisible(true);
    }
    
    public ImageBrightenFrame() {
        super();
        setBounds(100, 100, 357, 276);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("調整圖片的亮度");
        Image img = null;
        try {
            img = ImageIO.read(new File("src/img/image.jpg"));  // 建立圖形對像
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
        image.getGraphics().drawImage(img, 0, 0, null);// 在緩衝圖形對像上繪製圖形
        oldImage = image;// 儲存原來的圖形對象，用於以後的恢復操作
        getContentPane().add(imageBrightenPanel, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float a = 1.0f;// 定義縮放因子
                float b = 5.0f;// 定義偏移量
                RescaleOp op = new RescaleOp(a,b,null);// 建立具有指定縮放因子和偏移量的 RescaleOp對像
                image = op.filter(image, null);// 對源圖形中的資料進行逐像素重縮放，達到變亮的效果
                repaint();// 重新繪製圖形
            }
        });
        button.setText("變    亮");
        panel.add(button);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float a = 1.0f;// 定義縮放因子
                float b = -5.0f;// 定義偏移量
                RescaleOp op = new RescaleOp(a,b,null);// 建立具有指定縮放因子和偏移量的 RescaleOp對像
                image = op.filter(image, null);// 對源圖形中的資料進行逐像素重縮放，達到變暗的效果
                repaint();// 重新繪製圖形
            }
        });
        button_3.setText("變    暗");
        panel.add(button_3);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                image = oldImage;  // 獲得變亮前的圖形
                imageBrightenPanel.repaint();// 重新繪製原圖形，即恢復為變亮前的圖形
            }
        });
        button_2.setText("恢    復");
        panel.add(button_2);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
    }
    
    class ImageBrightenPanel extends JPanel {
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);  // 將緩衝圖形對像繪製到面板上
            }
        }
    }
}
