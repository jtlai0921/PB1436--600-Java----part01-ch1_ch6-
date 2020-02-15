package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TranslucenceImageFrame extends JFrame {
    private Image img = null; // 宣告圖形對像
    private TranslucenceImagePanel translucencePanel = null; // 宣告圖形面板對像
    private AlphaComposite alpha = AlphaComposite.SrcOver.derive(1.0f);// 建立表示不透明的AlphaComposite對像
    public static void main(String args[]) {
        TranslucenceImageFrame frame = new TranslucenceImageFrame();
        frame.setVisible(true);
    }
    
    public TranslucenceImageFrame() {
        super();
        URL imgUrl = TranslucenceImageFrame.class.getResource("/img/imag.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        translucencePanel = new TranslucenceImagePanel(); // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(translucencePanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("圖片半透明特效"); // 設定窗體標題

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(0.5f);// 獲得表示半透明的AlphaComposite對像
                translucencePanel.repaint();// 呼叫paint()方法
            }
        });
        button.setText("半透明");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(1.0f);// 獲得表示不透明的AlphaComposite對像
                translucencePanel.repaint();// 呼叫paint()方法
            }
        });
        button_1.setText("不透明");
        panel.add(button_1);
    }
    
    // 建立面板類別
    class TranslucenceImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// 獲得Graphics2D對像
            g2.clearRect(0, 0,  getWidth(), getHeight());// 清除繪圖上下文的內容
            g2.setComposite(alpha);// 指定AlphaComposite對像
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// 繪製圖形
        }
        
    }
}
