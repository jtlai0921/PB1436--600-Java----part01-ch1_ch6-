package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class FrameActionFrame extends JFrame {
    Thread thread = new Thread(new ActionThread());
    boolean flag = true; // 用於標誌動畫播放、暫停和繼續的成員變數
    boolean stop = false; // 用於標誌動畫播放和停止的成員變數
    final JLabel label = new JLabel(); // 顯示圖片的標籤
    
    public FrameActionFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // 啟動線程，在窗體開啟時播放動畫
            }
        });
        setTitle("框動畫效果");
        setBounds(260, 240, 324, 227); // 圖片的寬度和高度392,208
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);// 標籤內容水平居中
        label.setIcon(SwingResourceManager.getIcon(FrameActionFrame.class,
                "/image/1.gif"));
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (thread == null) {
                    thread = new Thread(new ActionThread()); // 如果線程為空，則建立線程對像
                }
                if (!thread.isAlive()) {
                    // 如果線程不是活動線程則執行下面的程式碼，啟動線程的執行
                    stop = false;
                    flag = true;
                    thread.start();
                }
            }
        });
        button.setText("播  放");
        panel.add(button);
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = false; // 暫停動畫播放
            }
        });
        button_1.setText("暫  停");
        panel.add(button_1);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = true; // 繼續播放動畫
            }
        });
        button_3.setText("繼  續");
        panel.add(button_3);
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                stop = true; // 停止播放動畫
            }
        });
        button_2.setText("停  止");
        panel.add(button_2);
    }
    
    public static void main(String[] args) {
        FrameActionFrame frame = new FrameActionFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author 張振坤
     *         用於實現動畫的線程
     */
    private class ActionThread implements Runnable {
        private int index = 1; // 用於控制圖形檔案的主檔案名
        public void run() {
            while (true) {
                if (stop) {
                    thread = null; // 銷毀線程
                    break; // 跳出循環，結束線程的執行
                }
                if (flag) {
                    String picture = "/image/"; // 建立圖片存放位置和檔案名的變數
                    index++;
                    if (index <= 8) {
                        picture = picture + index + ".jpg"; // 透過索引獲得圖片的位置和檔案名
                    } else {
                        index = 1;
                        picture = picture + index + ".jpg"; // 透過索引獲得圖片的位置和檔案名
                    }
                    // 改變標籤上顯示的圖片實現動畫效果
                    label.setIcon(SwingResourceManager.getIcon(
                            FrameActionFrame.class, picture));
                    try {
                        Thread.sleep(200); // 線程睡眠200毫秒
                    } catch (Exception ex) {
                        
                    }
                }
            }
        }
    }
}
