package com.zzk;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author �i���[
 */
public class PigLabel extends JLabel implements Runnable {
    // �H�����ͥ�v�ɶ��A�Y���޲��ʳt��
    private int sleepTime = (int) (Math.random() * 300) + 30;
    private int y = 260;// ����������y��
    private int score = 10;// �Ө������������
    private Thread thread;// ���ؽu�{�ﹳ
    private Container parent;// ��������e���ﹳ
    
    /**
     * �غc��k
     */
    public PigLabel() {
        super();
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "pig.gif"));// ���J���޹Ϥ�
        setIcon(icon);// �]�w�����󪺹ϼ�
        // �W�[���Шƥ󤶭��d
        addMouseListener(new MouseAdapter() {
            // ���U���Ы��䪺�B�z��k
            public void mousePressed(final MouseEvent e) {
                if (!MainFrame.readyAmmo())
                    return;
                MainFrame.useAmmo();// ���Ӥl�u
                appScore();// ���C���[��
                destory();// �P��������
            }
        });
        // �W�[����ƥ󤶭��d
        addComponentListener(new ComponentAdapter() {
            // �վ㤸��j�p��
            public void componentResized(final ComponentEvent e) {
                thread.start();// �Ұʽu�{
            }
        });
        // ��l�ƽu�{�ﹳ
        thread = new Thread(this);
    }
    
    @Override
    public void run() {
        parent = null;
        int width = 0;
        while (width <= 0 || parent == null) {// ��o���e���e��
            if (parent == null)
                parent = getParent();
            else
                width = parent.getWidth();
        }
        // �q���V�k���ʥ�����
        for (int i = 0; i < width && parent != null; i += 8) {
            setLocation(i, y);
            try {
                Thread.sleep(sleepTime);// ��v����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (parent != null) {
            MainFrame.appScore(-score * 10); // �۵M�P���N����
        }
        destory();
    }
    
    /**
     * �q�e�����������󪺤�k
     */
    public void destory() {
        if (parent == null)
            return;
        parent.remove(this);
        parent.repaint();
        parent = null; // �z�L�ӱԭz�פ�u�{�`��
    }
    
    /**
     * �[������k
     */
    private void appScore() {
        MainFrame.appScore(10);
    }
}
