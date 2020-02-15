package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.sun.awt.AWTUtilities;

public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();// �إߵ���ﹳ
        AWTUtilities.setWindowOpacity(frame, 0.8f); // �]�w����80%�z��
        frame.setVisible(true);// ��ܵ���
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
        getContentPane().setBackground(new Color(70, 130, 180));
        setTitle("�z�����C�L�w����͵���");
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
        panel.setOpaque(false);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �C�L�w��
                if (imgFile != null) {
                    isPreview = true; // ��ܥi�H�C�L
                    PrinterJob job = PrinterJob.getPrinterJob();
                    pf = job.pageDialog(pf);
                    canvas.repaint();
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
                if (!job.printDialog())
                    return;
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
                    job.print();
                } catch (PrinterException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("�}�l�C�L");
        panel.add(printButton);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
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
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(
                        "�ϧ��ɮס]JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");
                fileChooser.setFileFilter(filter);
                int i = fileChooser.showOpenDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��o�Ŀ諸�Ϥ��ﹳ
                    filePath.setText(imgFile.getAbsolutePath()); // ��ܹϤ����|
                }
            }
        });
        selectButton.setText("����ɮ�");
        panel_1.add(selectButton);
        // setGlassPane(getContentPane());
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
                g2.translate(10, 10);
                int x = (int) (pf.getImageableX() - 1);
                int y = (int) (pf.getImageableY() - 1);
                int width = (int) (pf.getImageableWidth() + 1);
                int height = (int) (pf.getImageableHeight() + 1);
                int mw = (int) pf.getWidth();
                int mh = (int) pf.getHeight();
                g2.setColor(new Color(255, 253, 234)); // �]�w�e����
                g2.fillRect(1, 1, mw - 1, mh - 1);
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));
                g2.setColor(Color.BLACK); // �]�w�e����
                g2.drawRect(x, y, width, height);
                g2.setColor(Color.WHITE); // �]�w�e����
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);
                MainFrame.this.print(g, pf, 0);
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * ø�s�C�L���e
     * 
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
                src = ImageIO.read(imgFile);// �غcImage�ﹳ
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            double imgWidth = src.getWidth(this);
            double imgHeight = src.getHeight(this);
            double percent = imgWidth / imgHeight; // ���e���
            int mw = (int) pf.getWidth() - x * 2;
            int mh = (int) pf.getHeight() - y * 2;
            if (imgWidth > mw) { // �p�G�e�j��i�C�L�ϰ�
                imgWidth = mw;
                imgHeight = mw * percent;
            }
            if (imgHeight > mh) { // �p�G���j��i�C�L�ϰ�
                imgHeight = mh;
                imgWidth = mh * percent;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // ø�s���`�ϧ�
        }
        isPreview = false; // �]�w���i�H�C�L
    }
}
