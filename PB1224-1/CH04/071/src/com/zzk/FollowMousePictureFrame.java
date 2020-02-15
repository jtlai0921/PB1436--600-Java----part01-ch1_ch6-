package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FollowMousePictureFrame extends JFrame {
    private Image img = null;
    private ImageIcon icon = null;
    final JLabel lb_move = new JLabel();
    final JLabel lb_tip = new JLabel();
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FollowMousePictureFrame frame = new FollowMousePictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FollowMousePictureFrame() {
        super();
        setTitle("�H���в��ʪ��Ϥ�");
        getContentPane().setLayout(null);
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = FollowMousePictureFrame.class
                .getResource("/image/coney.png");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        icon = new ImageIcon(img);// �إ߹ϧιϼ�
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent e) {
                int x = e.getX(); // ��o���Цb����e��������y�Э�
                int y = e.getY(); // ��o���Цb����e�������a�y�Э�
                int w = lb_move.getWidth(); // ��o�H���в��ʪ��ϧΩҦb���Ҫ��e��
                int h = lb_move.getHeight(); // ��o�H���в��ʪ��ϧΩҦb���Ҫ�����
                int x1 = x - w / 2; // �p��X�H���в��ʪ��ϧΩҦb���Ҫ���y�Э�
                int y1 = y - h / 2; // �p��X�H���в��ʪ��ϧΩҦb���Ҫ��a�y�Э�
                lb_move.setLocation(x1, y1); // �]�w�H���в��ʪ��ϧΩҦb���Ҫ���ܦ�m
                int x2 = x - 52; // �p����ܤ�r�����Ҫ���y�Э�
                int y2 = y1 + 120; // �p����ܤ�r�����Ҫ��a�y�Э�
                lb_tip.setLocation(x2, y2); // �]�w��ܤ�r�����Ҫ���ܦ�m
            }
        });
        lb_move.setIcon(icon);
        lb_move.setBounds(191, 61, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // �b�Ϥ��W�������Хk��h�X�t��
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
        lb_tip.setText("�ڴN��ۧA�C�C�C");
        lb_tip.setBounds(180, 191, 104, 18);
        getContentPane().add(lb_tip);
        Thread thread = new Thread(new FlareThread()); // �إ߽u�{�ﹳ
        thread.start(); // �Ұʽu�{�ﹳ
    }
    
    /**
     * @author Administrator
     *         �ʺA���ܤ�r�C�⪺�u�{
     */
    class FlareThread implements Runnable {
        public void run() {
            while (true) {
                int red = (int) (Math.random() * 256); // �H������RGB�C�⤤��R�A�Y����
                int green = (int) (Math.random() * 256); // �H������RGB�C�⤤��G�A�Y���
                int blue = (int) (Math.random() * 256); // �H������RGB�C�⤤��B�A�Y�Ŧ�
                lb_tip.setForeground(new Color(red, green, blue)); // �]�w���ҤW��r���e����
                try {
                    Thread.sleep(500); // �u�{��v500�@��
                } catch (Exception e) {
                    
                }
            }
        }
    }
}
