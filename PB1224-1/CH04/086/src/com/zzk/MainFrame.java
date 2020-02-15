package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 7791539566768257092L;
    
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public MainFrame() {
        super();
        getContentPane().setLayout(new BorderLayout());
        setTitle("���ϹC��");
        setBounds(300, 300, 358, 414);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ��Ҥ�JPanel
        final JPanel panel = new JPanel();
        // �W�[��W��
        getContentPane().add(panel, BorderLayout.NORTH);
        // ��ҤƹC�����O
        final GamePanel gamePanel = new GamePanel();
        // �W�[�줤����m
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        // ��Ҥƫ��s
        final JButton button = new JButton();
        // ���U�ƥ�
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �}�l�C��
                gamePanel.random();
            }
        });
        button.setText("�}�l");
        panel.add(button);
    }
    
}
