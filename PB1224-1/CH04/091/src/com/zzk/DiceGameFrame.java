package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class DiceGameFrame extends JFrame {
    private boolean flag = false; // 用於標誌壓大壓小的變數，true表示壓大，false表示壓小
    private int v1 = 0; // 第一個骰子的點數
    private int v2 = 0; // 第二個骰子的點數
    private int v3 = 0; // 第三個骰子的點數
    private int stopIndex = 0; // 當stopIndex為50時，顯示最終的點數
    private final JLabel lb_dice_1 = new JLabel();
    private final JLabel lb_dice_2 = new JLabel();
    private final JLabel lb_dice_3 = new JLabel();
    final JLabel lb_follow = new JLabel();
    Thread thread = null; // 定義判斷骰子點數的線程
    Thread okThread = null; // 定義確認賭注的線程
    // 人民幣面值
    private int ten = 10;
    private int twenty = 20;
    private int fifty = 50;
    private int hundred = 100;
    // 人民幣面值標記
    private boolean tenFlag = false;
    private boolean twentyFlag = false;
    private boolean fiftyFlag = false;
    private boolean hundredFlag = false;
    // 人民幣標記
    private boolean moneyFlag = false;
    // 加倍下注的標記
    private boolean doubleFlag = false;
    // 人民幣車累計金額
    int totalMoney = 0;
    // 確認下注標記
    private boolean ok = false;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DiceGameFrame frame = new DiceGameFrame();
        frame.setVisible(true);
    }
    
    public DiceGameFrame() {
        super();
        setTitle("擲骰子");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(673, 577);
        final BackgroundPanel bgPanel = new BackgroundPanel();
        bgPanel.setImage(SwingResourceManager.getImage(DiceGameFrame.class,
                "/image/background.jpg"));
        getContentPane().add(bgPanel, BorderLayout.CENTER);
        
        lb_dice_1.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/1.png"));
        lb_dice_1.setBounds(253, 143, 57, 55);
        bgPanel.add(lb_dice_1);
        
        lb_dice_3.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/1.png"));
        lb_dice_3.setBounds(304, 231, 57, 55);
        bgPanel.add(lb_dice_3);
        
        lb_dice_2.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/1.png"));
        lb_dice_2.setBounds(354, 143, 57, 55);
        bgPanel.add(lb_dice_2);
        
        final JButton button = new JButton();
        button.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/小.png"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (ok == true) {
                    if (thread == null) {
                        flag = false; // 為false表示壓小
                        thread = new Thread(new DiceThread()); // 建立判斷骰子點數的線程
                        thread.start(); // 啟動線程
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "請下注後進行確認。");
                }
            }
        });
        button.setBounds(216, 337, 106, 28);
        bgPanel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/大.png"));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (ok == true) {
                    if (thread == null) {
                        flag = true; // 為true表示壓大
                        thread = new Thread(new DiceThread()); // 建立判斷骰子點數的線程
                        thread.start(); // 啟動線程
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "請下注後進行確認。");
                }
            }
        });
        button_1.setBounds(339, 337, 106, 28);
        bgPanel.add(button_1);
        
        final JButton btn_10 = new JButton();
        btn_10.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!tenFlag) {
                    // 人民幣面值標記
                    tenFlag = true;
                    // 人民幣標記
                    moneyFlag = true;
                    // 人民幣車累計金額
                    totalMoney = totalMoney + ten;
                }
            }
        });
        btn_10.setText("10元");
        btn_10.setBounds(215, 386, 106, 28);
        bgPanel.add(btn_10);
        
        final JButton btn_20 = new JButton();
        btn_20.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!twentyFlag) {
                    // 人民幣面值標記
                    twentyFlag = true;
                    // 人民幣標記
                    moneyFlag = true;
                    // 人民幣車累計金額
                    totalMoney = totalMoney + twenty;
                }
            }
        });
        btn_20.setText("20元");
        btn_20.setBounds(339, 386, 106, 28);
        bgPanel.add(btn_20);
        
        final JButton btn_50 = new JButton();
        btn_50.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!fiftyFlag) {
                    // 人民幣面值標記
                    fiftyFlag = true;
                    // 人民幣標記
                    moneyFlag = true;
                    // 人民幣車累計金額
                    totalMoney = totalMoney + fifty;
                }
            }
        });
        btn_50.setText("50元");
        btn_50.setBounds(216, 420, 106, 28);
        bgPanel.add(btn_50);
        
        final JButton btn_100 = new JButton();
        btn_100.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!hundredFlag) {
                    // 人民幣面值標記
                    hundredFlag = true;
                    // 人民幣標記
                    moneyFlag = true;
                    // 人民幣車累計金額
                    totalMoney = totalMoney + hundred;
                }
            }
        });
        btn_100.setText("100元");
        btn_100.setBounds(339, 420, 106, 28);
        bgPanel.add(btn_100);
        
        final JButton btn_double = new JButton();
        btn_double.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!doubleFlag) {
                    if (moneyFlag == true) {
                        totalMoney = totalMoney * 2;
                        doubleFlag = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "選擇人民幣後才能加倍下注。");
                    }
                }
            }
        });
        btn_double.setText("加倍");
        btn_double.setBounds(339, 454, 106, 28);
        bgPanel.add(btn_double);
        
        final JButton btn_ok = new JButton();
        btn_ok.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/xiazhu.png"));
        btn_ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (moneyFlag == true) { // 如果玩家確認下注了就執行下面的程式碼
                    ok = true;
                    if (okThread == null) {
                        okThread = new Thread(new OkAnteThread()); // 建立確認賭注的線程對像
                    }
                    if (!okThread.isAlive()) {
                        okThread.start(); // 啟動線程對像
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "請下注後進行確認。");
                }
            }
        });
        btn_ok.setBounds(339, 505, 106, 28);
        bgPanel.add(btn_ok);
        
        lb_follow.setFont(new Font("", Font.BOLD, 16));
        lb_follow.setForeground(new Color(0, 0, 255));
        lb_follow.setText("跟了......");
        lb_follow.setBounds(121, 478, 137, 55);
        lb_follow.setVisible(false);
        bgPanel.add(lb_follow);
        
    }
    
    /**
     * @author 張振坤
     * 判斷骰子點數的線程
     */
    private class DiceThread implements Runnable {
        public void run() {
            while (true) {
                stopIndex++;
                v1 = (int) (Math.random() * 6 + 1); // 隨機產生第一個骰子的點數
                v2 = (int) (Math.random() * 6 + 1); // 隨機產生第二個骰子的點數
                v3 = (int) (Math.random() * 6 + 1); // 隨機產生第三個骰子的點數
                // 顯示骰子的圖片
                lb_dice_1.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v1 + ".png"));
                lb_dice_2.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v2 + ".png"));
                lb_dice_3.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v3 + ".png"));
                int totalValues = v1 + v2 + v3; // 骰子的點數總和
                if (stopIndex == 50) { // 當stopIndex為50時，顯示訊息框，並提示最終的點數
                    if (flag == true) {
                        if (totalValues > 9) { // 骰子的點數為大時顯示的提示資訊
                            JOptionPane.showMessageDialog(null, "點數是："
                                    + totalValues + "，大。\n玩家贏！！！\n總錢數：人民幣"
                                    + totalMoney + "元");
                        } else {
                            JOptionPane.showMessageDialog(null, "點數是："
                                    + totalValues + "，小。\n莊家贏！！！\n總錢數：人民幣"
                                    + totalMoney + "元");
                        }
                    } else {
                        if (totalValues <= 9) { // 骰子的點數為小時顯示的提示資訊
                            JOptionPane.showMessageDialog(null, "點數是："
                                    + totalValues + "，小。\n玩家贏！！！\n總錢數：人民幣"
                                    + totalMoney + "元");
                        } else {
                            JOptionPane.showMessageDialog(null, "點數是："
                                    + totalValues + "，大。\n莊家贏！！！\n總錢數：人民幣"
                                    + totalMoney + "元");
                        }
                    }
                    thread = null;
                    stopIndex = 0;
                    // 人民幣面值標記
                    tenFlag = false;
                    twentyFlag = false;
                    fiftyFlag = false;
                    hundredFlag = false;
                    // 加倍下注的標記
                    doubleFlag = false;
                    // 人民幣標記
                    moneyFlag = false;
                    // 人民幣車累計金額
                    totalMoney = 0;
                    // 確認下注標記
                    ok = false;
                    break;
                }
                try {
                    Thread.sleep(20);
                } catch (Exception ex) {
                    System.out.println(flag);
                }
            }
        }
    }
    
    /**
     * @author 張振坤
     * 確認賭注的線程
     */
    private class OkAnteThread implements Runnable {
        public void run() {
            boolean followFlag = true; // 使文字閃耀的標記變數
            while (true) {
                if (moneyFlag == true && ok == true) { // 如果玩家確認下注了就執行下面的敘述，顯示跟了的資訊
                    int count = 0;
                    while (true && followFlag == true) {
                        count++;
                        lb_follow.setVisible(true);
                        // 實現文字的閃耀效果
                        if (count % 2 == 1) {
                            lb_follow.setForeground(new Color(255, 0, 0));
                        } else {
                            lb_follow.setForeground(new Color(0, 0, 255));
                        }
                        if (count > 20) {
                            lb_follow.setVisible(false); // 隱藏文字閃耀的標籤
                            followFlag = false; // 把使文字閃耀的標記變數設定為false
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (Exception ex) {
                            
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception ex) {
                        System.out.println(flag);
                    }
                    lb_follow.setVisible(false); // 隱藏文字閃耀的標籤
                    okThread = null; // 釋放線程資源
                    break;
                }
            }
        }
    }
}
