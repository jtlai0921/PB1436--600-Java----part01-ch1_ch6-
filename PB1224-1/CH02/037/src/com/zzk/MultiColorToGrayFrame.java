package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MultiColorToGrayFrame extends JFrame {
    private BufferedImage image;
    private ColorToGrayPanel colorToGrayPanel = new ColorToGrayPanel();
    
    public static void main(String args[]) {
        MultiColorToGrayFrame frame = new MultiColorToGrayFrame();
        frame.setVisible(true);
    }
    
    public MultiColorToGrayFrame() {
        super();
        setBounds(100, 100, 357, 276);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("彩色圖形轉為灰階");
        Image img = null;
        try {
            img = ImageIO.read(new File("src/img/image.jpg"));  // 建立圖形對像
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
        image.getGraphics().drawImage(img, 0, 0, null);// 在緩衝圖形對像上繪製圖形
        
        getContentPane().add(colorToGrayPanel, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ColorSpace colorSpace1 = ColorSpace
                        .getInstance(ColorSpace.CS_GRAY);// 建立內建線性為灰階的顏色空間
                ColorSpace colorSpace2 = ColorSpace
                .getInstance(ColorSpace.CS_LINEAR_RGB);// 建立內建線性為 RGB的顏色空間
                ColorConvertOp op = new ColorConvertOp(colorSpace1,colorSpace2,
                        null);// 建立進行顏色轉換的對象
                image = op.filter(image, null);// 對緩衝圖形進行顏色轉換
                repaint();// 重新繪製圖形
            }
        });
        button.setText("轉為灰階");
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
    
    class ColorToGrayPanel extends JPanel {
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);  // 將緩衝圖形對像繪製到面板上
            }
        }
    }
}
