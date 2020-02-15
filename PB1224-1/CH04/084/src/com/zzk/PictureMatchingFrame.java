package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class PictureMatchingFrame extends JFrame implements MouseListener,
        MouseMotionListener {
    private JLabel img[] = new JLabel[3];// ��ܹϼЪ�����
    private JLabel targets[] = new JLabel[3];// ����U����ܤ�r������
    private Point pressPoint; // ���Ы��U�ɪ��_�l�y��
    
    public static void main(String args[]) {
        PictureMatchingFrame frame = new PictureMatchingFrame(); // �إߥ����O�ﹳ
        frame.setVisible(true); // �]�w���鬰�i�����A
    }
    
    public PictureMatchingFrame() {
        super();
        getContentPane().setLayout(new BorderLayout());
        setBounds(100, 100, 364, 312);
        setTitle("�Ϥ��t��C��");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel imagePanel = new JPanel();
        imagePanel.setLayout(null);
        imagePanel.setOpaque(false);
        setGlassPane(imagePanel);
        getGlassPane().setVisible(true);
        ImageIcon icon[] = new ImageIcon[3];
        icon[0] = new ImageIcon(getClass().getResource("screen.png"));
        icon[1] = new ImageIcon(getClass().getResource("clothing.png"));
        icon[2] = new ImageIcon(getClass().getResource("bike.png"));
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        for (int i = 0; i < 3; i++) {
            img[i] = new JLabel(icon[i]); // �إ߹ϧμ���
            img[i].setSize(50, 50); // �]�w���Ҥj�p
            img[i].setBorder(new LineBorder(Color.GRAY)); // �]�w�u�����
            int x = (int) (Math.random() * (getWidth() - 50)); // �H������X�y��
            int y = (int) (Math.random() * (getHeight() - 150));// �H������Y�y��
            img[i].setLocation(x, y); // �]�w�H���y��
            img[i].addMouseListener(this); // ���C�ӹϧμ��ҼW�[���Шƥ��ť��
            img[i].addMouseMotionListener(this);
            imagePanel.add(img[i]); // �W�[�ϧμ��Ҩ�ϧέ��O
            targets[i] = new JLabel(); // �إߤ���m����
            targets[i].setOpaque(true); // �ϼ��Ҥ��z���A�H�]�w�I����
            targets[i].setBackground(Color.ORANGE); // �]�w���ҭI����
            targets[i].setHorizontalTextPosition(SwingConstants.CENTER); // �]�w�¤�r�P�ϧΤ����~��
            targets[i].setVerticalTextPosition(SwingConstants.BOTTOM); // �]�w�¤�r��ܦb�ϧΤU��
            targets[i].setPreferredSize(new Dimension(80, 80)); // �]�w���ҭ����j�p
            targets[i].setHorizontalAlignment(SwingConstants.CENTER); // ��r�~�����
            bottomPanel.add(targets[i]); // �W�[���Ҩ쩳�����O
        }
        targets[0].setText("��ܾ�"); // �]�w����m���¤�r
        targets[1].setText("��A");
        targets[2].setText("�ۦ樮");
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
        pressPoint = e.getPoint(); // �x�s���Ϥ����Үɪ��_�l�y��
    }
    
    public void mouseReleased(MouseEvent e) {
        if (checkPosition()) { // �p�G�t�勵�T
            getGlassPane().setVisible(false);
            for (int i = 0; i < 3; i++) { // �ˬd�Ҧ�����m������
                targets[i].setText("��令�\"); // �]�w���T����
                targets[i].setIcon(img[i].getIcon()); // �]�w��諸�ϼ�
            }
        }
    }
    
    /**
     * ���Щ�ʱ���ɪ��ƥ�B�z��k
     */
    public void mouseDragged(MouseEvent e) {
        JLabel source = (JLabel) e.getSource(); // ��o�ƥ󷽱��
        Point imgPoint = source.getLocation(); // ��o����y��
        Point point = e.getPoint(); // ��o���Юy��
        source.setLocation(imgPoint.x + point.x - pressPoint.x, imgPoint.y
                + point.y - pressPoint.y); // �]�w����s�y��
    }
    
    private boolean checkPosition() {// �ˬd�t��O�_���T
        boolean result = true;
        for (int i = 0; i < 3; i++) {
            Point location = img[i].getLocationOnScreen(); // ��o�C�ӹϧμ��Ҫ���m
            Point seat = targets[i].getLocationOnScreen(); // ��o�C�ӹ�����m���y��
            targets[i].setBackground(Color.GREEN); // �]�w���᪺�C��
            // �p�G�t����~
            if (location.x < seat.x || location.y < seat.y
                    || location.x > seat.x + 80 || location.y > seat.y + 80) {
                targets[i].setBackground(Color.ORANGE); // �^�й�����m���C��
                result = false; // �˴����G��false
            }
        }
        return result; // �Ǧ^�˴����G
    }
}
