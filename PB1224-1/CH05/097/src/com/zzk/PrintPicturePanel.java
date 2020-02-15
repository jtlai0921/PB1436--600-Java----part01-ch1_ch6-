package com.zzk;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author �i���[
 *
 */
@SuppressWarnings("serial")
public class PrintPicturePanel extends JPanel implements Printable {
    private JButton printButton;
    private JButton previewButton;
    private JPanel controlPanel;
    private File imgFile = null;
    private BufferedImage src;
    private PrinterJob job;
    private PageFormat pf;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("�C�L�Ϥ�");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 280);
        PrintPicturePanel panel = new PrintPicturePanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
    
    public PrintPicturePanel() {
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        add(getControlPanel(), BorderLayout.SOUTH);
        pf = new PageFormat();// �إ�PageFormat�ﹳ
        pf.setOrientation(PageFormat.LANDSCAPE);// �]�w�C�L��V
        job = PrinterJob.getPrinterJob();// ��o�C�L�ﹳ
    }
    
    private void drawPage(Graphics2D g2) {
        int imgW = 0;
        int imgH = 0;
        if (src != null) {
            imgW = src.getWidth();
            imgH = src.getHeight();
            if (imgW > getWidth()) {
                imgW = getWidth();
            }
            if (imgH > getHeight()) {
                imgH = getHeight();
            }
        }
        g2.drawImage(src, 0, 0, imgW, imgH, this);// ø�s�ϧ�
    }
    
    protected JButton getPreviewButton() {
        if (previewButton == null) {
            previewButton = new JButton();
            previewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();// �إ��ɮ׿�ܾ�
                    FileFilter filter = new FileNameExtensionFilter(
                            "�ϧ��ɮס]JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// �إ߹L�o��
                    fileChooser.setFileFilter(filter);// �]�w�L�o��
                    int i = fileChooser.showOpenDialog(null);// ��ܶ}�ҥ�͵���
                    if (i == JFileChooser.APPROVE_OPTION) {
                        imgFile = fileChooser.getSelectedFile(); // ��o�Ŀ�Ϥ���File�ﹳ
                    }
                    if (imgFile != null) {
                        try {
                            src = ImageIO.read(imgFile);// �غcBufferedImage�ﹳ
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    PrintPicturePanel.this.repaint();// �I�spaintComponent()��k
                }
            });
            previewButton.setText("��ܹϤ�");
        }
        return previewButton;
    }
    
    protected JButton getPrintButton() {
        if (printButton == null) {
            printButton = new JButton();
            printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        job.setPrintable(PrintPicturePanel.this);// ���C�L�ﹳ���wPrintable�ﹳ
                        job.setJobName("�C�L�Ϥ�");// �]�w�C�L�u�@���W��
                        job.print();// ����C�L
                    } catch (PrinterException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            printButton.setText("�C�L");
        }
        return printButton;
    }
    
    protected JPanel getControlPanel() {
        if (controlPanel == null) {
            controlPanel = new JPanel();
            controlPanel.setBorder(new LineBorder(Color.BLUE, 1, false));
            final FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(20);
            controlPanel.setLayout(flowLayout);
            controlPanel.add(getPreviewButton());
            controlPanel.add(getPrintButton());
        }
        return controlPanel;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawPage(g2);
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex > 0)
            return Printable.NO_SUCH_PAGE;// �S���C�L��
        int x = (int) pageFormat.getImageableX();//��o�i�C�L�ϰ쪺x�y��
        int y = (int) pageFormat.getImageableY();//��o�i�C�L�ϰ쪺y�y��
        Graphics2D g2 = (Graphics2D) graphics;
        int ableW = (int) pageFormat.getImageableWidth();//��o�i�C�L�ϰ쪺�e��
        int ableH = (int) pageFormat.getImageableHeight();//��o�i�C�L�ϰ쪺����
        int imgW = 0;// �w�q�C�L�Ϥ����e��
        int imgH = 0;// �w�q�C�L�Ϥ�������
        if (src != null) {
            imgW = src.getWidth();// ��o�Ϥ����e��
            imgH = src.getHeight();// ��o�Ϥ�������
            if (imgW > ableW) {// �Ϥ��e�פj��C�L�ϰ쪺�e��
                imgW = ableW;// �Ϥ��e�׬��C�L�ϰ쪺�e��
            }
            if (imgH > ableH) {// �Ϥ����פj��C�L�ϰ쪺����
                imgH = ableH;// �Ϥ����׬��C�L�ϰ쪺����
            }
        }
        g2.drawImage(src, x, y, imgW, imgH, this);// ø�s�C�L���e
        return Printable.PAGE_EXISTS;// �Ǧ^�s�b�C�L���e����T
    }
}
