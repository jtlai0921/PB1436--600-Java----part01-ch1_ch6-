package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

public class CinefilmEffectFrame extends JFrame {
    Thread thread = new Thread(new CinefileThread());
    private final JLabel ggLabel = new JLabel();
    private final JLabel label_1 = new JLabel(); // ��ܹϤ�����1�Ӽ���
    private final JLabel label_2 = new JLabel(); // ��ܹϤ�����2�Ӽ���
    private final JLabel label_3 = new JLabel(); // ��ܹϤ�����3�Ӽ���
    private final JLabel label_4 = new JLabel(); // ��ܹϤ�����4�Ӽ���
    private final JLabel label_5 = new JLabel(); // ��ܹϤ�����5�Ӽ���
    private final JLabel ffLabel = new JLabel();
    int x1 = 0; // ��1�Ӽ�����ܦ�m���ܼ�
    int x2 = 98; // ��2�Ӽ�����ܦ�m���ܼ�
    int x3 = 196; // ��3�Ӽ�����ܦ�m���ܼ�
    int x4 = 294; // ��4�Ӽ�����ܦ�m���ܼ�
    int x5 = 392; // ��5�Ӽ�����ܦ�m���ܼ�
    boolean indexFlag = false; // �лx���ҬO�_���Ϫ��ܼ�
    
    public CinefilmEffectFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // �Ұʽu�{�ﹳ
            }
        });
        setTitle("�q�v�����S��"); // �]�w���骺���D
        setBounds(260, 240, 400, 280); // �Ϥ����e�שM����
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // �]�w���骺�����覡
        
        ggLabel.setOpaque(true);
        ggLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/����.JPG"));
        ggLabel.setText("  ");
        getContentPane().add(ggLabel, BorderLayout.NORTH);
        
        ffLabel.setText("  ");
        ffLabel.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/����.JPG"));
        ffLabel.setOpaque(true);
        getContentPane().add(ffLabel, BorderLayout.SOUTH);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        label_1.setBounds(0, 0, 98, 210);
        label_1.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/1.jpg"));
        label_1.setText("New JLabelfdbb");
        panel.add(label_1);
        label_2.setBounds(98, 0, 98, 210);
        label_2.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/2.jpg"));
        label_2.setText("22222222222222");
        panel.add(label_2);
        label_3.setBounds(196, 0, 98, 210);
        label_3.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/3.jpg"));
        label_3.setText("11111111111111");
        panel.add(label_3);
        label_4.setBounds(294, 0, 98, 210);
        label_4.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/4.jpg"));
        label_4.setText("New JLabelfdww");
        panel.add(label_4);
        label_5.setBounds(392, 0, 98, 210);
        label_5.setIcon(SwingResourceManager.getIcon(CinefilmEffectFrame.class,
                "/image/5.jpg"));
        label_5.setText("33333333333333");
        panel.add(label_5);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        CinefilmEffectFrame frame = new CinefilmEffectFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author �i���[
     *         �Ω��{�ʵe���u�{
     */
    private class CinefileThread implements Runnable {
        public void run() {
            while (true) {
                x1 = x1 - 7; // ��1�Ӽ��Ҫ�����ɴ�7�A�Ϩ䥪��
                x2 = x2 - 7; // ��2�Ӽ��Ҫ�����ɴ�7�A�Ϩ䥪��
                x3 = x3 - 7; // ��3�Ӽ��Ҫ�����ɴ�7�A�Ϩ䥪��
                x4 = x4 - 7; // ��4�Ӽ��Ҫ�����ɴ�7�A�Ϩ䥪��
                x5 = x5 - 7; // ��5�Ӽ��Ҫ�����ɴ�7�A�Ϩ䥪��
                label_1.setBounds(x1, 0, 98, 210); // �]�w��1�Ӽ��Ҫ���ܦ�m
                label_2.setBounds(x2, 0, 98, 210); // �]�w��1�Ӽ��Ҫ���ܦ�m
                label_3.setBounds(x3, 0, 98, 210); // �]�w��1�Ӽ��Ҫ���ܦ�m
                label_4.setBounds(x4, 0, 98, 210); // �]�w��1�Ӽ��Ҫ���ܦ�m
                label_5.setBounds(x5, 0, 98, 210); // �]�w��1�Ӽ��Ҫ���ܦ�m
                
                if (x1 == -98) { // ���1�Ӽ��Ҫ���ܦ�m�O-98�ɰ���
                    indexFlag = !indexFlag; // ����indexFlag����
                    x1 = 392; // �]�w��1�Ӽ��Ҫ���ܦ�m
                    if (indexFlag) {
                        // indexFlag��true�ɧ��ܪ��Ϥ�
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/6.jpg"));
                    } else {
                        // indexFlag��false�ɧ��ܪ��Ϥ�
                        label_1.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/1.jpg"));
                    }
                }
                if (x2 == -98) { // ���2�Ӽ��Ҫ���ܦ�m�O-98�ɰ���
                    indexFlag = !indexFlag; // ����indexFlag����
                    x2 = 392; // �]�w��2�Ӽ��Ҫ���ܦ�m
                    if (indexFlag) {
                        // indexFlag��true�ɧ��ܪ��Ϥ�
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/7.jpg"));
                    } else {
                        // indexFlag��false�ɧ��ܪ��Ϥ�
                        label_2.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/2.jpg"));
                    }
                }
                if (x3 == -98) { // ���3�Ӽ��Ҫ���ܦ�m�O-98�ɰ���
                    indexFlag = !indexFlag; // ����indexFlag����
                    x3 = 392; // �]�w��3�Ӽ��Ҫ���ܦ�m
                    if (indexFlag) {
                        // indexFlag��true�ɧ��ܪ��Ϥ�
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/8.jpg"));
                    } else {
                        // indexFlag��false�ɧ��ܪ��Ϥ�
                        label_3.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/3.jpg"));
                    }
                }
                if (x4 == -98) { // ���4�Ӽ��Ҫ���ܦ�m�O-98�ɰ���
                    indexFlag = !indexFlag; // ����indexFlag����
                    x4 = 392; // �]�w��4�Ӽ��Ҫ���ܦ�m
                    if (indexFlag) {
                        // indexFlag��true�ɧ��ܪ��Ϥ�
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/9.jpg"));
                    } else {
                        // indexFlag��false�ɧ��ܪ��Ϥ�
                        label_4.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/4.jpg"));
                    }
                }
                if (x5 == -98) { // ���5�Ӽ��Ҫ���ܦ�m�O-98�ɰ���
                    indexFlag = !indexFlag; // ����indexFlag����
                    x5 = 392; // �]�w��5�Ӽ��Ҫ���ܦ�m
                    if (indexFlag) {
                        // indexFlag��true�ɧ��ܪ��Ϥ�
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/10.jpg"));
                    } else {
                        // indexFlag��false�ɧ��ܪ��Ϥ�
                        label_5.setIcon(SwingResourceManager.getIcon(
                                CinefilmEffectFrame.class, "/image/5.jpg"));
                    }
                }
                try {
                    Thread.sleep(150); // �u�{�ίv150�@��
                } catch (Exception ex) {
                    
                }
            }
        }
    }
}
