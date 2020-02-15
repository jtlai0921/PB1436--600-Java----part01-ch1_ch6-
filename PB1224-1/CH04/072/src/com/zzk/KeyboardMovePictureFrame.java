package com.zzk;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyboardMovePictureFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private ImageIcon icon = null;// �ŧi�ϧιϼ�
    final JLabel lb_move = new JLabel();// �z�L��б������
    
    public static void main(String args[]) {
        KeyboardMovePictureFrame frame = new KeyboardMovePictureFrame();
        frame.setVisible(true);// ��ܵ���
        frame.getContentPane().requestFocus();// �ϵ��骺���e���O��o�J�I
    }
    
    public KeyboardMovePictureFrame() {
        super();
        getContentPane().addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                int x = lb_move.getLocation().x;// ��o���ʼ��Ҫ�x�y��
                int y = lb_move.getLocation().y;// ��o���ʼ��Ҫ�y�y��
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    lb_move.setLocation(x - 10, y);// �V�����ʡAx�y�д�p
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    lb_move.setLocation(x, y - 10);// �V�W���ʡAy�y�д�p
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    lb_move.setLocation(x + 10, y);// �V�k���ʡAx�y�мW�[
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    lb_move.setLocation(x, y + 10);// �V�U���ʡAy�y�мW�[
                }
            }
        });
        setTitle("�z�L��в��ʹϤ�");
        getContentPane().setLayout(null);
        setBounds(100, 100, 364, 239);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = KeyboardMovePictureFrame.class
                .getResource("/image/coney.png");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        icon = new ImageIcon(img);// �إ߹ϧιϼ�
        lb_move.setIcon(icon);// ���w������ܪ��ϼ�
        lb_move.setBounds(35, 30, 124, 102);
        getContentPane().add(lb_move);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                // �b�Ϥ��W�������Хk��h�X�t��
                if (e.getButton() == MouseEvent.BUTTON3) {
                    System.exit(0);
                }
            }
        });
    }
}
