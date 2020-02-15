package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlurImageFrame extends JFrame {
    private BufferedImage image;// 宣告緩衝圖形對像
    private BlurImagePanel blurImagePanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        BlurImageFrame frame = new BlurImageFrame();
        frame.setVisible(true);
    }
    
    public BlurImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片模糊特效"); // 設定窗體標題
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        blurImagePanel = new BlurImagePanel(); // 建立圖形面板對像
        this.add(blurImagePanel); // 在窗體上增加圖形面板對像
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = new float[9];// 定義表示像素份量的陣列
                for (int i = 0; i < 9; i++) {
                    elements[i] = 0.11f;// 為陣列給予值
                }
                convolve(elements);// 呼叫方法，實現模糊功能
            }
        });
        button.setText("模    糊");
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
        repaint();// 呼叫paint()方法
    }
    
    // 建立面板類別
    class BlurImagePanel extends JPanel {
        public BlurImagePanel(){
            Image img = null;// 宣告建立圖形對像
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // 建立圖形對像
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
            image.getGraphics().drawImage(img, 0, 0,  null);// 在緩衝圖形對像上繪製圖形
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// 繪製緩衝圖形對像
            }
        }
        
    }
}
