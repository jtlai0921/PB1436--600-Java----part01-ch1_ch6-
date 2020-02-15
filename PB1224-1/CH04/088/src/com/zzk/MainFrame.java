package com.zzk;

import static java.lang.Math.random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author �i���[
 */
public class MainFrame extends JFrame {
    private static long score = 0;// ����
    private static Integer ammoNum = 5;// �l�u�ƶq
    private static JLabel scoreLabel;// ����
    private BackgroundPanel backgroundPanel;
    private static JLabel ammoLabel;
    private static JPanel infoPane;
    
    /**
     * �[����k
     * 
     * @param score
     */
    public synchronized static void appScore(int num) {
        score += num;
        scoreLabel.setText("���ơG" + score);
    }
    
    /**
     * ���Ӥl�u����k
     * 
     * @param num
     */
    public synchronized static void useAmmo() {
        synchronized (ammoNum) {
            ammoNum--;// �l�u�ƶq����
            ammoLabel.setText("�l�u�ƶq�G" + ammoNum);
            if (ammoNum <= 0) {// �P�_�l�u�O�_�p��0
                new Thread(new Runnable() {
                    public void run() {
                        // ��ܴ��ܸ�T���O
                        infoPane.setVisible(true);
                        try {
                            // 1�����[���l�u���ɶ�
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ammoNum = 5;// ��_�l�u�ƶq
                        // �ק�l�u�ƶq���Ҫ��¤�r
                        ammoLabel.setText("�l�u�ƶq�G" + ammoNum);
                        infoPane.setVisible(false);// ���ô��ܸ�T���O
                    }
                }).start();
            }
        }
    }
    
    /**
     * �P�_�l�u�O�_����
     * 
     * @return
     */
    public synchronized static boolean readyAmmo() {
        synchronized (ammoNum) {
            return ammoNum > 0;
        }
    }
    
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
                    frame.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * �غc��k
     */
    public MainFrame() {
        super();
        setResizable(false);// �i��վ㵡��j�p
        setTitle("��s���y�C��");
        infoPane = (JPanel) getGlassPane();// ��o�������O
        JLabel label = new JLabel("�[���l�u�K�K");// �إߴ��ܼ��Ҥ���
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("����", Font.BOLD, 32));
        label.setForeground(Color.ORANGE);
        infoPane.setLayout(new BorderLayout());
        infoPane.add(label);// �W�[���ܼ��Ҥ����������O
        
        setAlwaysOnTop(true);// �O����O���b�̳��h
        setBounds(100, 100, 573, 411);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgroundPanel = new BackgroundPanel();// �إ߱a�I�������O
        backgroundPanel.setImage(new ImageIcon(getClass()
                .getResource("background.jpg")).getImage());// �]�w�I���Ϥ�
        getContentPane().add(backgroundPanel,
                BorderLayout.CENTER);
        // �W�[���Шƥ󤶭��d
        addMouseListener(new FrameMouseListener());
        scoreLabel = new JLabel();// ��ܤ��ƪ����Ҥ���
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.ORANGE);
        scoreLabel.setText("���ơG");
        scoreLabel.setBounds(25, 15, 120, 18);
        backgroundPanel.add(scoreLabel);
        ammoLabel = new JLabel();// ��ܦ۰ʼƶq�����Ҥ���
        ammoLabel.setForeground(Color.ORANGE);
        ammoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ammoLabel.setText("�l�u�ƶq�G" + ammoNum);
        ammoLabel.setBounds(422, 15, 93, 18);
        backgroundPanel.add(ammoLabel);
    }
    
    /**
     * �ҰʹC������k
     */
    public void start() {
        new PigThread().start();
        new BirdThread().start();
    }
    
    /**
     * ���骺���Шƥ��ť��
     * 
     * @author �i���[
     */
    private final class FrameMouseListener extends MouseAdapter {
        public void mousePressed(final MouseEvent e) {
            Component at = backgroundPanel.getComponentAt(e
                    .getPoint());
            if (at instanceof BackgroundPanel) {// �p�G�I�쭱�O�]�����l�u
                MainFrame.useAmmo();// ���Ӥl�u
            }
        }
    }
    
    /**
     * ���ͽި��⪺�u�{
     * 
     * @author �i���[
     */
    class PigThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // �إߥN���ު����ұ��
                PigLabel pig = new PigLabel();
                pig.setSize(120, 80);// �]�w�����l�j�p
                backgroundPanel.add(pig);// �W�[�����I�����O
                try {
                    // �u�{�H����v�@�q�ɶ�
                    sleep((long) (random() * 3000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * ���ͳ����⪺�u�{
     * 
     * @author �i���[
     */
    class BirdThread extends Thread {
        @Override
        public void run() {
            while (true) {
                // �إߥN��p�������ұ��
                BirdLabel bird = new BirdLabel();
                bird.setSize(50, 50);// �]�w�����l�j�p
                backgroundPanel.add(bird);// �W�[�����I�����O
                try {
                    // �u�{�H����v�@�q�ɶ�
                    sleep((long) (Math.random() * 3000) + 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
