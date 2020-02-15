package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveImageFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private DrawImagePanel imagePanel = null; // 宣告圖形面板對像
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SaveImageFrame frame = new SaveImageFrame();
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
    public SaveImageFrame() {
        super();
        setTitle("儲存圖片檔案"); // 設定窗體標題
        setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = SaveImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imagePanel = new DrawImagePanel(); // 建立圖形面板對像
        add(imagePanel); // 在窗體上增加圖形面板對像
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveImageFrame.this), img
                        .getHeight(SaveImageFrame.this),
                        BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
                Graphics g = bufferImage.getGraphics();// 獲得繪圖上下文對像
                g.drawImage(img, 0, 0, null);// 在緩衝圖形上繪製圖形
                try {
                    ImageIO.write(bufferImage, "jpg", new File("c:/image.jpg"));// 將緩衝圖形儲存到磁碟
                    JOptionPane.showMessageDialog(null,
                            "已將圖片image.jpg\n成功地儲存到C:碟");// 顯示提示資訊
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("儲存圖片");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        panel.add(button_1);
        //
    }
    
    // 建立面板類別
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, this); // 繪製指定的圖片
        }
    }
}
