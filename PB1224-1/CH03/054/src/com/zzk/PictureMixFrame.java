package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class PictureMixFrame extends JFrame {
    private Image img1 = null; // 宣告圖形對像
    private Image img2 = null; // 宣告圖形對像
    private JSlider slider = null;
    private PictureMixPanel pictureMixPanel = null; // 宣告圖形面板對像
    public static void main(String args[]) {
        PictureMixFrame frame = new PictureMixFrame();
        frame.setVisible(true);
    }
    
    public PictureMixFrame() {
        super();
        URL imgUrl = PictureMixFrame.class.getResource("/img/img.jpg");// 獲得圖片資源的路徑
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imgUrl = PictureMixFrame.class.getResource("/img/imag.jpg");// 獲得圖片資源的路徑
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        pictureMixPanel = new PictureMixPanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(pictureMixPanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片溶合特效"); // 設定窗體標題

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                pictureMixPanel.repaint();// 重新呼叫面板類別的paint()方法
            }
        });
        getContentPane().add(slider, BorderLayout.SOUTH);
    }
    
    // 建立面板類別
    class PictureMixPanel extends JPanel {
        boolean flag = true;// 定義標記變數，用於控制x的值
        AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.5f);// 獲得表示透明度的AlphaComposite對像
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            g2.drawImage(img1, 0, 0,  getWidth(), getHeight(), this);// 繪製圖形
            float value = slider.getValue();// 滑動區塊元件的取值
            float alphaValue = value / 100;// 計算透明度的值
            alpha = AlphaComposite.SrcOver.derive(alphaValue);// 獲得表示透明度的AlphaComposite對像
            g2.setComposite(alpha);// 指定AlphaComposite對像
            g.drawImage(img2, 0, 0, getWidth(), getHeight(), this);// 繪製調整透明度後的圖片
        }
        
    }
}
