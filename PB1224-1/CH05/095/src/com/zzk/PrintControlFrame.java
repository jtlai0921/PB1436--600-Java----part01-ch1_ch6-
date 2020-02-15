package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
public class PrintControlFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // 獲得列印對像
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintControlFrame frame = new PrintControlFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintControlFrame() {
        super();
        setTitle("實現列印");
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
                                return Printable.NO_SUCH_PAGE;// 傳回該值表示沒有列印頁
                            Graphics2D g2 = (Graphics2D) graphics; // 獲得圖形上下文對像
                            g2.setColor(Color.BLUE); // 設定目前繪圖顏色
                            Font font = new Font("細明體", Font.BOLD, 24);
                            g2.setFont(font); // 設定字體
                            g2.drawString("靜夜思", 80, 40); // 繪製純文字
                            font = new Font("細明體", Font.BOLD | Font.ITALIC, 18);
                            g2.setFont(font); // 設定字體
                            g2.drawString("李白", 130, 70); // 繪製純文字
                            font = new Font("細明體", Font.PLAIN, 14);
                            g2.setFont(font); // 設定字體
                            g2.drawString("床前明月光，", 40, 100); // 繪製純文字
                            g2.drawString("疑是地上霜。", 120, 100); // 繪製純文字
                            g2.drawString("舉頭望明月，", 40, 120); // 繪製純文字
                            g2.drawString("低頭思故鄉。", 120, 120); // 繪製純文字
                            return Printable.PAGE_EXISTS;// 傳回該值表示存在列印頁
                        }
                    });
                    job.setJobName("列印唐詩《靜夜思》"); // 設定列印工作的名稱
                    job.print(); // 呼叫print()方法開始列印
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("打    印");
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