package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
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
public class SaveZoomImageFrame extends JFrame {
    private JButton button_3;
    private JButton button_2;
    private JButton button_1;
    private JButton button;
    private JPanel panel;
    private Image img = null; // 宣告圖形對像
    private ZoomImagePanel imagePanel = null; // 宣告圖形面板對像
    private int imgWidth, imgHeight;// 用於儲存圖片的寬度和高度
    private int newW, newH;// 用於儲存圖片縮放後的寬度和高度
    private float value = 50.0f;// 控制圖形大小的變數
    
    public static void main(String args[]) {
        SaveZoomImageFrame frame = new SaveZoomImageFrame();
        frame.setVisible(true);
    }
    
    public SaveZoomImageFrame() {
        super();
        this.setTitle("縮放圖片並儲存"); // 設定窗體標題
        URL imgUrl = SaveZoomImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imagePanel = new ZoomImagePanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 355, 253); // 設定窗體大小和位置
        this.add(imagePanel); // 在窗體中部位置增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        getContentPane().add(getPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * @return
     */
    protected JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.add(getButton());
            panel.add(getButton_1());
            panel.add(getButton_2());
            panel.add(getButton_3());
        }
        return panel;
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value += 5;// 調整value的值，用於放大圖片
                    if (value >= 200.0f) {// 如果value的值大於等於200
                        value = 200.0f;// 使value的值等於200
                    }
                    imagePanel.repaint();// 重新呼叫面板類別的paint()方法
                }
            });
            button.setText("放  大");
        }
        return button;
    }
    
    /**
     * @return
     */
    protected JButton getButton_1() {
        if (button_1 == null) {
            button_1 = new JButton();
            button_1.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value -= 5;// 調整value的值，用於縮小圖片
                    if (value <= 0.0f) {// 如果value的值小於等於0
                        value = 0.0f;// 使value的值等於0
                    }
                    imagePanel.repaint();// 重新呼叫面板類別的paint()方法
                }
            });
            button_1.setText("縮    小");
        }
        return button_1;
    }
    
    /**
     * @return
     */
    protected JButton getButton_2() {
        if (button_2 == null) {
            button_2 = new JButton();
            button_2.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (newW <= 0 || newH <= 0) {
                        JOptionPane.showMessageDialog(null, "圖形的寬度和高度必須大於0");// 顯示提示資訊
                        return;
                    }
                    FileDialog dialog = new FileDialog(SaveZoomImageFrame.this,
                            "儲存");// 建立交談視窗
                    dialog.setMode(FileDialog.SAVE);// 設定交談視窗為儲存交談視窗
                    dialog.setVisible(true);// 顯示儲存交談視窗
                    String path = dialog.getDirectory();// 獲得檔案的儲存路徑
                    String fileName = dialog.getFile();// 獲得儲存的檔案名
                    if (path == null || fileName == null) {
                        return;
                    }
                    String fileExtName = fileName.substring(fileName
                            .indexOf(".") + 1);// 檔案擴充名,不含點
                    String pathAndName = path + "\\" + fileName;// 檔案的完整路徑
                    BufferedImage bufferImage = new BufferedImage(newW, newH,
                            BufferedImage.TYPE_INT_RGB);// 建立緩衝圖形對像
                    Graphics g = bufferImage.getGraphics();// 獲得繪圖上下文對像
                    g.drawImage(img, 0, 0, newW, newH, null);// 在緩衝圖形上繪製圖形
                    try {
                        ImageIO.write(bufferImage, fileExtName, new File(
                                pathAndName));// 將緩衝圖形儲存到磁碟
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "儲存失敗\n"
                                + e1.getMessage());// 顯示提示資訊
                    }
                }
            });
            button_2.setText("保    存");
        }
        return button_2;
    }
    
    /**
     * @return
     */
    protected JButton getButton_3() {
        if (button_3 == null) {
            button_3 = new JButton();
            button_3.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    System.exit(0);
                }
            });
            button_3.setText("退    出");
        }
        return button_3;
    }
    
    // 建立面板類別
    class ZoomImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清除繪圖上下文的內容
            imgWidth = img.getWidth(this); // 獲得圖片寬度
            imgHeight = img.getHeight(this); // 獲得圖片高度
            newW = (int) (imgWidth * value / 100);// 計算圖片縮放後的寬度
            newH = (int) (imgHeight * value / 100);// 計算圖片縮放後的高度
            g.drawImage(img, 0, 0, newW, newH, this);// 繪製指定大小的圖片
        }
    }
    
}
