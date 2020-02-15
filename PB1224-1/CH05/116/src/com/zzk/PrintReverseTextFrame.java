package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PrintReverseTextFrame extends JFrame {
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
                    PrintReverseTextFrame frame = new PrintReverseTextFrame();
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
    public PrintReverseTextFrame() {
        super();
        setTitle("鏡面效果純文字列印");
        job = PrinterJob.getPrinterJob();
        
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
        getContentPane().add(scrollPane);
        
        final JPanel panel_1 = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);
        panel_1.setLayout(flowLayout);
        scrollPane.setViewportView(panel_1);
        
        final PrintPanel printPanel = new PrintPanel();
        printPanel.setLayout(null);
        panel_1.add(printPanel);
        printPanel.setBorder(new LineBorder(Color.ORANGE, 2, false));
        printPanel.setName("printPanel");
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JToggleButton reverseBtn = new JToggleButton();
        reverseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                boolean reverse = reverseBtn.isSelected();
                printPanel.setReverse(reverse);// 重新繪製列印面板
            }
        });
        reverseBtn.setText("鏡面效果/還原");
        panel.add(reverseBtn);
        
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