package com.zzk;

import javax.swing.*;

/**
 * @author �i���[
 */
public class BallFrame extends JFrame {
    private JPanel panel = null;// �I�����O
    private BallPanel ballPanel = null;// ���鴣�Ѥ@�Ӥp�y
    
    public static void main(String[] args) {
        BallFrame thisClass = new BallFrame();
        thisClass.setVisible(true);
    }
    
    /**
     * �غc��k
     */
    public BallFrame() {
        super();
        setSize(320, 223);// �]�w����j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);// �T��վ㵡��j�p
        setTitle("�ୱ�u�y�ʵe");// �]�w������D�¤�r
        ballPanel = new BallPanel();// �إߤp�y
        ballPanel.setBounds(121, 67, 20, 20);// �]�w�p�y��m�P�j�p
        panel = (JPanel) getContentPane();// ��o���骺���e���O
        panel.setLayout(null);// ���餺�e���O�ϥ�null�G��
        panel.add(ballPanel, null);// �W�[�p�y�쵡�骺���e���O
    }
}
