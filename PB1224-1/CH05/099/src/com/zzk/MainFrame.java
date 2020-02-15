package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private PageFormat pf;
    private PreviewCanvas canvas;
    private File imgFile = null;
    private Image src;
    private boolean isPreview = false; // �O�_�i�H�C�L
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// ����j�p���ܮɰ���
                // �C�L�w��
                if (imgFile != null) {
                    isPreview = true; // ��ܥi�H�C�L
                    canvas.repaint();// �I�spaint()��k
                }
            }
        });
        setTitle("�˧ǦC�L");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 750));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �C�L�w��
                if (imgFile != null) {
                    isPreview = true; // ��ܥi�H�C�L
                    PrinterJob job = PrinterJob.getPrinterJob();// ��o�C�L�ﹳ
                    pf = job.pageDialog(pf);// ��ܭק�PageFormat��Ҫ���͵���
                    canvas.repaint();// �I�spaint()��k
                } else {
                    JOptionPane.showMessageDialog(null, "�Х���ܭn�C�L���Ϥ��I");
                }
            }
        });
        previewButton.setText("�C�L�w��");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // ��oPrinterJob��H�����
                if (!job.printDialog())// �}�ҦC�L��͵���
                    return;// �����C�L��͵������������s�������C�L��͵��������{��������
                // �]�w�C�L���e
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // �]�w�i�H�C�L
                        if (pageIndex < 1) {
                            printPicture(graphics, pageFormat, pageIndex); // ø�s�C�L���e
                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                    
                });
                job.setJobName("�C�L�ϧ�");
                try {
                    job.print();// �I�sprint()��k�A��{�C�L
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("�}�l�C�L");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setText("�п�ܭn�C�L���Ϥ��G");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // ����ɮ׫��s
        final JButton selectButton = new JButton();
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �إ��ɮ׿�ܾ�
                FileFilter filter = new FileNameExtensionFilter(
                        "�ϧ��ɮס]JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// �إ߹L�o��
                fileChooser.setFileFilter(filter);// �]�w�L�o��
                int i = fileChooser.showOpenDialog(getContentPane());// ��ܶ}�ҥ�͵���
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��o�Ŀ諸�Ϥ��ﹳ
                    filePath.setText(imgFile.getAbsolutePath()); // ��ܹϤ����|
                }
            }
        });
        selectButton.setText("����ɮ�");
        panel_1.add(selectButton);
        
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex); // ø�s�C�L���e
        return Printable.PAGE_EXISTS; // �Ǧ^PAGE_EXISTS
    }
    
    // �e��
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.translate(10, 10);// ����ø�ϤW�U��
                int x = (int) (pf.getImageableX() - 1);// ��o�i�C�L�ϰ쪺x�y�а����A�Ω�ø�s��u
                int y = (int) (pf.getImageableY() - 1);// ��o�i�C�L�ϰ쪺y�y�а��W�A�Ω�ø�s��u
                int width = (int) (pf.getImageableWidth() + 1);// ��o�i�C�L�ϰ쪺�e�װ��k�A�Ω�ø�s��u
                int height = (int) (pf.getImageableHeight() + 1);// ��o�i�C�L�ϰ쪺���װ��U�A�Ω�ø�s��u
                int mw = (int) pf.getWidth();// ��o�C�L�����e��
                int mh = (int) pf.getHeight();// ��o�C�L��������
                g2.drawRect(0, 0, mw, mh);// ø�s��u�~��
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// ���w��u�Ҧ�
                g2.drawRect(x, y, width, height);// ø�s��u����
                MainFrame.this.print(g2, pf, 0);// �I�sprint()��k
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * ø�s�˧Ǫ��C�L���e
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX(); // ��o�i�C�L�ϰ�y�Ъ�X��m
        int y = (int) pageFormat.getImageableY(); // ��o�i�C�L�ϰ�y�Ъ�Y��m
        Graphics2D g2 = (Graphics2D) graphics;
        if (imgFile != null && isPreview) {
            try {
                src = ImageIO.read(imgFile);// �غcBufferedImage�ﹳ
            } catch (IOException e) {
                e.printStackTrace();
            }
            double imgWidth = src.getWidth(this);// ��o�ϧΪ��e��
            double imgHeight = src.getHeight(this);// ��o�ϧΪ�����
            double imgWidthS = imgWidth;// �x�s�ϧΪ��e��
            double imgHeightS = imgHeight;// �x�s�ϧΪ�����
            int mw = (int) pf.getWidth();// ��o�C�L�����e��
            int mh = (int) pf.getHeight();// ��o�C�L��������
            if (imgWidth > mw) { // �p�G�e�j��i�C�L�ϰ�
                imgWidth = mw - x;// �]�w�s�e�׭�
            }
            if (imgHeight > mh) { // �p�G���j��i�C�L�ϰ�
                imgHeight = mh - y;// �]�w�s�����׭�
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, x,
                    (int) imgHeightS, (int) imgWidthS, y, this);// ø�s�˧ǹϧ�
        }
        isPreview = false; // �]�w���i�H�C�L
    }
}
