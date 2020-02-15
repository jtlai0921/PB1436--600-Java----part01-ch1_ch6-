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
 * @author �i���[
 */
@SuppressWarnings("serial")
public class PrintControlFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��o�C�L�ﹳ
    
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
        setTitle("��{�C�L");
        getContentPane().setLayout(null);
        setBounds(100, 100, 281, 179);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    if (!job.printDialog()) // �}�ҦC�L��͵���
                        return;// �����C�L��͵������������s�������C�L��͵��������{��������
                    job.setPrintable(new Printable() {
                        // ��{print()��k�Aø�s�C�L���e
                        public int print(Graphics graphics,
                                PageFormat pageFormat, int pageIndex)
                                throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;// �Ǧ^�ӭȪ�ܨS���C�L��
                            Graphics2D g2 = (Graphics2D) graphics; // ��o�ϧΤW�U��ﹳ
                            g2.setColor(Color.BLUE); // �]�w�ثeø���C��
                            Font font = new Font("�ө���", Font.BOLD, 24);
                            g2.setFont(font); // �]�w�r��
                            g2.drawString("�R�]��", 80, 40); // ø�s�¤�r
                            font = new Font("�ө���", Font.BOLD | Font.ITALIC, 18);
                            g2.setFont(font); // �]�w�r��
                            g2.drawString("����", 130, 70); // ø�s�¤�r
                            font = new Font("�ө���", Font.PLAIN, 14);
                            g2.setFont(font); // �]�w�r��
                            g2.drawString("�ɫe������A", 40, 100); // ø�s�¤�r
                            g2.drawString("�ìO�a�W���C", 120, 100); // ø�s�¤�r
                            g2.drawString("�|�Y�����A", 40, 120); // ø�s�¤�r
                            g2.drawString("�C�Y��G�m�C", 120, 120); // ø�s�¤�r
                            return Printable.PAGE_EXISTS;// �Ǧ^�ӭȪ�ܦs�b�C�L��
                        }
                    });
                    job.setJobName("�C�L��֡m�R�]��n"); // �]�w�C�L�u�@���W��
                    job.print(); // �I�sprint()��k�}�l�C�L
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("��    �L");
        button.setBounds(28, 56, 86, 28);
        getContentPane().add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h    �X");
        button_1.setBounds(151, 56, 86, 28);
        getContentPane().add(button_1);
        
        //
    }
    
}
