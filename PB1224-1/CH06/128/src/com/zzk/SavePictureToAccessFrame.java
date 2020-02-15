package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SavePictureToAccessFrame extends JFrame {
    private static final long serialVersionUID = 8339759858493972497L;
    private JTextField tf_name;
    private String picturePath = null;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SavePictureToAccessFrame frame = new SavePictureToAccessFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public SavePictureToAccessFrame() {
        super();
        setTitle("�Ϥ��x�s��Access��ƨ�Ʈw��");
        setBounds(100, 100, 369, 248);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JLabel label = new JLabel();
        label.setFont(new Font("����", Font.BOLD, 22));
        label.setBounds(26, 12, 302, 41);
        label.setText("�N�Ϥ��x�s��Access��ƨ�Ʈw��");
        panel.add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("�m    �W�G");
        label_1.setBounds(26, 59, 66, 18);
        panel.add(label_1);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("��    ���G");
        label_2.setBounds(26, 130, 66, 18);
        panel.add(label_2);
        
        tf_name = new JTextField();
        tf_name.setBounds(83, 57, 245, 22);
        panel.add(tf_name);
        
        final JLabel lb_picture = new JLabel();
        lb_picture.setOpaque(true);
        lb_picture.setBackground(Color.PINK);
        lb_picture.setBounds(83, 83, 139, 117);
        panel.add(lb_picture);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(); // �إ��ɮץ�͵���
                // �إ��ɮ׹L�o
                FileFilter filter = new FileNameExtensionFilter(
                        "�ϧ��ɮ�(*.gif;*.jpg;*.jpeg;*.png)", "gif", "jpg", "jpeg",
                        "png");
                fileChooser.setFileFilter(filter); // ���ɮץ�͵����]�w�ɮ׹L�o��
                int returnValue = fileChooser.showOpenDialog(null);// �}���ɮ׿�ܥ�͵���
                if (returnValue == JFileChooser.APPROVE_OPTION) { // �P�_�O�_��ܤF�ɮ�
                    File file = fileChooser.getSelectedFile(); // ��o�ɮ׹ﹳ
                    if (file.length() / 1024.0 > 50.0) {
                        JOptionPane.showMessageDialog(null, "�п�ܤp�󵥩�50KB���Ϥ��ɮסC");
                        return;
                    }
                    picturePath = file.getAbsolutePath();
                    Icon icon = new ImageIcon(picturePath);
                    Dimension size = lb_picture.getSize();
                    lb_picture.setIcon(icon);
                    lb_picture.setSize(size);
                }
            }
        });
        button_2.setText("��ܹϤ�");
        button_2.setBounds(231, 85, 97, 28);
        panel.add(button_2);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String name = tf_name.getText().trim();
                if (picturePath == null || picturePath.equals("")) {
                    JOptionPane.showMessageDialog(null, "�п�ܹϤ��ɮסC");
                    return;
                }
                File file = new File(picturePath);
                try {
                    FileInputStream in = new FileInputStream(file);
                    Connection conn = DAO.getConn();
                    String sql = "insert into tb_picture (name,picture) values(?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);// �إ�PreparedStatement��H�A�öǻ�SQL�ԭz
                    ps.setString(1, name); // ����1�ӰѼƵ�����
                    ps.setBinaryStream(2, in, (int) file.length());// ����2�ӰѼƵ�����
                    int flag = ps.executeUpdate(); // ����SQL�ԭz�A��o��s�O����
                    if (flag > 0) {
                        JOptionPane.showMessageDialog(null, "�x�s���\�I");
                    } else {
                        JOptionPane.showMessageDialog(null, "�x�s���ѡI");
                    }
                    ps.close();// ����PreparedStatement�ﹳ
                    conn.close(); // �����s��
                    if (in != null) {
                        in.close();// ����IO�y
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        });
        button.setBounds(231, 125, 97, 28);
        panel.add(button);
        button.setText("�O    �s");
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setBounds(231, 167, 97, 28);
        panel.add(button_1);
        button_1.setText("�h    �X");
        //
    }
    
}
