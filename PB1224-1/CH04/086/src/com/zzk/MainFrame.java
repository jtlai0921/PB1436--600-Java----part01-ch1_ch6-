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
        setTitle("拼圖遊戲");
        setBounds(300, 300, 358, 414);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 實例化JPanel
        final JPanel panel = new JPanel();
        // 增加到上方
        getContentPane().add(panel, BorderLayout.NORTH);
        // 實例化遊戲面板
        final GamePanel gamePanel = new GamePanel();
        // 增加到中央位置
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        // 實例化按鈕
        final JButton button = new JButton();
        // 註冊事件
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // 開始遊戲
                gamePanel.random();
            }
        });
        button.setText("開始");
        panel.add(button);
    }
    
}
