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
 * @author �i���[
 */
@SuppressWarnings("serial")
public class PrintShapeFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��o�C�L�ﹳ
    
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
        setTitle("�C�L�ϧ�");
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
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D)graphics; // ��oGraphics2D�ﹳ
                            BasicStroke stroke = new BasicStroke(3); // �إ߼e�׬O3�����e�ﹳ
                            g2.setStroke(stroke);// �]�w���e�ﹳ
                            Color color = new Color(0,162,232);// �إ��C��ﹳ
                            g2.setColor(color);// �]�w�C��
                            g2.drawOval(30, 40, 60, 60);     // ø�s�Ĥ@�Ӷ�
                            color = new Color(233,123,16);   // �إ߷s���C��ﹳ
                            g2.setColor(color);// �]�w�C��
                            g2.drawOval(60, 70, 60, 60);     // ø�s�ĤG�Ӷ�
                            color = new Color(28,20,100);// �إ߷s���C��ﹳ
                            g2.setColor(color);// �]�w�C��
                            g2.drawOval(92, 40, 60, 60);     // ø�s�ĤT�Ӷ�
                            color = new Color(0,255,0);// �إ߷s���C��ﹳ
                            g2.setColor(color);// �]�w�C��
                            g2.drawOval(122, 70, 60, 60);    // ø�s�ĥ|�Ӷ�
                            color = new Color(255,0,0);// �إ߷s���C��ﹳ
                            g2.setColor(color);// �]�w�C��
                            g2.drawOval(154, 40, 60, 60);    // ø�s�Ĥ��Ӷ�
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("�C�L�����ϧ�"); // �]�w�C�L�u�@���W��
                    job.print(); // �I�sprint()��k�}�l�C�L
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("�C�L�ϧ�");
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