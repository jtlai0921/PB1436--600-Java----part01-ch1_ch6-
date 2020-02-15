package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Cursor;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private BackgroundPanel backgroundPanel = null;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame thisClass = new MainFrame();
                thisClass.setVisible(true);
            }
        });
    }
    
    public MainFrame() {
        super();
        setTitle("�����Ƹ��ʵe");
        setSize(628, 441);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = new ImageIcon(getClass().getResource("/image/cursor.png")).getImage();// �إ߹ϧιﹳ
        Cursor cursor = getToolkit().createCustomCursor(image, new Point(),"�]��");// �إ߹��Х��йﹳ
        setCursor(cursor);// ���w���Х���
        setResizable(false);// �����\���ܵ���j�p
        backgroundPanel = new BackgroundPanel();// �إ߭I�����O
        // ���I�����O���w�ϧ�
        backgroundPanel.setImage(new ImageIcon(getClass().getResource("/image/bg.jpg")).getImage());
        backgroundPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {// ���в��ʨƥ�
                SnowFlakeLabel snow = new SnowFlakeLabel();// �إ߳����Ƹ�����
                Point point = e.getPoint();// ��o���Ц�m
                snow.setLocation(point);// ���w����b�I�����O�W����m
                backgroundPanel.add(snow);// �N����W�[��I�����O�W
            }
        });
        getContentPane().setLayout(new BorderLayout());// ���w���餺�e���O����ɧG��
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);// �b���餺�e���O�W�W�[�I�����O
    }
}