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
        setTitle("帶干擾線的驗證碼");
        setBounds(100, 100, 426, 210);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageCode = new DisturbCodePanel();// 建立類別的實例
        imageCode.setBounds(170, 85, 106, 35);// 設定位置
        getContentPane().add(imageCode); // 增加驗證碼
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (imageCode != null) {
                    imageCode.draw(); // 呼叫方法產生驗證碼
                }
            }
        });
        button.setText("換一張");
        button.setBounds(301, 90, 94, 28);
        panel.add(button);
        
        final JLabel label = new JLabel();
        label.setText("使用者名稱：");
        label.setBounds(29, 25, 66, 18);
        panel.add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("密   碼：");
        label_1.setBounds(29, 59, 66, 18);
        panel.add(label_1);
        
        nameText = new JTextField();
        nameText.setBounds(85, 23, 310, 22);
        panel.add(nameText);
        
        pwdText = new JPasswordField();
        pwdText.setBounds(85, 57, 310, 22);
        panel.add(pwdText);
        
        final JLabel label_1_1 = new JLabel();
        label_1_1.setText("驗證碼：");
        label_1_1.setBounds(29, 95, 66, 18);
        panel.add(label_1_1);
        
        codeText = new JTextField();
        codeText.setBounds(85, 93, 77, 22);
        panel.add(codeText);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String username = nameText.getText();// 從純文字框中獲得使用者名稱
                String password = new String(pwdText.getPassword());// 從密碼框中獲得密碼
                String code = codeText.getText();// 獲得輸入的驗證碼
                String info = "";// 使用者登入資訊
                // 判斷使用者名稱是否為null或空的字串
                if (username == null || username.isEmpty()) {
                    info = "使用者名稱為空！";
                }
                // 判斷密碼是否為null或空的字串
                else if (password == null || password.isEmpty()) {
                    info = "密碼為空！";
                }
                // 判斷驗證碼是否為null或空的字串
                else if (code == null || code.isEmpty()) {
                    info = "驗證碼為空！";
                }
                // 判斷 驗證碼是否正確
                else if (!code.equals(imageCode.getNum())) {
                    info = "驗證碼錯誤！";
                }
                // 如果使用者名稱與密碼均為"mrsoft"，則登入成功
                else if (username.equals("mrsoft") && password.equals("mrsoft")) {
                    info = "恭喜，登入成功";
                } else {
                    info = "使用者名稱或密碼錯誤！";
                }
                JOptionPane.showMessageDialog(null, info);// 透過交談視窗出現使用者登入資訊
            }
        });
        button_1.setText("登  錄");
        button_1.setBounds(42, 134, 106, 28);
        panel.add(button_1);
        
        final JButton button_1_1 = new JButton();
        button_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                nameText.setText("");// 清除使用者名稱純文字框內容
                pwdText.setText("");// 清除密碼純文字框內容
                codeText.setText("");// 清除驗證碼純文字框內容
            }
        });
        button_1_1.setText("重  置");
        button_1_1.setBounds(191, 134, 106, 28);
        panel.add(button_1_1);
    }
    
}