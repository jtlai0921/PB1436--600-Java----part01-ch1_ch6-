package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NegativeImageFrame extends JFrame {
    private BufferedImage image;// 宣告緩衝圖形對像
    private NegativeImagePanel negativeImagePanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        NegativeImageFrame frame = new NegativeImageFrame();
        frame.setVisible(true);
    }
    
    public NegativeImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片反向特效"); // 設定窗體標題
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativeImagePanel = new NegativeImagePanel(); // 建立圖形面板對像
        this.add(negativeImagePanel); // 在窗體上增加圖形面板對像
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256*1];// 建立表示顏色反向的份量陣列
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// 為陣列給予值
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// 建立尋找表對像
                LookupOp op = new LookupOp(table,null);// 建立實現從源到目標尋找操作的LookupOp對像
                image = op.filter(image, null);// 呼叫LookupOp對象的filter()方法，實現圖形反向功能
                repaint();  // 呼叫paint()方法
            }
        });
        button.setText("反    向");
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
    
    
    // 建立面板類別
    class NegativeImagePanel extends JPanel {
        public NegativeImagePanel(){
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