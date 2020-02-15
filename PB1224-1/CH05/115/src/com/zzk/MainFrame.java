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
    private String border = ""; // 邊框型態
    private PageFormat pf; // 描述頁面大小和方向的對象
    private PreviewCanvas canvas; // 列印預覽畫布
    private File imgFile = null; // 相片檔案
    private Image src;// 相片圖片
    private boolean isPreview = false; // 是否可以列印
    
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
        setTitle("相簿特效列印程式");
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
        label.setText("請選擇要列印的相片：");
        panel_1.add(label);
        
        filePath = new JTextField();
        filePath.setBounds(186, 12, 502, 24);
        filePath.setPreferredSize(new Dimension(300, 24));
        panel_1.add(filePath);
        // 選擇檔案按鈕
        final JButton selectButton = new JButton();
        selectButton.setBounds(694, 10, 86, 28);
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter(
                        "圖形檔案（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");
                fileChooser.setFileFilter(filter);
                int i = fileChooser.showOpenDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // 獲得勾選的圖片對像
                    filePath.setText(imgFile.getAbsolutePath()); // 顯示圖片路徑
                }
            }
        });
        selectButton.setText("選擇檔案");
        panel_1.add(selectButton);
        
        final ImgLabel imgLabel1 = new ImgLabel();
        imgLabel1.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠標左鍵
                    border = "border1"; // 設定邊框型態
                    if (imgFile != null) {
                        isPreview = true; // 表示可以列印
                        canvas.repaint(); // 重繪畫布
                    } else {
                        JOptionPane.showMessageDialog(null, "請先選擇要列印的圖片！");
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
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠標左鍵
                    border = "border2"; // 設定邊框型態
                    if (imgFile != null) {
                        isPreview = true; // 表示可以列印
                        canvas.repaint(); // 重繪畫布
                    } else {
                        JOptionPane.showMessageDialog(null, "請先選擇要列印的圖片！");
                    }
                }
            }
        });
        imgLabel2.setIcon(SwingResourceManager.getIcon(MainFrame.class,
                "/com/zzk/border2.png"));
        imgLabel2.setBounds(289, 42, 86, 35);
        panel_1.add(imgLabel2);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("選擇邊框觀看預覽效果：");
        label_1.setBounds(37, 50, 143, 18);
        panel_1.add(label_1);
        
        final JButton pageSetButton = new JButton();
        pageSetButton.setBounds(602, 43, 86, 28);
        panel_1.add(pageSetButton);
        pageSetButton.addActionListener(new ActionListener() {
            //
            /*
             * 「頁面設定」按鈕的單擊事件
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // 獲得PrinterJob類別的實例
                pf = job.pageDialog(pf); // 開啟頁面設定交談視窗
                isPreview = true; // 表示可以列印
                canvas.repaint(); // 重繪畫面
            }
        });
        pageSetButton.setText("頁面設定");
        
        final JButton printButton = new JButton();
        printButton.setBounds(694, 43, 86, 28);
        panel_1.add(printButton);
        printButton.addActionListener(new ActionListener() {
            /*
             * 「開始列印」按鈕的單擊事件
             * @see
             * java.awt.event.ActionListener#actionPerformed(java.awt.event.
             * ActionEvent)
             */
            public void actionPerformed(final ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob(); // 獲得PrinterJob對象的實例
                if (!job.printDialog())
                    return;
                // 設定列印內容
                job.setPrintable(new Printable() {
                    
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // 設定可以列印
                        if (pageIndex < 1) {
                            printPicture(graphics, pageFormat, pageIndex); // 繪製列印內容
                            return Printable.PAGE_EXISTS; // 傳回PAGE_EXISTS
                        } else {
                            return Printable.NO_SUCH_PAGE; // 傳回NO_SUCH_PAGE
                        }
                    }
                    
                });
                job.setJobName("列印相片"); // 設定列印文件的名稱
                try {
                    job.print(); // 開始列印
                } catch (PrinterException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        
        printButton.setText("開始列印");
        
        final ImgLabel imgLabel3 = new ImgLabel();
        imgLabel3.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠標左鍵
                    border = "border3"; // 設定邊框型態
                    if (imgFile != null) {
                        isPreview = true; // 表示可以列印
                        canvas.repaint(); // 重繪畫布
                    } else {
                        JOptionPane.showMessageDialog(null, "請先選擇要列印的圖片！");
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
                if (e.getModifiers() == InputEvent.BUTTON1_MASK) { // 按下鼠標左鍵
                    border = "border4"; // 設定邊框型態
                    if (imgFile != null) {
                        isPreview = true; // 表示可以列印
                        canvas.repaint(); // 重繪畫布
                    } else {
                        JOptionPane.showMessageDialog(null, "請先選擇要列印的圖片！");
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
        printPicture(graphics, pageFormat, pageIndex); // 繪製列印內容
        return Printable.PAGE_EXISTS; // 傳回PAGE_EXISTS
    }
    
    // 畫布
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g; // 獲得Graphics2D對像
                g2.translate(10, 10); // 將原點座標平移10個像素
                int x = (int) (pf.getImageableX() - 1); // 獲得可列印區域起始點的x軸座標
                int y = (int) (pf.getImageableY() - 1); // 獲得可列印區域起始點的y軸座標
                int width = (int) (pf.getImageableWidth() + 1); // 獲得可列印區域結束點的x軸座標
                int height = (int) (pf.getImageableHeight() + 1); // 獲得可列印區域結束點的y軸座標
                int mw = (int) pf.getWidth(); // 獲得頁面寬度
                int mh = (int) pf.getHeight();// 獲得頁面高度
                g2.drawRect(0, 0, mw, mh);
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f)); // 設定線條的樣式
                g2.drawRect(x, y, width, height); // 繪製矩形邊框
                g2.setColor(Color.WHITE); // 設定前景色
                g2.fillRect(x + 1, y + 1, width - 1, height - 1); // 繪製白色背景的矩形
                MainFrame.this.print(g, pf, 0);
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 繪製列印內容
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX(); // 獲得可列印區域座標的X位置
        int y = (int) pageFormat.getImageableY(); // 獲得可列印區域座標的Y位置
        Graphics2D g2 = (Graphics2D) graphics;
        if (imgFile != null && isPreview) {
            try {
                src = ImageIO.read(imgFile);// 建構Image對像
            } catch (IOException e) {
                e.printStackTrace();
            } 
            double imgWidth = src.getWidth(this); // 獲得圖片的寬
            double imgHeight = src.getHeight(this); // 獲得圖片的高
            double percent = imgWidth / imgHeight; // 長寬比例
            int mw = (int) pf.getWidth() - x * 2; // 計算可列印區域的寬
            int mh = (int) pf.getHeight() - y * 2; // 計算可列印區域的高
            if (imgWidth > mw) { // 如果寬大於可列印區域
                imgWidth = mw;
                imgHeight = mw * percent;
            }
            if (imgHeight > mh) { // 如果高大於可列印區域
                imgHeight = mh;
                imgWidth = mh * percent;
            }
            g2.drawImage(src, x, y, (int) imgWidth, (int) imgHeight, this); // 繪製正常圖形，如圖片、照片等
            /********************* 繪製邊框 *************************/
            if (!border.equals("")) {
                ImageIcon icon = SwingResourceManager.getIcon(MainFrame.class,
                        "/com/zzk/" + border + ".png"); // 獲得ImageIcon對像
                Image borderImg = icon.getImage(); // 獲得用於繪製邊框的Image對像
                g2.drawImage(borderImg, x, y, (int) imgWidth, (int) imgHeight,
                        this); // 繪製邊框
            }
            /*****************************************************/
        }
        isPreview = false; // 設定不可以列印
    }
}
