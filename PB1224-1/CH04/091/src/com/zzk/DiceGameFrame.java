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
    private boolean flag = false; // �Ω�лx���j���p���ܼơAtrue������j�Afalse������p
    private int v1 = 0; // �Ĥ@�ӻ�l���I��
    private int v2 = 0; // �ĤG�ӻ�l���I��
    private int v3 = 0; // �ĤT�ӻ�l���I��
    private int stopIndex = 0; // ��stopIndex��50�ɡA��̲ܳת��I��
    private final JLabel lb_dice_1 = new JLabel();
    private final JLabel lb_dice_2 = new JLabel();
    private final JLabel lb_dice_3 = new JLabel();
    final JLabel lb_follow = new JLabel();
    Thread thread = null; // �w�q�P�_��l�I�ƪ��u�{
    Thread okThread = null; // �w�q�T�{��`���u�{
    // �H��������
    private int ten = 10;
    private int twenty = 20;
    private int fifty = 50;
    private int hundred = 100;
    // �H�������ȼаO
    private boolean tenFlag = false;
    private boolean twentyFlag = false;
    private boolean fiftyFlag = false;
    private boolean hundredFlag = false;
    // �H�����аO
    private boolean moneyFlag = false;
    // �[���U�`���аO
    private boolean doubleFlag = false;
    // �H�������֭p���B
    int totalMoney = 0;
    // �T�{�U�`�аO
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
        setTitle("�Y��l");
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
                "/icon/�p.png"));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (ok == true) {
                    if (thread == null) {
                        flag = false; // ��false������p
                        thread = new Thread(new DiceThread()); // �إߧP�_��l�I�ƪ��u�{
                        thread.start(); // �Ұʽu�{
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "�ФU�`��i��T�{�C");
                }
            }
        });
        button.setBounds(216, 337, 106, 28);
        bgPanel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/�j.png"));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (ok == true) {
                    if (thread == null) {
                        flag = true; // ��true������j
                        thread = new Thread(new DiceThread()); // �إߧP�_��l�I�ƪ��u�{
                        thread.start(); // �Ұʽu�{
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "�ФU�`��i��T�{�C");
                }
            }
        });
        button_1.setBounds(339, 337, 106, 28);
        bgPanel.add(button_1);
        
        final JButton btn_10 = new JButton();
        btn_10.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!tenFlag) {
                    // �H�������ȼаO
                    tenFlag = true;
                    // �H�����аO
                    moneyFlag = true;
                    // �H�������֭p���B
                    totalMoney = totalMoney + ten;
                }
            }
        });
        btn_10.setText("10��");
        btn_10.setBounds(215, 386, 106, 28);
        bgPanel.add(btn_10);
        
        final JButton btn_20 = new JButton();
        btn_20.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!twentyFlag) {
                    // �H�������ȼаO
                    twentyFlag = true;
                    // �H�����аO
                    moneyFlag = true;
                    // �H�������֭p���B
                    totalMoney = totalMoney + twenty;
                }
            }
        });
        btn_20.setText("20��");
        btn_20.setBounds(339, 386, 106, 28);
        bgPanel.add(btn_20);
        
        final JButton btn_50 = new JButton();
        btn_50.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!fiftyFlag) {
                    // �H�������ȼаO
                    fiftyFlag = true;
                    // �H�����аO
                    moneyFlag = true;
                    // �H�������֭p���B
                    totalMoney = totalMoney + fifty;
                }
            }
        });
        btn_50.setText("50��");
        btn_50.setBounds(216, 420, 106, 28);
        bgPanel.add(btn_50);
        
        final JButton btn_100 = new JButton();
        btn_100.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (!hundredFlag) {
                    // �H�������ȼаO
                    hundredFlag = true;
                    // �H�����аO
                    moneyFlag = true;
                    // �H�������֭p���B
                    totalMoney = totalMoney + hundred;
                }
            }
        });
        btn_100.setText("100��");
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
                        JOptionPane.showMessageDialog(null, "��ܤH������~��[���U�`�C");
                    }
                }
            }
        });
        btn_double.setText("�[��");
        btn_double.setBounds(339, 454, 106, 28);
        bgPanel.add(btn_double);
        
        final JButton btn_ok = new JButton();
        btn_ok.setIcon(SwingResourceManager.getIcon(DiceGameFrame.class,
                "/icon/xiazhu.png"));
        btn_ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (moneyFlag == true) { // �p�G���a�T�{�U�`�F�N����U�����{���X
                    ok = true;
                    if (okThread == null) {
                        okThread = new Thread(new OkAnteThread()); // �إ߽T�{��`���u�{�ﹳ
                    }
                    if (!okThread.isAlive()) {
                        okThread.start(); // �Ұʽu�{�ﹳ
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "�ФU�`��i��T�{�C");
                }
            }
        });
        btn_ok.setBounds(339, 505, 106, 28);
        bgPanel.add(btn_ok);
        
        lb_follow.setFont(new Font("", Font.BOLD, 16));
        lb_follow.setForeground(new Color(0, 0, 255));
        lb_follow.setText("��F......");
        lb_follow.setBounds(121, 478, 137, 55);
        lb_follow.setVisible(false);
        bgPanel.add(lb_follow);
        
    }
    
    /**
     * @author �i���[
     * �P�_��l�I�ƪ��u�{
     */
    private class DiceThread implements Runnable {
        public void run() {
            while (true) {
                stopIndex++;
                v1 = (int) (Math.random() * 6 + 1); // �H�����ͲĤ@�ӻ�l���I��
                v2 = (int) (Math.random() * 6 + 1); // �H�����ͲĤG�ӻ�l���I��
                v3 = (int) (Math.random() * 6 + 1); // �H�����ͲĤT�ӻ�l���I��
                // ��ܻ�l���Ϥ�
                lb_dice_1.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v1 + ".png"));
                lb_dice_2.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v2 + ".png"));
                lb_dice_3.setIcon(SwingResourceManager.getIcon(
                        DiceGameFrame.class, "/icon/" + v3 + ".png"));
                int totalValues = v1 + v2 + v3; // ��l���I���`�M
                if (stopIndex == 50) { // ��stopIndex��50�ɡA��ܰT���ءA�ô��̲ܳת��I��
                    if (flag == true) {
                        if (totalValues > 9) { // ��l���I�Ƭ��j����ܪ����ܸ�T
                            JOptionPane.showMessageDialog(null, "�I�ƬO�G"
                                    + totalValues + "�A�j�C\n���aĹ�I�I�I\n�`���ơG�H����"
                                    + totalMoney + "��");
                        } else {
                            JOptionPane.showMessageDialog(null, "�I�ƬO�G"
                                    + totalValues + "�A�p�C\n���aĹ�I�I�I\n�`���ơG�H����"
                                    + totalMoney + "��");
                        }
                    } else {
                        if (totalValues <= 9) { // ��l���I�Ƭ��p����ܪ����ܸ�T
                            JOptionPane.showMessageDialog(null, "�I�ƬO�G"
                                    + totalValues + "�A�p�C\n���aĹ�I�I�I\n�`���ơG�H����"
                                    + totalMoney + "��");
                        } else {
                            JOptionPane.showMessageDialog(null, "�I�ƬO�G"
                                    + totalValues + "�A�j�C\n���aĹ�I�I�I\n�`���ơG�H����"
                                    + totalMoney + "��");
                        }
                    }
                    thread = null;
                    stopIndex = 0;
                    // �H�������ȼаO
                    tenFlag = false;
                    twentyFlag = false;
                    fiftyFlag = false;
                    hundredFlag = false;
                    // �[���U�`���аO
                    doubleFlag = false;
                    // �H�����аO
                    moneyFlag = false;
                    // �H�������֭p���B
                    totalMoney = 0;
                    // �T�{�U�`�аO
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
     * @author �i���[
     * �T�{��`���u�{
     */
    private class OkAnteThread implements Runnable {
        public void run() {
            boolean followFlag = true; // �Ϥ�r�{ģ���аO�ܼ�
            while (true) {
                if (moneyFlag == true && ok == true) { // �p�G���a�T�{�U�`�F�N����U�����ԭz�A��ܸ�F����T
                    int count = 0;
                    while (true && followFlag == true) {
                        count++;
                        lb_follow.setVisible(true);
                        // ��{��r���{ģ�ĪG
                        if (count % 2 == 1) {
                            lb_follow.setForeground(new Color(255, 0, 0));
                        } else {
                            lb_follow.setForeground(new Color(0, 0, 255));
                        }
                        if (count > 20) {
                            lb_follow.setVisible(false); // ���ä�r�{ģ������
                            followFlag = false; // ��Ϥ�r�{ģ���аO�ܼƳ]�w��false
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
                    lb_follow.setVisible(false); // ���ä�r�{ģ������
                    okThread = null; // ����u�{�귽
                    break;
                }
            }
        }
    }
}
