package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReadPictureFromSQLServerFrame extends JFrame {
    private static final long serialVersionUID = 8339759858493972497L;
    private JTextField tf_id;
    private JTextField tf_name;
    private JLabel lb_picture = null;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReadPictureFromSQLServerFrame frame = new ReadPictureFromSQLServerFrame();
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
    public ReadPictureFromSQLServerFrame() {
        super();
        setTitle("Ū��SQLServer��ƨ�Ʈw���x�s���Ϥ�");
        setBounds(100, 100, 369, 271);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("�s    ���G");
        label_1.setBounds(26, 26, 66, 18);
        panel.add(label_1);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("��    ���G");
        label_2.setBounds(26, 130, 66, 18);
        panel.add(label_2);
        
        tf_id = new JTextField();
        tf_id.setText("1");
        tf_id.setBounds(83, 24, 122, 22);
        panel.add(tf_id);
        
        lb_picture = new JLabel();
        lb_picture.setOpaque(true);
        lb_picture.setBackground(Color.PINK);
        lb_picture.setBounds(83, 83, 245, 140);
        panel.add(lb_picture);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int id = Integer.parseInt(tf_id.getText().trim());
                Connection conn = DAO.getConn();
                String sql = "select name,picture from tb_picture where id = ?";
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    ps = conn.prepareStatement(sql);// �إ�PreparedStatement��H�A�öǻ�SQL�ԭz
                    ps.setInt(1, id); // ���ѼƵ�����
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        String name = rs.getString(1);// ��o�m�W
                        tf_name.setText(name);// �b�¤�r�ؤ���ܩm�W
                        Blob img = (Blob) rs.getBlob(2);// ��o�Ϥ���Blob�ﹳ
                        Icon icon = new ImageIcon(img.getBytes(1, (int) img
                                .length()));// �إ߹ϼйﹳ
                        Dimension size = lb_picture.getSize();// ��o��ܹϼЪ����Ҥj�p
                        lb_picture.setIcon(icon);// �����ҫ��w�ϼ�
                        lb_picture.setSize(size);// �]�w���Ҫ��j�p
                    }
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setBounds(211, 21, 117, 28);
        panel.add(button);
        button.setText("���s���d��");
        
        final JLabel label = new JLabel();
        label.setText("�m    �W�G");
        label.setBounds(26, 59, 66, 18);
        panel.add(label);
        
        tf_name = new JTextField();
        tf_name.setBounds(83, 55, 245, 22);
        panel.add(tf_name);
    }
}
