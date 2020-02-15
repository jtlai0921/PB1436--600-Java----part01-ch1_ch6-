package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class SaveWatermarkPictureFrame extends JFrame {
    private boolean watermark = false;// 水印標記，為true時繪製水印
    private Image img = null; // 宣告圖形對像
    private DrawWatermarkPanel watermarkPanel = null; // 宣告圖形面板對像
    
    public static void main(String args[]) {
        SaveWatermarkPictureFrame frame = new SaveWatermarkPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveWatermarkPictureFrame() {
        super();
        setTitle("為圖片增加水印並儲存"); // 設定窗體標題
        URL imgUrl = SaveWatermarkPictureFrame.class
                .getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        watermarkPanel = new DrawWatermarkPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 420, 320); // 設定窗體大小和位置
        this.add(watermarkPanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                watermark = true;
                watermarkPanel.repaint();// 呼叫paint()方法繪製水印
            }
        });
        button.setText("增加水印");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!watermark) {
                    JOptionPane.showMessageDialog(null,"還沒有為圖片增加水印。");// 顯示提示資訊
                    return;
                }
                FileDialog dialog = new FileDialog(SaveWatermarkPictureFrame.this,"儲存");// 建立交談視窗
                dialog.setMode(FileDialog.SAVE);// 設定交談視窗為儲存交談視窗
                dialog.setVisible(true);// 顯示儲存交談視窗
                String path = dialog.getDirectory();// 獲得檔案的儲存路徑
                String fileName = dialog.getFile();// 獲得儲存的檔案名
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// 檔案擴充名,不含點
                String pathAndName = path + "\\" + fileName;// 檔案的完整路徑
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveWatermarkPictureFrame.this), img
                        .getHeight(SaveWatermarkPictureFrame.this),
                        BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// 獲得繪圖上下文對像
                g2.drawImage(img, 0, 0, img.getWidth(SaveWatermarkPictureFrame.this), 
                        img.getHeight(SaveWatermarkPictureFrame.this), null);// 在緩衝圖形上繪製圖形
                g2.rotate(Math.toRadians(-30));// 旋轉繪圖上下文對像
                Font font = new Font("楷體", Font.BOLD, 72);// 建立字體對像
                g2.setFont(font);// 指定字體
                g2.setColor(Color.RED);// 指定顏色
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 獲得表示透明度的AlphaComposite對像
                g2.setComposite(alpha);// 指定AlphaComposite對像
                g2.drawString("編程詞典", -30, 240);// 繪製純文字,實現水印
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// 將緩衝圖形儲存到磁碟
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"儲存失敗\n"+e1.getMessage());// 顯示提示資訊
                }
            }
        });
        button_1.setText("儲存圖片");
        panel.add(button_1);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_2.setText("退    出");
        panel.add(button_2);
    }
    
    // 建立面板類別
    class DrawWatermarkPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// 繪製圖形
            if (watermark) {
                g2.rotate(Math.toRadians(-30));// 旋轉繪圖上下文對像
                Font font = new Font("楷體", Font.BOLD, 72);// 建立字體對像
                g2.setFont(font);// 指定字體
                g2.setColor(Color.RED);// 指定顏色
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 獲得表示透明度的AlphaComposite對像
                g2.setComposite(alpha);// 指定AlphaComposite對像
                g2.drawString("編程詞典", -30, 240);// 繪製純文字,實現水印
            }
        }
    }
}
