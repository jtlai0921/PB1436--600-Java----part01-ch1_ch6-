package com.zzk;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PartImageFrame extends JFrame {
    private Image img = null;  // 宣告圖形對像
    private PartImagePanel imagePanel = null;  // 宣告圖形面板對像
    private int dx1, dy1, dx2, dy2;   // 目標矩形第一個角與第二個角的X、Y座標
    private int sx1, sy1, sx2, sy2;   // 源矩形第一個角與第二個角的X、Y座標
    public static void main(String args[]) {
        PartImageFrame frame = new PartImageFrame();
        frame.setVisible(true);
    }
    public PartImageFrame() {
        super();
        URL imgUrl = PartImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        dx2 = sx2 = 340; // 初始化圖形大小
        dy2 = sy2 = 200; // 初始化圖形大小
        imagePanel = new PartImagePanel();  // 建立圖形面板對像
        this.setBounds(200, 160, 355, 276); // 設定窗體大小和位置
        this.add(imagePanel); // 在窗體中部位置增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("翻轉圖形"); // 設定窗體標題
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JButton btn_h = new JButton();
        btn_h.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 下面3行程式碼用於交換sx1和sx2的值
                int x = sx1;
                sx1 = sx2;
                sx2 = x;
                imagePanel.repaint();  // 重新呼叫面板類別的paint()方法
            }
        });
        btn_h.setText("水平翻轉");
        panel.add(btn_h);
        final JButton btn_v = new JButton();
        btn_v.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 下面3行程式碼用於交換sy1和sy2的值
                int y = sy1;
                sy1 = sy2;
                sy2 = y;
                imagePanel.repaint();// 重新呼叫面板類別的paint()方法
            }
        });
        btn_v.setText("垂直翻轉");
        panel.add(btn_v);
    }
    // 建立面板類別
    class PartImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清除繪圖上下文的內容
            g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, this);// 繪製圖形
        }
    }
}
