package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 6760471507923160452L;
    private JTextField codeText;
    private JPasswordField pwdText;
    private JTextField nameText;
    DisturbCodePanel imageCode = null;
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public MainFrame() {
        super();
        setResizable(false);
        setTitle("�a�z�Z�u�����ҽX");
        setBounds(100, 100, 426, 210);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageCode = new DisturbCodePanel();// �إ����O�����
        imageCode.setBounds(170, 85, 106, 35);// �]�w��m
        getContentPane().add(imageCode); // �W�[���ҽX
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (imageCode != null) {
                    imageCode.draw(); // �I�s��k�������ҽX
                }
            }
        });
        button.setText("���@�i");
        button.setBounds(301, 90, 94, 28);
        panel.add(button);
        
        final JLabel label = new JLabel();
        label.setText("�ϥΪ̦W�١G");
        label.setBounds(29, 25, 66, 18);
        panel.add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("�K   �X�G");
        label_1.setBounds(29, 59, 66, 18);
        panel.add(label_1);
        
        nameText = new JTextField();
        nameText.setBounds(85, 23, 310, 22);
        panel.add(nameText);
        
        pwdText = new JPasswordField();
        pwdText.setBounds(85, 57, 310, 22);
        panel.add(pwdText);
        
        final JLabel label_1_1 = new JLabel();
        label_1_1.setText("���ҽX�G");
        label_1_1.setBounds(29, 95, 66, 18);
        panel.add(label_1_1);
        
        codeText = new JTextField();
        codeText.setBounds(85, 93, 77, 22);
        panel.add(codeText);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String username = nameText.getText();// �q�¤�r�ؤ���o�ϥΪ̦W��
                String password = new String(pwdText.getPassword());// �q�K�X�ؤ���o�K�X
                String code = codeText.getText();// ��o��J�����ҽX
                String info = "";// �ϥΪ̵n�J��T
                // �P�_�ϥΪ̦W�٬O�_��null�ΪŪ��r��
                if (username == null || username.isEmpty()) {
                    info = "�ϥΪ̦W�٬��šI";
                }
                // �P�_�K�X�O�_��null�ΪŪ��r��
                else if (password == null || password.isEmpty()) {
                    info = "�K�X���šI";
                }
                // �P�_���ҽX�O�_��null�ΪŪ��r��
                else if (code == null || code.isEmpty()) {
                    info = "���ҽX���šI";
                }
                // �P�_ ���ҽX�O�_���T
                else if (!code.equals(imageCode.getNum())) {
                    info = "���ҽX���~�I";
                }
                // �p�G�ϥΪ̦W�ٻP�K�X����"mrsoft"�A�h�n�J���\
                else if (username.equals("mrsoft") && password.equals("mrsoft")) {
                    info = "���ߡA�n�J���\";
                } else {
                    info = "�ϥΪ̦W�٩αK�X���~�I";
                }
                JOptionPane.showMessageDialog(null, info);// �z�L��͵����X�{�ϥΪ̵n�J��T
            }
        });
        button_1.setText("�n  ��");
        button_1.setBounds(42, 134, 106, 28);
        panel.add(button_1);
        
        final JButton button_1_1 = new JButton();
        button_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                nameText.setText("");// �M���ϥΪ̦W�ٯ¤�r�ؤ��e
                pwdText.setText("");// �M���K�X�¤�r�ؤ��e
                codeText.setText("");// �M�����ҽX�¤�r�ؤ��e
            }
        });
        button_1_1.setText("��  �m");
        button_1_1.setBounds(191, 134, 106, 28);
        panel.add(button_1_1);
    }
    
}