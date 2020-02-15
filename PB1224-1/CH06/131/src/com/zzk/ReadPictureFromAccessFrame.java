package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ReadPictureFromAccessFrame extends JFrame {
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
                    ReadPictureFromAccessFrame frame = new ReadPictureFromAccessFrame();
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
    public ReadPictureFromAccessFrame() {
        super();
        setTitle("讀取Access資料函數庫中儲存的圖片");
        setBounds(100, 100, 369, 271);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("編    號：");
        label_1.setBounds(26, 26, 66, 18);
        panel.add(label_1);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("照    片：");
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
                    ps = conn.prepareStatement(sql);// 建立PreparedStatement對象，並傳遞SQL敘述
                    ps.setInt(1, id); // 為參數給予值
                    rs = ps.executeQuery();// 執行SQL敘述
                    if (rs.next()) {
                        String name = rs.getString(1);// 獲得姓名
                        tf_name.setText(name);// 在純文字框中顯示姓名
                        byte[] bt = rs.getBytes(2);// 獲得表中照片的字節陣列
                        Icon icon = new ImageIcon(bt);// 建立圖標對像
                        Dimension size = lb_picture.getSize();// 獲得顯示圖標的標籤大小
                        lb_picture.setIcon(icon);// 為標籤指定圖標
                        lb_picture.setSize(size);// 設定標籤的大小
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
        button.setText("按編號查詢");
        
        final JLabel label = new JLabel();
        label.setText("姓    名：");
        label.setBounds(26, 59, 66, 18);
        panel.add(label);
        
        tf_name = new JTextField();
        tf_name.setBounds(83, 55, 245, 22);
        panel.add(tf_name);
    }
}
