package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.zzk.panel.BackgroundPanel;

@SuppressWarnings("serial")
public class ExpressPrintFrame extends JFrame {
    private URL url = null;// 宣告圖片的URL
    private Image image = null;// 宣告圖形對像
    private BackgroundPanel backPanel = null;// 宣告自定義背景面板對像
    private Robot robot = null; // 宣告Robot對像
    private BufferedImage buffImage = null; // 宣告緩衝圖形對像
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExpressPrintFrame frame = new ExpressPrintFrame();
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
    public ExpressPrintFrame() {
        super();
        setTitle("列印快遞單");
        setBounds(0, 0, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        url = ExpressPrintFrame.class.getResource("/image/express.jpg"); // 獲得圖片的URL
        image = new ImageIcon(url).getImage(); // 建立圖形對像
        backPanel = new BackgroundPanel(image);
        backPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {// 單擊鼠標右鍵
                    int x = e.getX();// 獲得鼠標位置的X座標
                    int y = e.getY();// 獲得鼠標位置的Y座標
                    TargetTextField tf = new TargetTextField();// 建立自定義純文字框的實例
                    tf.addMouseListener(tf);// 增加鼠標監聽器
                    tf.addMouseMotionListener(tf);// 增加鼠標監聽器
                    tf.addActionListener(tf);// 增加動作監聽器
                    tf.setBounds(x, y, 147, 22);// 指定純文字框的位置和大小
                    backPanel.add(tf);// 增加到背景面板上
                    tf.requestFocus();// 使純文字框獲得焦點
                }
            }
        });
        backPanel.setLayout(null);
        getContentPane().add(backPanel);
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrinterJob job = PrinterJob.getPrinterJob(); // 獲得列印對像
                    if (!job.printDialog()) // 開啟列印交談視窗
                        return;// 單擊列印交談視窗的取消按鈕或關閉列印交談視窗結束程式的執行
                    job.setPrintable(new Printable() {
                        // 實現print()方法，繪製列印內容
                        public int print(Graphics graphics,
                                PageFormat pf, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // 獲得圖形上下文對像
                            int x = (int)(ExpressPrintFrame.this.getBounds().getX())+8;// 背景面板在屏幕上的X座標
                            int y = (int)(ExpressPrintFrame.this.getBounds().getY())+30;// 背景面板在屏幕上的Y座標
                            int w = (int)backPanel.getBounds().getWidth();// 背景面板的寬度
                            int h = (int)backPanel.getBounds().getHeight();// 背景面板的高度
                            Rectangle rect = new Rectangle(x, y, w, h);// 建立Rectangle對像
                            buffImage = robot.createScreenCapture(rect);// 獲得緩衝圖形對像
                            int imgWidth = buffImage.getWidth();// 圖形的寬度
                            int imgHeight = buffImage.getHeight();// 圖形的高度
                            float wh = imgWidth / imgHeight;// 圖形寬高比
                            int printX = (int) pf.getImageableX();// 獲得可列印區域的x座標
                            int printY = (int) pf.getImageableY();// 獲得可列印區域的y座標
                            int width = (int) pf.getImageableWidth();// 獲得可列印區域的寬度
                            int height = (int) pf.getImageableHeight();// 獲得可列印區域的高度
                            if (imgWidth > width) { // 如果寬大於可列印區域
                                imgWidth = width;
                                imgHeight = (int)(imgHeight * wh);
                            }
                            if (imgHeight > height) { // 如果高大於可列印區域
                                imgHeight = height;
                                imgWidth = (int)(imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth, imgHeight, ExpressPrintFrame.this);// 將緩衝圖形繪製到列印頁
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("列印快遞單"); // 設定列印工作的名稱
                    job.print(); // 呼叫print()方法開始列印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("列印快遞單");
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
}
