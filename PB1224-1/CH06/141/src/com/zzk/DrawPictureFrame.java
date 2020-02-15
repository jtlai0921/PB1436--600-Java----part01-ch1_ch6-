package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class DrawPictureFrame extends JFrame {
    // 建立圖形對像
    BufferedImage image = new BufferedImage(570, 390,
            BufferedImage.TYPE_INT_BGR);
    Graphics gs = image.getGraphics(); // 獲得圖形的繪圖上下文對像
    Graphics2D g = (Graphics2D) gs; // 將繪圖上下文對像轉為Graphics2D型態
    DrawPictureCanvas canvas = new DrawPictureCanvas(); // 建立畫布對像
    Color foreColor = Color.BLACK; // 定義前景色
    Color backgroundColor = Color.WHITE; // 定義背景色
    boolean rubber = false; // 橡皮標誌變數
    int x = -1; // 上一次鼠標繪製點的橫座標
    int y = -1; // 上一次鼠標繪製點的縱座標
    private JMenuItem newItemMenuItem_6 = null;
    
    public DrawPictureFrame() {
        super();
        setResizable(false);
        setTitle("畫圖程式");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 80, 574, 397);
        g.setColor(backgroundColor); // 用背景色設定繪圖上下文對象的顏色
        g.fillRect(0, 0, 570, 390); // 用背景色填充整個畫布
        g.setColor(foreColor); // 用前景色設定繪圖上下文對象的顏色
        canvas.setImage(image); // 設定畫布的圖形
        getContentPane().add(canvas); // 將畫布增加到窗體容器預設佈局的中部位置
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                if (rubber) { // 橡皮標誌為true，表示使用橡皮
                    if (x > 0 && y > 0) {
                        g.setColor(backgroundColor); // 用背景色設定繪圖上下文對象的顏色
                        g.fillRect(x, y, 10, 10); // 擦除鼠標經過位置的圖形
                    }
                    x = e.getX(); // 獲得鼠標在畫布上的橫座標
                    y = e.getY(); // 獲得鼠標在畫布上的縱座標
                } else { // 橡皮標誌為false，表示畫圖
                    if (x > 0 && y > 0) {
                        // 在鼠標經過處畫直線
                        g.drawLine(x, y, e.getX(), e.getY());
                    }
                    x = e.getX(); // 上一次鼠標繪製點的橫座標
                    y = e.getY(); // 上一次鼠標繪製點的縱座標
                }
                canvas.repaint(); // 更新畫布
            }
            
            public void mouseMoved(final MouseEvent arg0) {
                if (rubber) {
                    // 設定鼠標指標的形狀
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    // 設定鼠標指標的形狀
                    setCursor(Cursor
                            .getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }
        });
        canvas.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent arg0) {
                x = -1; // 上一次鼠標繪製點的橫座標
                y = -1; // 上一次鼠標繪製點的縱座標
            }
        });
        final JToolBar toolBar = new JToolBar();
        toolBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent arg0) {
                // 設定鼠標指標的形狀
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        getContentPane().add(toolBar, BorderLayout.NORTH);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // 宣告畫筆的屬性，粗細為1像素
                BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 設定繪圖上下文對象的畫筆
            }
        });
        button.setText("  細  線  ");
        toolBar.add(button);
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // 宣告畫筆的屬性，粗細為2像素
                BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 設定繪圖上下文對象的畫筆
            }
        });
        button_1.setText("  粗  線  ");
        toolBar.add(button_1);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // 宣告畫筆的屬性，粗細為4像素
                BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 設定繪圖上下文對象的畫筆
            }
        });
        button_2.setText("  較  粗  ");
        toolBar.add(button_2);
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // 開啟選擇顏色交談視窗
                Color bgColor = JColorChooser.showDialog(null, "選擇顏色交談視窗",
                        Color.CYAN);
                if (bgColor != null) {
                    backgroundColor = bgColor;
                }
                g.setColor(backgroundColor); // 設定繪圖上下文對象的背景色
                g.fillRect(0, 0, 570, 390); // 用背景色填充整個畫布
                g.setColor(foreColor); // 設定繪圖上下文對象的前景色
                canvas.repaint(); // 更新畫布
            }
        });
        button_3.setText("背景顏色");
        toolBar.add(button_3);
        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // 開啟選擇顏色交談視窗
                Color fColor = JColorChooser.showDialog(null, "選擇顏色交談視窗",
                        Color.CYAN);
                if (fColor != null) {
                    foreColor = fColor;
                }
                g.setColor(foreColor); // 設定繪圖上下文對象的前景色
            }
        });
        button_4.setText("前景顏色");
        toolBar.add(button_4);
        final JButton button_5 = new JButton();
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                g.setColor(backgroundColor); // 設定繪圖上下文對象的背景色
                g.fillRect(0, 0, 570, 390); // 用背景色填充整個畫布
                g.setColor(foreColor); // 設定繪圖上下文對象的前景色
                canvas.repaint(); // 更新畫布
            }
        });
        button_5.setText("  清  除  ");
        toolBar.add(button_5);
        
        final JButton button_6 = new JButton();
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (button_6.getText().equals("  橡  皮  ")) { // 單擊工具欄上的橡皮按鈕，使用橡皮
                    rubber = true; // 設定橡皮標誌為true
                    newItemMenuItem_6.setText("畫  圖"); // 改變選單上顯示的純文字為畫圖
                    button_6.setText("  畫  圖  "); // 改變按鈕上顯示的純文字為畫圖
                } else { // 單擊工具欄上的畫圖按鈕，使用畫筆
                    rubber = false; // 設定橡皮標誌為false
                    newItemMenuItem_6.setText("橡  皮"); // 改變選單上顯示的純文字為橡皮
                    button_6.setText("  橡  皮  "); // 改變按鈕上顯示的純文字為橡皮
                    g.setColor(foreColor); // 設定繪圖上下文對象的前景色
                }
            }
        });
        button_6.setText("  橡  皮  ");
        toolBar.add(button_6);
        
        final JButton button_7 = new JButton();
        button_7.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                System.exit(0); // 退出應用程式
            }
        });
        button_7.setText("  退  出  ");
        toolBar.add(button_7);
        
        final JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        final JMenu menu = new JMenu();
        menu.setText("線  型");
        menuBar.add(menu);
        
        final JMenuItem newItemMenuItem = new JMenuItem();
        newItemMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 宣告畫筆的屬性，粗細為1像素
                BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 設定繪圖上下文對象的畫筆
            }
        });
        newItemMenuItem.setText("細  線");
        menu.add(newItemMenuItem);
        
        final JMenuItem newItemMenuItem_1 = new JMenuItem();
        newItemMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 宣告畫筆的屬性，粗細為2像素
                BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 設定繪圖上下文對象的畫筆
            }
        });
        newItemMenuItem_1.setText("粗  線");
        menu.add(newItemMenuItem_1);
        
        final JMenuItem newItemMenuItem_2 = new JMenuItem();
        newItemMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 宣告畫筆的屬性，粗細為4像素
                BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // 設定繪圖上下文對象的畫筆
            }
        });
        newItemMenuItem_2.setText("較  粗");
        menu.add(newItemMenuItem_2);
        
        final JMenu menu_1 = new JMenu();
        menu_1.setText("顏  色");
        menuBar.add(menu_1);
        
        final JMenuItem newItemMenuItem_3 = new JMenuItem();
        newItemMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 開啟選擇顏色交談視窗
                Color fColor = JColorChooser.showDialog(null, "選擇顏色交談視窗",
                        Color.CYAN);
                if (fColor != null) {
                    foreColor = fColor;
                }
                g.setColor(foreColor); // 設定繪圖上下文對象的前景色
            }
        });
        newItemMenuItem_3.setText("前景顏色");
        menu_1.add(newItemMenuItem_3);
        
        final JMenuItem newItemMenuItem_4 = new JMenuItem();
        newItemMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 開啟選擇顏色交談視窗
                Color bgColor = JColorChooser.showDialog(null, "選擇顏色交談視窗",
                        Color.CYAN);
                if (bgColor != null) {
                    backgroundColor = bgColor;
                }
                g.setColor(backgroundColor); // 設定繪圖上下文對象的背景色
                g.fillRect(0, 0, 570, 390); // 用背景色填充整個畫布
                g.setColor(foreColor); // 設定繪圖上下文對象的前景色
                canvas.repaint(); // 更新畫布
            }
        });
        newItemMenuItem_4.setText("背景顏色");
        menu_1.add(newItemMenuItem_4);
        
        final JMenu menu_2 = new JMenu();
        menu_2.setText("編  輯");
        menuBar.add(menu_2);
        
        final JMenuItem newItemMenuItem_5 = new JMenuItem();
        newItemMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                g.setColor(backgroundColor); // 設定繪圖上下文對象的背景色
                g.fillRect(0, 0, 570, 390); // 用背景色填充整個畫布
                g.setColor(foreColor); // 設定繪圖上下文對象的前景色
                canvas.repaint(); // 更新畫布
            }
        });
        newItemMenuItem_5.setText("清  除");
        menu_2.add(newItemMenuItem_5);
        
        newItemMenuItem_6 = new JMenuItem();
        newItemMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (newItemMenuItem_6.getText().equals("橡  皮")) { // 單擊橡皮選單，使用橡皮
                    rubber = true; // 設定橡皮標誌為true
                    newItemMenuItem_6.setText("畫  圖"); // 改變選單上顯示的純文字為畫圖
                    button_6.setText("  畫  圖  "); // 改變按鈕上顯示的純文字為畫圖
                } else { // 單擊工具欄上的畫圖按鈕，使用畫筆
                    rubber = false; // 設定橡皮標誌為false
                    newItemMenuItem_6.setText("橡  皮"); // 改變選單上顯示的純文字為橡皮
                    button_6.setText("  橡  皮  "); // 改變按鈕上顯示的純文字為橡皮
                    g.setColor(foreColor); // 設定繪圖上下文對象的前景色
                }
            }
        });
        newItemMenuItem_6.setText("橡  皮");
        menu_2.add(newItemMenuItem_6);
        
        final JMenu menu_3 = new JMenu();
        menu_3.setText("系統");
        menuBar.add(menu_3);
        
        final JMenuItem newItemMenuItem_8 = new JMenuItem();
        newItemMenuItem_8.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        newItemMenuItem_8.setText("退  出");
        menu_3.add(newItemMenuItem_8);
    }
    
    public static void main(String[] args) {
        DrawPictureFrame frame = new DrawPictureFrame();
        frame.setVisible(true);
    }
}
