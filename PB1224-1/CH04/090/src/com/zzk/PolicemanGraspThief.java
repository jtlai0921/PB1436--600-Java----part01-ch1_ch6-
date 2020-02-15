package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class PolicemanGraspThief extends JFrame {
    private JLabel label;
    private JButton button;
    final JLabel lb_thief = new JLabel(); // ��ܤp��������
    final JLabel lb_policeman = new JLabel(); // ���ĵ�����
    // �إ߽u�{�ﹳ
    private Thread thread = new Thread(new GraspThiefThread());
    private boolean stop = false; // ��true�ɡA��ܴ��ܯ¤�r���u�����F�v�����ҡA��false�ɤ����
    private final JLabel lb_tip = new JLabel("�����F");
    
    public PolicemanGraspThief() {
        super();
        setTitle("ĵ���p��");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 80, 808, 584);
        
        final BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setFocusable(false);
        backgroundPanel.setImage(SwingResourceManager.getImage(
                PolicemanGraspThief.class, "/image/background.png"));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        lb_thief.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/�p��.png"));
        lb_thief.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent arg0) {
                stop = true; // stop��true�ɡA��ܴ��ܯ¤�r���u�����F�v������
                JOptionPane.showMessageDialog(null, "�����F..."); // ��ܰT����
                lb_tip.setVisible(false);
            }
        });
        lb_thief.setBounds(350, 150, 50, 50);
        backgroundPanel.add(lb_thief);
        
        lb_tip.setBounds(350, 210, 66, 50);
        lb_tip.setForeground(new Color(0, 0, 255));
        lb_tip.setFont(new Font("", Font.BOLD, 16));
        backgroundPanel.add(lb_tip);
        
        lb_policeman.setIcon(SwingResourceManager.getIcon(
                PolicemanGraspThief.class, "/icon/ĵ��.png"));
        lb_policeman.setBounds(0, 131, 95, 88);
        backgroundPanel.add(lb_policeman);
        lb_tip.setVisible(false);
        backgroundPanel.add(getButton());
        backgroundPanel.add(getLabel());
        thread.start();
        
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.setIcon(SwingResourceManager.getIcon(
                    PolicemanGraspThief.class, "/icon/zailai.png"));
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent arg0) {
                    if (thread == null) { // �u�{�ﹳ����
                        thread = new Thread(new GraspThiefThread()); // �إ߽u�{�ﹳ
                    }
                    if (!thread.isAlive()) { // ���O���ʽu�{
                        stop = false; // stop��false�ɪk��ܴ��ܯ¤�r���u�����F�v������
                        lb_tip.setVisible(false); // ���ô��ܯ¤�r���u�����F�v������
                        thread.start(); // ���s�Ұʽu�{�ﹳ
                    }
                }
            });
            button.setBounds(616, 485, 149, 47);
        }
        return button;
    }
    
    /**
     * @author �i���[
     *         �Ϥp���B�ʪ��u�{
     */
    private class GraspThiefThread implements Runnable {
        boolean flag = false; // �лx�p���V���B���٬O�V�k�B�ʪ��ܼ�
        int x = 400; // �p�����ҥ�����ɪ���y��
        
        public void run() {
            while (true) {
                if (stop) { // stop��true�ɡA��ܴ��ܯ¤�r���u�����F�v����
                    int x = lb_thief.getX(); // ��o�p�����Ҫ���y��
                    int y = lb_thief.getY(); // ��o�p�����Ҫ��a�y��
                    lb_tip.setBounds(x, y + 60, 50, 50); // �]�w���ܯ¤�r���u�����F�v���Ҫ���ܦ�m�M�j�p
                    lb_tip.setVisible(true); // ��ܴ��ܯ¤�r���u�����F�v������
                    thread = null; // ����u�{�귽
                    break; // �h�X�`���A�����u�{������
                }
                if (flag == false) { // flag��false�V�k�B��
                    x += 20; // x���ȼW�[��ܦV�k�B��
                    if (x == 640) { // ��p�����ҥ�����ɪ���y�ЬO640��
                        flag = true; // �Nflag�����Ȭ�true
                    }
                } else { // flag��true�V���B��
                    x -= 20; // x���ȴ�֪�ܦV���B��
                    if (x == 100) { // ��p�����ҥ�����ɪ���y�ЬO100��
                        flag = false; // �Nflag�����Ȭ�false
                    }
                }
                // ����100-200�������H����ơA�Ω�]�w�p�����ҤW��ɪ��a�y��
                int y = (int) (Math.random() * 100) + 100;
                lb_thief.setLocation(x, y); // �]�w�p�����Ҫ���ܦ�m
                try {
                    Thread.sleep(200); // ��v200�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        PolicemanGraspThief frame = new PolicemanGraspThief();
        frame.setVisible(true);
    }
    
    /**
     * @return
     */
    protected JLabel getLabel() {
        if (label == null) {
            label = new JLabel();
            label.setForeground(new Color(255, 0, 0));
            label.setFont(new Font("", Font.BOLD, 26));
            label.setText("�`�N�G�Шϥι��з�j�A�����p���C");
            label.setBounds(40, 428, 468, 80);
        }
        return label;
    }
    
}
