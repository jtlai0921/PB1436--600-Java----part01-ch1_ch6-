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
        setTitle("�C�L�ୱ�Ϥ�");
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
                    Robot robot = new Robot();// �إ�Robot���O����ҹﹳ
                    Dimension size = getToolkit().getScreenSize();// ��o�̹��j�p
                    Rectangle screenRect = new Rectangle(0, 0, size.width,
                            size.height);// �إ̹߫��j�p���x�ιﹳ
                    setVisible(false);// ���õ���
                    Thread.sleep(1000);// ��v1����
                    BufferedImage image = robot.createScreenCapture(screenRect);// ��_�̹��ϧ�
                    setVisible(true);// ��ܵ���
                    printPanel.setImage(image);// ���C�L���O�]�w�ϧ�
                    printPanel.repaint();// ���sø�s�C�L���O
                } catch (AWTException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        getDesktopBtn.setText("��o�ୱ");
        panel.add(getDesktopBtn);
        
        final JButton pageSetBtn = new JButton();
        pageSetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (job != null) {// �p�G�C�L�ﹳ����NULL
                    printPanel.pageSet(job);// �I�s�C�L���O�������]�w��k
                }
            }
        });
        pageSetBtn.setText("�����]�w");
        panel.add(pageSetBtn);
        
        final JButton printBtn = new JButton();
        printBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                boolean print = job.printDialog();// ��ܦC�L��͵���
                if (print) {// �p�G�ϥΪ̽T�{�C�L
                    try {
                        job.setJobName("�C�L�ୱ�Ϥ�");// �]�w�C�L�u�@�W��
                        job.setPrintable(printPanel);// �]�w�C�L�������C�L���O
                        job.print();// �}�l�C�L�u�@
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        printBtn.setText("�C�L");
        panel.add(printBtn);
        //
    }
    
}
