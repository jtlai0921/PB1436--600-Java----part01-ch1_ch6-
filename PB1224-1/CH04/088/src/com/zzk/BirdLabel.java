package com.zzk;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author �i���[
 */
public class BirdLabel extends JLabel implements Runnable {
    /**
     * ���������ƥ��ť��
     * @author �i���[
     */
    private final class ComponentAction extends ComponentAdapter {
        public void componentResized(final ComponentEvent e) {
            thread.start();// �u�{�Ұ�
        }
    }
    
    /**
     * ��������Шƥ��ť��
     * 
     * @author �i���[
     */
    private final class MouseAction extends MouseAdapter {
        public void mousePressed(final MouseEvent e) {
            if (!MainFrame.readyAmmo())// �p�G�l�u�S���ǳƦn
                return;// ����]����
            MainFrame.useAmmo();// ���Ӥl�u
            appScore();// �[��
            destory();// �P��������
        }
    }
    
    // �H�����ͽu�{����v�ɶ��A�Y����p�����ʳt��
    private int sleepTime = (int) (Math.random() * 300) + 5;
    private int y = 100;
    private Thread thread;// �N�u�{�@�������ܼ�
    private Container parent;
    private int score = 15;// �����O�������������
    
    /**
     * �غc��k
     */
    public BirdLabel() {
        super();
        // �إߤp���ϼйﹳ
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "bird.gif"));
        setIcon(icon);// �]�w����ϼ�
        addMouseListener(new MouseAction());// �W�[���Шƥ��ť��
        // �W�[����ƥ��ť��
        addComponentListener(new ComponentAction());
        thread = new Thread(this);// �إ߽u�{�ﹳ
    }
    
    @Override
    public void run() {
        parent = null;
        int width = 0;
        try {
            while (width <= 0 || parent == null) {
                if (parent == null){
                    parent = getParent();// ��o���e��
                } else {
                    width = parent.getWidth();// ��o���e�����e��
                }
                Thread.sleep(10);
            }
            for (int i = width; i > 0 && parent != null; i -= 8) {
                setLocation(i, y);// �q�k�V�����ʥ������m
                Thread.sleep(sleepTime);// ��v����
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (parent != null) {
            MainFrame.appScore(-score * 10); // �۵M�P���N����
        }
        destory();// ���ʧ����A�P��������
    }
    
    /**
     * �q�e�����������󪺤�k
     */
    public void destory() {
        if (parent == null)
            return;
        parent.remove(this);// �q���e�����������v��
        parent.repaint();
        parent = null; // �z�L�ӱԭz�פ�u�{�`��
    }
    
    /**
     * �[������k
     */
    private void appScore() {
        MainFrame.appScore(15);
    }
}
