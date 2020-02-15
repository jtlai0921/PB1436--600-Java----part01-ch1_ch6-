package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FileDialog;
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
public class SaveMixPictureFrame extends JFrame {
    private Image img1 = null; // 宣告圖形對像
    private Image img2 = null; // 宣告圖形對像
    private boolean mixFlag = false;// 用於決定是否溶合圖片，為true時溶合圖片
    private boolean firstOrSecondFlag = false;// 為false時顯示第一張圖片，為true時顯示第二張圖片
    private MixPicturePanel mixPicturePanel = null; // 宣告圖形面板對像
    private int panelWidth = 0;// 圖形面板的寬度
    private int panelHeight = 0;// 圖形面板的高度
    public static void main(String args[]) {
        SaveMixPictureFrame frame = new SaveMixPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveMixPictureFrame() {
        super();
        URL imgUrl = SaveMixPictureFrame.class.getResource("/img/img.jpg");// 獲得圖片資源的路徑
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imgUrl = SaveMixPictureFrame.class.getResource("/img/imag.jpg");// 獲得圖片資源的路徑
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        mixPicturePanel = new MixPicturePanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 476, 336); // 設定窗體大小和位置
        this.add(mixPicturePanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("溶合兩張圖片並儲存"); // 設定窗體標題
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = false;// 標記不繪製第二張圖片
                mixFlag = false;// 標記沒溶合圖片
                mixPicturePanel.repaint();// 呼叫paint()方法繪製第一張圖片
            }
        });
        button_2.setText("第一張");
        panel.add(button_2);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// 標記繪製第二張圖片
                mixFlag = false;// 標記沒溶合圖片
                mixPicturePanel.repaint();// 呼叫paint()方法繪製第一張圖片
            }
        });
        button_3.setText("第二張");
        panel.add(button_3);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// 標記繪製第二張圖片
                mixFlag = true;// 標記溶合圖片
                mixPicturePanel.repaint();// 呼叫paint()方法繪製第一張圖片
            }
        });
        button.setText("溶合圖片");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!mixFlag) {
                    JOptionPane.showMessageDialog(null,"還沒有溶合圖片。");// 顯示提示資訊
                    return;
                }
                FileDialog dialog = new FileDialog(SaveMixPictureFrame.this,"儲存");// 建立交談視窗
                dialog.setMode(FileDialog.SAVE);// 設定交談視窗為儲存交談視窗
                dialog.setVisible(true);// 顯示儲存交談視窗
                String path = dialog.getDirectory();// 獲得檔案的儲存路徑
                String fileName = dialog.getFile();// 獲得儲存的檔案名
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// 檔案擴充名,不含點
                String pathAndName = path + "\\" + fileName;// 檔案的完整路徑
                BufferedImage bufferImage = new BufferedImage(panelWidth, panelHeight,
                        BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// 獲得繪圖上下文對像
                g2.drawImage(img1, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// 繪製圖形
                    AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 獲得表示透明度的AlphaComposite對像
                    g2.setComposite(alpha);// 指定AlphaComposite對像
                    g2.drawImage(img2, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// 繪製調整透明度後的圖片
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
        
        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_4.setText("退    出");
        panel.add(button_4);
        
    }
    
    // 建立面板類別
    class MixPicturePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            panelWidth = getWidth();// 獲得圖形面板的寬度
            panelHeight = getHeight();// 獲得圖形面板的高度
            g2.drawImage(img1, 0, 0, panelWidth, panelHeight, this);// 繪製圖形
            if (mixFlag) {// 標記溶合圖片
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// 獲得表示透明度的AlphaComposite對像
                g2.setComposite(alpha);// 指定AlphaComposite對像
            }
            if (firstOrSecondFlag) {// 標記繪製第二張圖片
                g2.drawImage(img2, 0, 0, panelWidth, panelHeight, this);// 繪製調整透明度後的圖片
            }
        }
    }
}
