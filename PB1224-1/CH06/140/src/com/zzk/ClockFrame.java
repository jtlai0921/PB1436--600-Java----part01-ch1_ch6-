package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.awt.AWTUtilities;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class ClockFrame extends JDialog {
    private float opqua = 0.7f;
    private ClockPanel clockPanel;
    private Point fp; // �즲���餧�e�����Ц�m
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClockFrame frame = new ClockFrame();// �إߵ���ﹳ
                    frame.setVisible(true);// ��ܵ���
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * �G�����骺�غc��k
     */
    public ClockFrame() {
        super();
        setUndecorated(true);// ��������׹�
        setAlwaysOnTop(true);// ����m��
        setTitle("�����ޥۭ^��");// �]�w������D
        getContentPane().setLayout(new BorderLayout());
        setBounds(100, 30, 217, 257);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        clockPanel = new ClockPanel();// �إ߮������O
        getContentPane().add(clockPanel);
        // ���������O�W�[���Ы���ƥ��ť��
        clockPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {
                fp = e.getPoint();
                if (e.getButton()==MouseEvent.BUTTON3){
                    System.exit(0);// �k��h�X
                }
            }
        });
        // �b�������O�����Щ즲�ƥ󤤲��ʵ���
        clockPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                JDialog frame = (JDialog) getRootPane().getParent();
                Point point = e.getLocationOnScreen();
                frame.setLocation(point.x - fp.x, point.y - fp.y);
            }
        });
        pack();
        
        addKeyListener(new KeyAdapter() {// ������W�[��Шƥ��ť��
            public void keyPressed(final KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {// �P�_����g�{��
                    case KeyEvent.VK_ADD:// +�Ÿ�����|���C�z����
                        opqua += 0.05;
                        opqua = opqua > 0.95f ? 1f : opqua;
                        break;
                    case KeyEvent.VK_SUBTRACT:// -�Ÿ�����|���ɳz����
                        opqua -= 0.05;
                        opqua = opqua < 0.1f ? 0.1f : opqua;
                        break;
                }
                // �p�G��Ctrl+Shift+X�զX��A�N�h�X�{��
                if (code == KeyEvent.VK_X
                        && e.getModifiersEx() == (KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK))
                    System.exit(0);
                AWTUtilities.setWindowOpacity(ClockFrame.this, opqua);// ��s����z����
            }
        });
        
        AWTUtilities.setWindowOpacity(this, opqua);
        Dimension screenSize = getToolkit().getScreenSize();
        double width = screenSize.getWidth();
        int x = (int) (width - getWidth() - 30);
        setLocation(x, 30);
        
        new Thread() {// �إ߽u�{��H�A��s�������O�ɭ�
            @Override
            public void run() {
                try {
                    while (true) {
                        sleep(1000);// ��v1��
                        clockPanel.repaint();// ���sø�s�������O�ɭ�
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
