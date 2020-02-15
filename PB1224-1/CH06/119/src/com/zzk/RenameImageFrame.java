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
    private String path = null;// �Ϥ������|
    private String fileName = null;// ��Ϥ����ɮצW
    private DrawImagePanel imagePanel = null; // �ŧi�ϧέ��O�ﹳ
    
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
        setTitle("�ק�Ϥ��ɮצW"); // �]�w������D
        setBounds(200, 160, 391, 288); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imagePanel = new DrawImagePanel(); // �إ߹ϧέ��O�ﹳ
        add(imagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("��ܹϤ��G");
        panel.add(label_1);
        
        tf_fileName = new JTextField();
        tf_fileName.setPreferredSize(new Dimension(200, 25));
        panel.add(tf_fileName);
        
        final JButton button_2 = new JButton();
        panel.add(button_2);
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �إ��ɮ׿�ܾ�
                FileFilter filter = new FileNameExtensionFilter(
                        "�ϧ��ɮס]JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// �إ߹L�o��
                fileChooser.setFileFilter(filter);// �]�w�L�o��
                int i = fileChooser.showOpenDialog(null);// ��ܶ}�ҥ�͵���
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��o�Ŀ�Ϥ���File�ﹳ
                    path = imgFile.getParent();// ��o�Ϥ������|
                    fileName = imgFile.getName();// ��o��Ϥ��ɮצW
                    tf_fileName.setText(path + "\\" + fileName);// �b�¤�r�ؤ���ܹϤ���������|
                }
                if (imgFile != null) {
                    try {
                        src = ImageIO.read(imgFile);// �غcBufferedImage�ﹳ
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imagePanel.repaint();// �I�spaint��k
            }
        });
        button_2.setText("��ܹϤ�");
        
        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);
        
        final JLabel label = new JLabel();
        label.setText("��J�s�ɮצW�G");
        panel_1.add(label);
        
        tf_newFileName = new JTextField();
        tf_newFileName.setPreferredSize(new Dimension(160, 25));
        panel_1.add(tf_newFileName);
        
        final JButton button = new JButton();
        panel_1.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (path != null && fileName != null) {
                    String newName = tf_newFileName.getText().trim();// ��o�s��J���ɮצW
                    if (newName.indexOf(":") >= 0 || newName.indexOf("\\") >= 0) {// �P�_�ɮצW�O�_�]�t���|
                        JOptionPane.showMessageDialog(null, "�Ъ�����J�s�ɮצW�C\n���ݭn���w���|�C");
                        return;
                    } else if (newName.equals("")) {// �p�G�S����J�ɮצW�h�i�洣��
                        JOptionPane.showMessageDialog(null, "�п�J�s�ɮצW�C");
                        return;
                    } else {
                        if (newName.indexOf(".") > 0) {
                            imgFile.renameTo(new File(path + "\\" + newName));// �s�ɮצW�a�X�R�W
                        } else {
                            imgFile.renameTo(new File(path + "\\" + newName + fileName.substring(fileName.indexOf("."))));// �s�ɮצW���a�X�R�W
                        }
                    }
                    JOptionPane.showMessageDialog(null, "�ק令�\�C");
                }
            }
        });
        button.setText("���R�W�Ϥ�");
    }
    
    // �إ߭��O���O
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(src, 0, 0, getWidth(), getHeight(), this); // ø�s���w���Ϥ�
        }
    }
}
