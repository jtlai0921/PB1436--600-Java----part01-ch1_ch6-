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
    // �إ߹ϧιﹳ
    BufferedImage image = new BufferedImage(570, 390,
            BufferedImage.TYPE_INT_BGR);
    Graphics gs = image.getGraphics(); // ��o�ϧΪ�ø�ϤW�U��ﹳ
    Graphics2D g = (Graphics2D) gs; // �Nø�ϤW�U��ﹳ�ରGraphics2D���A
    DrawPictureCanvas canvas = new DrawPictureCanvas(); // �إߵe���ﹳ
    Color foreColor = Color.BLACK; // �w�q�e����
    Color backgroundColor = Color.WHITE; // �w�q�I����
    boolean rubber = false; // ��ּлx�ܼ�
    int x = -1; // �W�@������ø�s�I����y��
    int y = -1; // �W�@������ø�s�I���a�y��
    private JMenuItem newItemMenuItem_6 = null;
    
    public DrawPictureFrame() {
        super();
        setResizable(false);
        setTitle("�e�ϵ{��");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 80, 574, 397);
        g.setColor(backgroundColor); // �έI����]�wø�ϤW�U���H���C��
        g.fillRect(0, 0, 570, 390); // �έI�����R��ӵe��
        g.setColor(foreColor); // �Ϋe����]�wø�ϤW�U���H���C��
        canvas.setImage(image); // �]�w�e�����ϧ�
        getContentPane().add(canvas); // �N�e���W�[�쵡��e���w�]�G����������m
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                if (rubber) { // ��ּлx��true�A��ܨϥξ��
                    if (x > 0 && y > 0) {
                        g.setColor(backgroundColor); // �έI����]�wø�ϤW�U���H���C��
                        g.fillRect(x, y, 10, 10); // �������иg�L��m���ϧ�
                    }
                    x = e.getX(); // ��o���Цb�e���W����y��
                    y = e.getY(); // ��o���Цb�e���W���a�y��
                } else { // ��ּлx��false�A��ܵe��
                    if (x > 0 && y > 0) {
                        // �b���иg�L�B�e���u
                        g.drawLine(x, y, e.getX(), e.getY());
                    }
                    x = e.getX(); // �W�@������ø�s�I����y��
                    y = e.getY(); // �W�@������ø�s�I���a�y��
                }
                canvas.repaint(); // ��s�e��
            }
            
            public void mouseMoved(final MouseEvent arg0) {
                if (rubber) {
                    // �]�w���Ы��Ъ��Ϊ�
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    // �]�w���Ы��Ъ��Ϊ�
                    setCursor(Cursor
                            .getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }
        });
        canvas.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent arg0) {
                x = -1; // �W�@������ø�s�I����y��
                y = -1; // �W�@������ø�s�I���a�y��
            }
        });
        final JToolBar toolBar = new JToolBar();
        toolBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent arg0) {
                // �]�w���Ы��Ъ��Ϊ�
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        getContentPane().add(toolBar, BorderLayout.NORTH);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �ŧi�e�����ݩʡA�ʲӬ�1����
                BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // �]�wø�ϤW�U���H���e��
            }
        });
        button.setText("  ��  �u  ");
        toolBar.add(button);
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �ŧi�e�����ݩʡA�ʲӬ�2����
                BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // �]�wø�ϤW�U���H���e��
            }
        });
        button_1.setText("  ��  �u  ");
        toolBar.add(button_1);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �ŧi�e�����ݩʡA�ʲӬ�4����
                BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // �]�wø�ϤW�U���H���e��
            }
        });
        button_2.setText("  ��  ��  ");
        toolBar.add(button_2);
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �}�ҿ���C���͵���
                Color bgColor = JColorChooser.showDialog(null, "����C���͵���",
                        Color.CYAN);
                if (bgColor != null) {
                    backgroundColor = bgColor;
                }
                g.setColor(backgroundColor); // �]�wø�ϤW�U���H���I����
                g.fillRect(0, 0, 570, 390); // �έI�����R��ӵe��
                g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
                canvas.repaint(); // ��s�e��
            }
        });
        button_3.setText("�I���C��");
        toolBar.add(button_3);
        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �}�ҿ���C���͵���
                Color fColor = JColorChooser.showDialog(null, "����C���͵���",
                        Color.CYAN);
                if (fColor != null) {
                    foreColor = fColor;
                }
                g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
            }
        });
        button_4.setText("�e���C��");
        toolBar.add(button_4);
        final JButton button_5 = new JButton();
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                g.setColor(backgroundColor); // �]�wø�ϤW�U���H���I����
                g.fillRect(0, 0, 570, 390); // �έI�����R��ӵe��
                g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
                canvas.repaint(); // ��s�e��
            }
        });
        button_5.setText("  �M  ��  ");
        toolBar.add(button_5);
        
        final JButton button_6 = new JButton();
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (button_6.getText().equals("  ��  ��  ")) { // �����u����W����֫��s�A�ϥξ��
                    rubber = true; // �]�w��ּлx��true
                    newItemMenuItem_6.setText("�e  ��"); // ���ܿ��W��ܪ��¤�r���e��
                    button_6.setText("  �e  ��  "); // ���ܫ��s�W��ܪ��¤�r���e��
                } else { // �����u����W���e�ϫ��s�A�ϥεe��
                    rubber = false; // �]�w��ּлx��false
                    newItemMenuItem_6.setText("��  ��"); // ���ܿ��W��ܪ��¤�r�����
                    button_6.setText("  ��  ��  "); // ���ܫ��s�W��ܪ��¤�r�����
                    g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
                }
            }
        });
        button_6.setText("  ��  ��  ");
        toolBar.add(button_6);
        
        final JButton button_7 = new JButton();
        button_7.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                System.exit(0); // �h�X���ε{��
            }
        });
        button_7.setText("  �h  �X  ");
        toolBar.add(button_7);
        
        final JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        final JMenu menu = new JMenu();
        menu.setText("�u  ��");
        menuBar.add(menu);
        
        final JMenuItem newItemMenuItem = new JMenuItem();
        newItemMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �ŧi�e�����ݩʡA�ʲӬ�1����
                BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // �]�wø�ϤW�U���H���e��
            }
        });
        newItemMenuItem.setText("��  �u");
        menu.add(newItemMenuItem);
        
        final JMenuItem newItemMenuItem_1 = new JMenuItem();
        newItemMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �ŧi�e�����ݩʡA�ʲӬ�2����
                BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // �]�wø�ϤW�U���H���e��
            }
        });
        newItemMenuItem_1.setText("��  �u");
        menu.add(newItemMenuItem_1);
        
        final JMenuItem newItemMenuItem_2 = new JMenuItem();
        newItemMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �ŧi�e�����ݩʡA�ʲӬ�4����
                BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // �]�wø�ϤW�U���H���e��
            }
        });
        newItemMenuItem_2.setText("��  ��");
        menu.add(newItemMenuItem_2);
        
        final JMenu menu_1 = new JMenu();
        menu_1.setText("�C  ��");
        menuBar.add(menu_1);
        
        final JMenuItem newItemMenuItem_3 = new JMenuItem();
        newItemMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �}�ҿ���C���͵���
                Color fColor = JColorChooser.showDialog(null, "����C���͵���",
                        Color.CYAN);
                if (fColor != null) {
                    foreColor = fColor;
                }
                g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
            }
        });
        newItemMenuItem_3.setText("�e���C��");
        menu_1.add(newItemMenuItem_3);
        
        final JMenuItem newItemMenuItem_4 = new JMenuItem();
        newItemMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �}�ҿ���C���͵���
                Color bgColor = JColorChooser.showDialog(null, "����C���͵���",
                        Color.CYAN);
                if (bgColor != null) {
                    backgroundColor = bgColor;
                }
                g.setColor(backgroundColor); // �]�wø�ϤW�U���H���I����
                g.fillRect(0, 0, 570, 390); // �έI�����R��ӵe��
                g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
                canvas.repaint(); // ��s�e��
            }
        });
        newItemMenuItem_4.setText("�I���C��");
        menu_1.add(newItemMenuItem_4);
        
        final JMenu menu_2 = new JMenu();
        menu_2.setText("�s  ��");
        menuBar.add(menu_2);
        
        final JMenuItem newItemMenuItem_5 = new JMenuItem();
        newItemMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                g.setColor(backgroundColor); // �]�wø�ϤW�U���H���I����
                g.fillRect(0, 0, 570, 390); // �έI�����R��ӵe��
                g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
                canvas.repaint(); // ��s�e��
            }
        });
        newItemMenuItem_5.setText("�M  ��");
        menu_2.add(newItemMenuItem_5);
        
        newItemMenuItem_6 = new JMenuItem();
        newItemMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (newItemMenuItem_6.getText().equals("��  ��")) { // ������ֿ��A�ϥξ��
                    rubber = true; // �]�w��ּлx��true
                    newItemMenuItem_6.setText("�e  ��"); // ���ܿ��W��ܪ��¤�r���e��
                    button_6.setText("  �e  ��  "); // ���ܫ��s�W��ܪ��¤�r���e��
                } else { // �����u����W���e�ϫ��s�A�ϥεe��
                    rubber = false; // �]�w��ּлx��false
                    newItemMenuItem_6.setText("��  ��"); // ���ܿ��W��ܪ��¤�r�����
                    button_6.setText("  ��  ��  "); // ���ܫ��s�W��ܪ��¤�r�����
                    g.setColor(foreColor); // �]�wø�ϤW�U���H���e����
                }
            }
        });
        newItemMenuItem_6.setText("��  ��");
        menu_2.add(newItemMenuItem_6);
        
        final JMenu menu_3 = new JMenu();
        menu_3.setText("�t��");
        menuBar.add(menu_3);
        
        final JMenuItem newItemMenuItem_8 = new JMenuItem();
        newItemMenuItem_8.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        newItemMenuItem_8.setText("�h  �X");
        menu_3.add(newItemMenuItem_8);
    }
    
    public static void main(String[] args) {
        DrawPictureFrame frame = new DrawPictureFrame();
        frame.setVisible(true);
    }
}
