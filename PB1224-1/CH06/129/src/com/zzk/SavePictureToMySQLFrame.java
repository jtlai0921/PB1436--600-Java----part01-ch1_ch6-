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

public class SavePictureToMySQLFrame extends JFrame {
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
                    SavePictureToMySQLFrame frame = new SavePictureToMySQLFrame();
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
    public SavePictureToMySQLFrame() {
        super();
        setTitle("圖片儲存到MySQL資料函數庫中");
        setBounds(100, 100, 369, 248);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        final JLabel label = new JLabel();
        label.setFont(new Font("黑體", Font.BOLD, 22));
        label.setBounds(26, 12, 302, 41);
        label.setText("將圖片儲存到MySQL資料函數庫中");
        panel.add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("姓    名：");
        label_1.setBounds(26, 59, 66, 18);
        panel.add(label_1);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("圖    片：");
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
                JFileChooser fileChooser = new JFileChooser(); // 建立檔案交談視窗
                // 建立檔案過濾
                FileFilter filter = new FileNameExtensionFilter(
                        "圖形檔案(*.gif;*.jpg;*.jpeg;*.png)", "gif", "jpg", "jpeg",
                        "png");
                fileChooser.setFileFilter(filter); // 為檔案交談視窗設定檔案過濾器
                int returnValue = fileChooser.showOpenDialog(null);// 開啟檔案選擇交談視窗
                if (returnValue == JFileChooser.APPROVE_OPTION) { // 判斷是否選擇了檔案
                    File file = fileChooser.getSelectedFile(); // 獲得檔案對像
                    if (file.length() / 1024.0 > 50.0) {
                        JOptionPane
                                .showMessageDialog(null, "請選擇小於等於50KB的圖片檔案。");
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
        button_2.setText("選擇圖片");
        button_2.setBounds(231, 85, 97, 28);
        panel.add(button_2);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String name = tf_name.getText().trim();
                if (picturePath == null || picturePath.equals("")) {
                    JOptionPane.showMessageDialog(null, "請選擇圖片檔案。");
                    return;
                }
                File file = new File(picturePath);
                try {
                    FileInputStream in = new FileInputStream(file);
                    Connection conn = DAO.getConn();
                    String sql = "insert into tb_picture (name,picture) values(?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);// 建立PreparedStatement對象，並傳遞SQL敘述
                    ps.setString(1, name); // 為第1個參數給予值
                    ps.setBinaryStream(2, in, (int) file.length());// 為第2個參數給予值
                    int flag = ps.executeUpdate(); // 執行SQL敘述，獲得更新記錄數
                    if (flag > 0) {
                        JOptionPane.showMessageDialog(null, "儲存成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "儲存失敗！");
                    }
                    ps.close();// 關閉PreparedStatement對像
                    conn.close(); // 關閉連接
                    if (in != null) {
                        in.close();// 關閉IO流
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
        button.setText("保    存");
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setBounds(231, 167, 97, 28);
        panel.add(button_1);
        button_1.setText("退    出");
    }
}
