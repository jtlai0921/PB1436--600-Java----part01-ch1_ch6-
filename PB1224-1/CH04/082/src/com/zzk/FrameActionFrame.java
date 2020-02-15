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
    boolean flag = true; // �Ω�лx�ʵe����B�Ȱ��M�~�򪺦����ܼ�
    boolean stop = false; // �Ω�лx�ʵe����M��������ܼ�
    final JLabel label = new JLabel(); // ��ܹϤ�������
    
    public FrameActionFrame() {
        super();
        addWindowListener(new WindowAdapter() {
            public void windowOpened(final WindowEvent arg0) {
                thread.start(); // �Ұʽu�{�A�b����}�Үɼ���ʵe
            }
        });
        setTitle("�ذʵe�ĪG");
        setBounds(260, 240, 324, 227); // �Ϥ����e�שM����392,208
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(label, BorderLayout.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);// ���Ҥ��e�����~��
        label.setIcon(SwingResourceManager.getIcon(FrameActionFrame.class,
                "/image/1.gif"));
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (thread == null) {
                    thread = new Thread(new ActionThread()); // �p�G�u�{���šA�h�إ߽u�{�ﹳ
                }
                if (!thread.isAlive()) {
                    // �p�G�u�{���O���ʽu�{�h����U�����{���X�A�Ұʽu�{������
                    stop = false;
                    flag = true;
                    thread.start();
                }
            }
        });
        button.setText("��  ��");
        panel.add(button);
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = false; // �Ȱ��ʵe����
            }
        });
        button_1.setText("��  ��");
        panel.add(button_1);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                flag = true; // �~�򼽩�ʵe
            }
        });
        button_3.setText("�~  ��");
        panel.add(button_3);
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                stop = true; // �����ʵe
            }
        });
        button_2.setText("��  ��");
        panel.add(button_2);
    }
    
    public static void main(String[] args) {
        FrameActionFrame frame = new FrameActionFrame();
        frame.setVisible(true);
    }
    
    /**
     * @author �i���[
     *         �Ω��{�ʵe���u�{
     */
    private class ActionThread implements Runnable {
        private int index = 1; // �Ω󱱨�ϧ��ɮת��D�ɮצW
        public void run() {
            while (true) {
                if (stop) {
                    thread = null; // �P���u�{
                    break; // ���X�`���A�����u�{������
                }
                if (flag) {
                    String picture = "/image/"; // �إ߹Ϥ��s���m�M�ɮצW���ܼ�
                    index++;
                    if (index <= 8) {
                        picture = picture + index + ".jpg"; // �z�L������o�Ϥ�����m�M�ɮצW
                    } else {
                        index = 1;
                        picture = picture + index + ".jpg"; // �z�L������o�Ϥ�����m�M�ɮצW
                    }
                    // ���ܼ��ҤW��ܪ��Ϥ���{�ʵe�ĪG
                    label.setIcon(SwingResourceManager.getIcon(
                            FrameActionFrame.class, picture));
                    try {
                        Thread.sleep(200); // �u�{�ίv200�@��
                    } catch (Exception ex) {
                        
                    }
                }
            }
        }
    }
}
