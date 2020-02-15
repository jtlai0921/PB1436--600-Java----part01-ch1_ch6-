package com.zzk;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawEllipseImageFrame extends JFrame {
    private Image img = null;  // 宣告圖形對像
    private EllipseImagePanel imagePanel = null;  // 宣告圖形面板對像
    public static void main(String args[]) {
        DrawEllipseImageFrame frame = new DrawEllipseImageFrame();
        frame.setVisible(true);
    }
    public DrawEllipseImageFrame() {
        super();
        URL imgUrl = DrawEllipseImageFrame.class.getResource("/img/image.jpg");// 獲得圖片資源的路徑
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 獲得圖形資源
        imagePanel = new EllipseImagePanel();  // 建立圖形面板對像
        this.setBounds(200, 160, 316, 237); // 設定窗體大小和位置
        this.add(imagePanel); // 在窗體上增加圖形面板對像
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定窗體關閉模式
        this.setTitle("以橢圓形顯示圖形"); // 設定窗體標題
    }
    // 建立面板類別
    class EllipseImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(img, 20, 20, 260, 160, this);// 繪製圖形
            Rectangle2D.Float rectangle = new Rectangle2D.Float(0, 0, getWidth(),getHeight());// 建立矩形對像
            Ellipse2D.Float ellipse = new Ellipse2D.Float(20, 20, 260, 160);// 建立橢圓形對像
            Area area1 = new Area(rectangle);   // 建立區域矩形
            Area area2 = new Area(ellipse);   // 建立區域橢圓
            area1.subtract(area2);// 兩個區域形狀進行減運算
            g2.setColor(getBackground());// 設定繪圖上下文的顏色為面板的背景顏色
            g2.fill(area1);  // 繪製減運算後的區域形狀
        }
    }
}
