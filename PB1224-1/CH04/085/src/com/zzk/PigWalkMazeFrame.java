package com.zzk;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class PigWalkMazeFrame extends JFrame implements KeyListener, Runnable {
    Rectangle rect1, rect2, rect3, rect4;
    int gobuttonX = 0, gobuttonY = 0;
    final JButton goButton = new JButton();
    URL url = getClass().getResource("/images/pig.png");
    ImageIcon imageIcon = new ImageIcon(url);
    final JLabel label = new JLabel();
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PigWalkMazeFrame frame = new PigWalkMazeFrame();
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
    public PigWalkMazeFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent e) {
                goButton.requestFocus(); // 使小豬獲得焦點
            }
        });
        getContentPane().setLayout(null);
        setBounds(100, 100, 488, 375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("小豬走迷宮");
        BakcgroundPanel panel = new BakcgroundPanel();
        rect1 = new Rectangle(0, 55, 190, 40);
        rect2 = new Rectangle(190, 40, 40, 240);
        rect3 = new Rectangle(190, 180, 230, 40);
        rect4 = new Rectangle(300, 180, 40, 140);
        setResizable(false);
        panel.setLayout(null);
        panel.setBounds(0, 0, 482, 341);
        getContentPane().add(panel);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                buttonAction(e);
            }
        });
        URL url = getClass().getResource("/images/button.png");
        ImageIcon imageIcons = new ImageIcon(url);
        button.setIcon(imageIcons);
        button.setBounds(27, 100, 106, 28);
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight());
        panel.add(goButton);
        panel.add(button);
        gobuttonX = goButton.getBounds().x; // 定義小豬按鈕的橫座標
        gobuttonY = goButton.getBounds().y; // 定義小豬按鈕的縱座標
        goButton.addKeyListener(this);
        goButton.setIcon(imageIcon);
        goButton.setContentAreaFilled(false); // 取消填充區域
        goButton.setBorder(null); // 取消邊框
        url = getClass().getResource("/images/exit.png");
        imageIcons = new ImageIcon(url);
        label.setIcon(imageIcons);
        label.setBounds(300, 315, imageIcons.getIconWidth(), imageIcons
                .getIconHeight());
        panel.add(label);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if ((gobuttonY == 286)) { // 如果小豬的縱座標等於286
            Thread thread = new Thread(this);
            thread.start(); // 啟動線程
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) { // 如果使用者按下了向上鍵
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20); // 建立Rectangle對像
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) { // 判斷小豬是否走出了迷宮
                gobuttonY = gobuttonY - 2; // 設定變數座標
                goButton.setLocation(gobuttonX, gobuttonY); // 設定按鈕座標
            } else { // 如果小豬走出了迷宮
                JOptionPane.showMessageDialog(this, "撞牆了吧！重新開始吧！", "撞牆啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) { // 判斷使用者是否按向下鍵
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonY = gobuttonY + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "撞牆了吧！重新開始吧！", "撞牆啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) { // 如果使用者按向左鍵
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX - 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "撞牆了吧！重新開始吧！", "撞牆啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // 如果使用者按向右鍵
            Rectangle rectAngle = new Rectangle(gobuttonX, gobuttonY, 20, 20);
            if (rectAngle.intersects(rect1)
                    || rectAngle.intersects(rect2)
                    || rectAngle.intersects(rect3)
                    || rectAngle.intersects(rect4)) {
                gobuttonX = gobuttonX + 2;
                goButton.setLocation(gobuttonX, gobuttonY);
            } else {
                JOptionPane.showMessageDialog(this, "撞牆了吧！重新開始吧！", "撞牆啦！",
                        JOptionPane.DEFAULT_OPTION);
            }
        }
    }
    
    public void buttonAction(ActionEvent e) {
        goButton.setIcon(imageIcon); // 重新設定按鈕的顯示圖片
        goButton.addKeyListener(this); // 為按鈕增加鍵碟事件
        goButton.setBounds(0, 40, imageIcon.getIconWidth(), imageIcon
                .getIconHeight()); // 設定小豬位置
        gobuttonX = goButton.getBounds().x; // 獲得小豬目前位置的X座標
        gobuttonY = goButton.getBounds().y;// 獲得小豬目前位置的Y座標
        goButton.requestFocus(); // 設定按鈕獲得焦點
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void run() {
        URL out = getClass().getResource("/images/pigOut.png"); // 獲得圖片URL
        ImageIcon imageout = new ImageIcon(out); // 建立圖片對像
        goButton.setIcon(imageout); // 設定小豬按鈕顯示圖片
        goButton.setBounds(gobuttonX,
                gobuttonY - imageout.getIconHeight() + 50, imageout
                        .getIconWidth(), imageout.getIconHeight()); // 重新設定按鈕位置
        goButton.removeKeyListener(this); // 按鈕移除鍵碟事件
    }
    
}
