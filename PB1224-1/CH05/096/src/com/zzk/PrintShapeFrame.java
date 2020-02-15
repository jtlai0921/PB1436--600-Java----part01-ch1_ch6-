package com.zzk;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class PrintShapeFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 獲得列印對像
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintShapeFrame frame = new PrintShapeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintShapeFrame() {
        super();
        setTitle("列印圖形");
        getContentPane().setLayout(null);
        setBounds(100, 100, 281, 179);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    if (!job.printDialog()) // 開啟列印交談視窗
                        return;// 單擊列印交談視窗的取消按鈕或關閉列印交談視窗結束程式的執行
                    job.setPrintable(new Printable() {
                        // 實現print()方法，繪製列印內容
                        public int print(Graphics graphics,
                                PageFormat pageFormat, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D)graphics; // 獲得Graphics2D對像
                            BasicStroke stroke = new BasicStroke(3); // 建立寬度是3的筆畫對像
                            g2.setStroke(stroke);// 設定筆畫對像
                            Color color = new Color(0,162,232);// 建立顏色對像
                            g2.setColor(color);// 設定顏色
                            g2.drawOval(30, 40, 60, 60);     // 繪製第一個圓
                            color = new Color(233,123,16);   // 建立新的顏色對像
                            g2.setColor(color);// 設定顏色
                            g2.drawOval(60, 70, 60, 60);     // 繪製第二個圓
                            color = new Color(28,20,100);// 建立新的顏色對像
                            g2.setColor(color);// 設定顏色
                            g2.drawOval(92, 40, 60, 60);     // 繪製第三個圓
                            color = new Color(0,255,0);// 建立新的顏色對像
                            g2.setColor(color);// 設定顏色
                            g2.drawOval(122, 70, 60, 60);    // 繪製第四個圓
                            color = new Color(255,0,0);// 建立新的顏色對像
                            g2.setColor(color);// 設定顏色
                            g2.drawOval(154, 40, 60, 60);    // 繪製第五個圓
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("列印五環圖形"); // 設定列印工作的名稱
                    job.print(); // 呼叫print()方法開始列印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("列印圖形");
        button.setBounds(28, 56, 86, 28);
        getContentPane().add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退    出");
        button_1.setBounds(151, 56, 86, 28);
        getContentPane().add(button_1);
        
        //
    }
    
}
