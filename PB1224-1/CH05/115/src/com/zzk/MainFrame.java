package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.swtdesigner.SwingResourceManager;

public class MainFrame extends JFrame implements Printable {
    
    private JTextField filePath;
    private String border = ""; // ��ث��A
    private PageFormat pf; // �y�z�����j�p�M��V����H
    private PreviewCanvas canvas; // �C�L�w���e��
    private File imgFile = null; // �ۤ��ɮ�
    private Image src;// �ۤ��Ϥ�
    private boolean isPreview = false; // �O�_�i�H�C�L
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        setTitle("��ï�S�ĦC�L�{��");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        pf.setOrientation(PageFormat.LANDSCAPE);
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(840, 780));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(0, 80));
        panel_1.setLayout(null);
        getContentPane().add(panel_1, BorderLayout.NORTH);
        
        final JLabel label = new JLabel();
        label.setBounds(50, 15, 130, 18);
        label.setText("�п�ܭn�C�L���ۤ��G");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setBounds(186, 12, 502, 24);
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // ����ɮ׫��s
        final JButton selectButton = new JButton();
        selectButton.setBounds(694, 10, 86, 28);
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
        
        final ImgLabel imgLabel1 = new ImgLabel();
        imgLabel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ���U���Х���
                    border = "border1"; // �]�w��ث��A
                    if (imgFile != null) {
                        isPreview = true; // ��ܥi�H�C�L
                        canvas.repaint(); // ��ø�e��
                    } else {
                        JOptionPane.showMessageDialog(null, "�Х���ܭn�C�L���Ϥ��I");
                    }
                }
            }
        });
        imgLabel1.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border1.png"));
        imgLabel1.setBounds(186, 42, 86, 35);
        panel_1.add(imgLabel1);
        
        final ImgLabel imgLabel2 = new ImgLabel();
        imgLabel2.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ���U���Х���
                    border = "border2"; // �]�w��ث��A
                    if (imgFile != null) {
                        isPreview = true; // ��ܥi�H�C�L
                        canvas.repaint(); // ��ø�e��
                    } else {
                        JOptionPane.showMessageDialog(null, "�Х���ܭn�C�L���Ϥ��I");
                    }
                }
            }
        });
        imgLabel2.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border2.png"));
        imgLabel2.setBounds(289, 42, 86, 35);
        panel_1.add(imgLabel2);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("�������[�ݹw���ĪG�G");
        label_1.setBounds(37, 50, 143, 18);
        panel_1.add(label_1);
        
        final JButton pageSetButton = new JButton();
        pageSetButton.setBounds(602, 43, 86, 28);
        panel_1.add(pageSetButton);
        pageSetButton.addActionListener(new ActionListener() {
            //
            /*
             * �u�����]�w�v���s�������ƥ�
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // ��oPrinterJob���O�����
                pf = job.pageDialog(pf); // �}�ҭ����]�w��͵���
                isPreview = true; // ��ܥi�H�C�L
                canvas.repaint(); // ��ø�e��
            }
        });
        pageSetButton.setText("�����]�w");
        
        final JButton printButton = new JButton();
        printButton.setBounds(694, 43, 86, 28);
        panel_1.add(printButton);
        printButton.addActionListener(new ActionListener() {
            /*
             * �u�}�l�C�L�v���s�������ƥ�
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
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
                            return Printable.PAGE_EXISTS; // �Ǧ^PAGE_EXISTS
                        } else {
                            return Printable.NO_SUCH_PAGE; // �Ǧ^NO_SUCH_PAGE
                        }
                    }
                    
                });
                job.setJobName("�C�L�ۤ�"); // �]�w�C�L��󪺦W��
                try {
                    job.print(); // �}�l�C�L
                } catch (PrinterException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("�}�l�C�L");
        
        final ImgLabel imgLabel3 = new ImgLabel();
        imgLabel3.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ���U���Х���
                    border = "border3"; // �]�w��ث��A
                    if (imgFile != null) {
                        isPreview = true; // ��ܥi�H�C�L
                        canvas.repaint(); // ��ø�e��
                    } else {
                        JOptionPane.showMessageDialog(null, "�Х���ܭn�C�L���Ϥ��I");
                    }
                }
            }
        });
        imgLabel3.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border3.png"));
        imgLabel3.setBounds(393, 42, 86, 35);
        panel_1.add(imgLabel3);
        
        final ImgLabel imgLabel4 = new ImgLabel();
        imgLabel4.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // ���U���Х���
                    border = "border4"; // �]�w��ث��A
                    if (imgFile != null) {
                        isPreview = true; // ��ܥi�H�C�L
                        canvas.repaint(); // ��ø�e��
                    } else {
                        JOptionPane.showMessageDialog(null, "�Х���ܭn�C�L���Ϥ��I");
                    }
                }
            }
        });
        imgLabel4.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border4.PNG"));
        imgLabel4.setText("New ImgLabel");
        imgLabel4.setBounds(497, 42, 86, 35);
        panel_1.add(imgLabel4);
        
        //
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        // TODO Auto-generated method stub
        printPicture(graphics, pageFormat, pageIndex); // ø�s�C�L���e
        return Printable.PAGE_EXISTS; // �Ǧ^PAGE_EXISTS
    }
    
    // �e��
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g; // ��oGraphics2D�ﹳ
                g2.translate(10, 10); // �N���I�y�Х���10�ӹ���
                int x = (int) (pf.getImageableX() - 1); // ��o�i�C�L�ϰ�_�l�I��x�b�y��
                int y = (int) (pf.getImageableY() - 1); // ��o�i�C�L�ϰ�_�l�I��y�b�y��
                int width = (int) (pf.getImageableWidth() + 1); // ��o�i�C�L�ϰ쵲���I��x�b�y��
                int height = (int) (pf.getImageableHeight() + 1); // ��o�i�C�L�ϰ쵲���I��y�b�y��
                int mw = (int) pf.getWidth(); // ��o�����e��
                int mh = (int) pf.getHeight();// ��o��������
                g2.drawRect(0, 0, mw, mh);
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f)); // �]�w�u�����˦�
                g2.drawRect(x, y, width, height); // ø�s�x�����
                g2.setColor(Color.WHITE); // �]�w�e����
                g2.fillRect(x + 1, y + 1, width - 1, height - 1); // ø�s�զ�I�����x��
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
            double imgWidth = src.getWidth(this); // ��o�Ϥ����e
            double imgHeight = src.getHeight(this); // ��o�Ϥ�����
            double percent = imgWidth / imgHeight; // ���e���
            int mw = (int) pf.getWidth() - x * 2; // �p��i�C�L�ϰ쪺�e
            int mh = (int) pf.getHeight() - y * 2; // �p��i�C�L�ϰ쪺��
            if (imgWidth > mw) { // �p�G�e�j��i�C�L�ϰ�
                imgWidth = mw;
                imgHeight = mw * percent;
            }
            if (imgHeight > mh) { // �p�G���j��i�C�L�ϰ�
                imgHeight = mh;
                imgWidth = mh * percent;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // ø�s���`�ϧΡA�p�Ϥ��B�Ӥ���
            /********************* ø�s��� *************************/
            if (!border.equals("")) {
                ImageIcon icon = SwingResourceManager.getIcon(MainFrame.class,
                        "/com/zzk/" + border + ".png"); // ��oImageIcon�ﹳ
                Image borderImg = icon.getImage(); // ��o�Ω�ø�s��ت�Image�ﹳ
                g2.drawImage(borderImg, x, y, (int) imgWidth, (int) imgHeight,
                        this); // ø�s���
            }
            /*****************************************************/
        }
        isPreview = false; // �]�w���i�H�C�L
    }
}
