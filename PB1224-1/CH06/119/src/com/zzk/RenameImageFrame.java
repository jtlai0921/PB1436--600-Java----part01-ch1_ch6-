package com.zzk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class RenameImageFrame extends JFrame {
    private JTextField tf_newFileName;
    private JTextField tf_fileName;
    private File imgFile = null;
    private BufferedImage src;
    private String path = null;// 圖片的路徑
    private String fileName = null;// 原圖片的檔案名
    private DrawImagePanel imagePanel = null; // 宣告圖形面板對像
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RenameImageFrame frame = new RenameImageFrame();
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
    public RenameImageFrame() {
        super();
        setTitle("修改圖片檔案名"); // 設定窗體標題
        setBounds(200, 160, 391, 288); // 設定窗體大小和位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imagePanel = new DrawImagePanel(); // 建立圖形面板對像
        add(imagePanel); // 在窗體上增加圖形面板對像
        
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("選擇圖片：");
        panel.add(label_1);
        
        tf_fileName = new JTextField();
        tf_fileName.setPreferredSize(new Dimension(200, 25));
        panel.add(tf_fileName);
        
        final JButton button_2 = new JButton();
        panel.add(button_2);
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// 建立檔案選擇器
                FileFilter filter = new FileNameExtensionFilter(
                        "圖形檔案（JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// 建立過濾器
                fileChooser.setFileFilter(filter);// 設定過濾器
                int i = fileChooser.showOpenDialog(null);// 顯示開啟交談視窗
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // 獲得勾選圖片的File對像
                    path = imgFile.getParent();// 獲得圖片的路徑
                    fileName = imgFile.getName();// 獲得原圖片檔案名
                    tf_fileName.setText(path + "\\" + fileName);// 在純文字框中顯示圖片的完整路徑
                }
                if (imgFile != null) {
                    try {
                        src = ImageIO.read(imgFile);// 建構BufferedImage對像
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imagePanel.repaint();// 呼叫paint方法
            }
        });
        button_2.setText("選擇圖片");
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("輸入新檔案名：");
        panel_1.add(label);
        
        tf_newFileName = new JTextField();
        tf_newFileName.setPreferredSize(new Dimension(160, 25));
        panel_1.add(tf_newFileName);
        
        final JButton button = new JButton();
        panel_1.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (path != null && fileName != null) {
                    String newName = tf_newFileName.getText().trim();// 獲得新輸入的檔案名
                    if (newName.indexOf(":") >= 0 || newName.indexOf("\\") >= 0) {// 判斷檔案名是否包含路徑
                        JOptionPane.showMessageDialog(null, "請直接輸入新檔案名。\n不需要指定路徑。");
                        return;
                    } else if (newName.equals("")) {// 如果沒有輸入檔案名則進行提示
                        JOptionPane.showMessageDialog(null, "請輸入新檔案名。");
                        return;
                    } else {
                        if (newName.indexOf(".") > 0) {
                            imgFile.renameTo(new File(path + "\\" + newName));// 新檔案名帶擴充名
                        } else {
                            imgFile.renameTo(new File(path + "\\" + newName + fileName.substring(fileName.indexOf("."))));// 新檔案名不帶擴充名
                        }
                    }
                    JOptionPane.showMessageDialog(null, "修改成功。");
                }
            }
        });
        button.setText("重命名圖片");
    }
    
    // 建立面板類別
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(src, 0, 0, getWidth(), getHeight(), this); // 繪製指定的圖片
        }
    }
}
