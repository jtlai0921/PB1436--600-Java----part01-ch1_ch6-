package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveTextureFillFrame extends JFrame {
    private TextureFillPanel textureFillPanel = null; // 宣告面板對像
    private int newW, newH;// 用於儲存圖片縮放後的寬度和高度
    
    public static void main(String args[]) {
        SaveTextureFillFrame frame = new SaveTextureFillFrame();
        frame.setVisible(true);
    }
    
    public SaveTextureFillFrame() {
        super();
        setTitle("填充紋理並儲存為圖片"); // 設定窗體標題
        textureFillPanel = new TextureFillPanel(); // 建立圖形面板對像
        setBounds(200, 160, 346, 285); // 設定窗體大小和位置
        add(textureFillPanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                FileDialog dialog = new FileDialog(SaveTextureFillFrame.this,
                        "儲存");// 建立交談視窗
                dialog.setMode(FileDialog.SAVE);// 設定交談視窗為儲存交談視窗
                dialog.setVisible(true);// 顯示儲存交談視窗
                String path = dialog.getDirectory();// 獲得檔案的儲存路徑
                String fileName = dialog.getFile();// 獲得儲存的檔案名
                if (path == null || fileName == null) {
                    JOptionPane.showMessageDialog(null, "請指定檔案的儲存路徑和檔案名。");// 顯示提示資訊
                    return;
                }
                String fileExtName = fileName
                        .substring(fileName.indexOf(".") + 1);// 檔案擴充名,不含點
                String pathAndName = path + "\\" + fileName;// 檔案的完整路徑
                BufferedImage image = new BufferedImage(200, 200,
                        BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
                Graphics2D g2 = image.createGraphics();// 獲得緩衝圖形對象的繪圖上下文對像
                g2.setColor(Color.BLUE);// 設定顏色
                g2.fillRect(0, 0, 90, 90);// 繪製填充矩形
                g2.setColor(Color.RED);// 設定顏色
                g2.fillOval(95, 0, 90, 90);// 繪製帶填充色的圓形
                g2.setColor(Color.GREEN);// 設定顏色
                g2.fillRect(95, 95, 90, 90);// 繪製填充矩形
                g2.setColor(Color.ORANGE);// 設定顏色
                g2.fillOval(0, 95, 90, 90);// 繪製帶填充色的圓形
                Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// 建立Rectangle2D對像
                TexturePaint textPaint = new TexturePaint(image, rect);// 建立紋理填充對像
                BufferedImage bufferImage = new BufferedImage(newW, newH,
                        BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
                Graphics g = bufferImage.getGraphics();// 獲得緩衝圖形的繪圖上下文對像
                Graphics2D graphics2 = (Graphics2D) g;// 轉換繪圖上下文對像為Graphics2D型態
                graphics2.setPaint(textPaint);// 為繪圖上下文對像指定紋理填充對像
                Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW,
                        newH);// 建立矩形對像
                graphics2.fill(ellipse2);// 繪製填充紋理的矩形
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// 將緩衝圖形儲存到磁碟
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "儲存失敗\n"
                            + e1.getMessage());// 顯示提示資訊
                }
            }
        });
        button.setText("儲存為圖片");
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
    class TextureFillPanel extends JPanel {
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
            Graphics2D g2 = image.createGraphics();// 獲得緩衝圖形對象的繪圖上下文對像
            g2.setColor(Color.BLUE);// 設定顏色
            g2.fillRect(0, 0, 90, 90);// 繪製填充矩形
            g2.setColor(Color.RED);// 設定顏色
            g2.fillOval(95, 0, 90, 90);// 繪製帶填充色的圓形
            g2.setColor(Color.GREEN);// 設定顏色
            g2.fillRect(95, 95, 90, 90);// 繪製填充矩形
            g2.setColor(Color.ORANGE);// 設定顏色
            g2.fillOval(0, 95, 90, 90);// 繪製帶填充色的圓形
            Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// 建立Rectangle2D對像
            TexturePaint textPaint = new TexturePaint(image, rect);// 建立紋理填充對像
            Graphics2D graphics2 = (Graphics2D) g;// 轉換paint()方法的繪圖上下文對像
            graphics2.setPaint(textPaint);// 為繪圖上下文對像設定紋理填充對像
            newW = getWidth();
            newH = getHeight();
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW, newH);// 建立矩形對像
            graphics2.fill(ellipse2);// 繪製填充紋理的矩形
        }
    }
}
