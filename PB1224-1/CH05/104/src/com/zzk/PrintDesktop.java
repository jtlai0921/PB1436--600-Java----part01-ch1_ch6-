package com.zzk;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PrintDesktop extends JFrame {
    private PrinterJob job;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintDesktop frame = new PrintDesktop();
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
    public PrintDesktop() {
        super();
        setTitle("列印桌面圖片");
        job = PrinterJob.getPrinterJob();
        
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(scrollPane);
        
        final JPanel panel_1 = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(30);
        flowLayout.setHgap(30);
        panel_1.setLayout(flowLayout);
        scrollPane.setViewportView(panel_1);
        
        final PrintPanel printPanel = new PrintPanel();
        printPanel.setBorder(new LineBorder(Color.ORANGE, 2, false));
        printPanel.setName("printPanel");
        panel_1.add(printPanel);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton getDesktopBtn = new JButton();
        getDesktopBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    Robot robot = new Robot();// 建立Robot類別的實例對像
                    Dimension size = getToolkit().getScreenSize();// 獲得屏幕大小
                    Rectangle screenRect = new Rectangle(0, 0, size.width,
                            size.height);// 建立屏幕大小的矩形對像
                    setVisible(false);// 隱藏窗體
                    Thread.sleep(1000);// 休眠1秒鐘
                    BufferedImage image = robot.createScreenCapture(screenRect);// 抓起屏幕圖形
                    setVisible(true);// 顯示窗體
                    printPanel.setImage(image);// 為列印面板設定圖形
                    printPanel.repaint();// 重新繪製列印面板
                } catch (AWTException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        getDesktopBtn.setText("獲得桌面");
        panel.add(getDesktopBtn);
        
        final JButton pageSetBtn = new JButton();
        pageSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (job != null) {// 如果列印對像不為NULL
                    printPanel.pageSet(job);// 呼叫列印面板的頁面設定方法
                }
            }
        });
        pageSetBtn.setText("頁面設定");
        panel.add(pageSetBtn);
        
        final JButton printBtn = new JButton();
        printBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                boolean print = job.printDialog();// 顯示列印交談視窗
                if (print) {// 如果使用者確認列印
                    try {
                        job.setJobName("列印桌面圖片");// 設定列印工作名稱
                        job.setPrintable(printPanel);// 設定列印頁面為列印面板
                        job.print();// 開始列印工作
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        printBtn.setText("列印");
        panel.add(printBtn);
        //
    }
    
}
