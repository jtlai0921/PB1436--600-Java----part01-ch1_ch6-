package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.awt.AWTUtilities;

/**
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class ClockFrame extends JDialog {
    private float opqua = 0.7f;
    private ClockPanel clockPanel;
    private Point fp; // 拖曳窗體之前的鼠標位置
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClockFrame frame = new ClockFrame();// 建立窗體對像
                    frame.setVisible(true);// 顯示窗體
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * 佈局窗體的建構方法
     */
    public ClockFrame() {
        super();
        setUndecorated(true);// 取消窗體修飾
        setAlwaysOnTop(true);// 窗體置頂
        setTitle("明日科技石英鐘");// 設定窗體標題
        getContentPane().setLayout(new BorderLayout());
        setBounds(100, 30, 217, 257);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        clockPanel = new ClockPanel();// 建立時鐘面板
        getContentPane().add(clockPanel);
        // 為時鐘面板增加鼠標按鍵事件監聽器
        clockPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                fp = e.getPoint();
                if (e.getButton()==MouseEvent.BUTTON3){
                    System.exit(0);// 右鍵退出
                }
            }
        });
        // 在時鐘面板的鼠標拖曳事件中移動窗體
        clockPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                JDialog frame = (JDialog) getRootPane().getParent();
                Point point = e.getLocationOnScreen();
                frame.setLocation(point.x - fp.x, point.y - fp.y);
            }
        });
        pack();
        
        addKeyListener(new KeyAdapter() {// 為窗體增加鍵碟事件監聽器
            public void keyPressed(final KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {// 判斷按鍵寫程式
                    case KeyEvent.VK_ADD:// +符號按鍵會降低透明圖
                        opqua += 0.05;
                        opqua = opqua > 0.95f ? 1f : opqua;
                        break;
                    case KeyEvent.VK_SUBTRACT:// -符號按鍵會提升透明度
                        opqua -= 0.05;
                        opqua = opqua < 0.1f ? 0.1f : opqua;
                        break;
                }
                // 如果按Ctrl+Shift+X組合鍵，將退出程式
                if (code == KeyEvent.VK_X
                        && e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK))
                    System.exit(0);
                AWTUtilities.setWindowOpacity(ClockFrame.this, opqua);// 更新窗體透明度
            }
        });
        
        AWTUtilities.setWindowOpacity(this, opqua);
        Dimension screenSize = getToolkit().getScreenSize();
        double width = screenSize.getWidth();
        int x = (int) (width - getWidth() - 30);
        setLocation(x, 30);
        
        new Thread() {// 建立線程對象，更新時鐘面板界面
            @Override
            public void run() {
                try {
                    while (true) {
                        sleep(1000);// 休眠1秒
                        clockPanel.repaint();// 重新繪製時鐘面板界面
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
