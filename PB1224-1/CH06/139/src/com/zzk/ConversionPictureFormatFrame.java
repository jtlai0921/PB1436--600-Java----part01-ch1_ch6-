package com.zzk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class ConversionPictureFormatFrame extends JFrame {
    
    private JComboBox comboBox;
    private JTextField tf_pathAndFileName;
    private File imgFile = null;
    private BufferedImage buffImage;
    private DrawImagePanel imagePanel = null;
    private String path = null;
    private String fileName = null;
    private String pathAndFileName = null;
    /**
     * Launch the application
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConversionPictureFormatFrame frame = new ConversionPictureFormatFrame();
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
    public ConversionPictureFormatFrame() {
        super();
        setTitle("�ഫ�Ϥ��榡");
        setBounds(100, 100, 432, 315);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        
        imagePanel = new DrawImagePanel();
        getContentPane().add(imagePanel, BorderLayout.CENTER);

        final JLabel label = new JLabel();
        label.setText("�п�ܭ�Ϥ��G");
        panel.add(label);

        tf_pathAndFileName = new JTextField();
        tf_pathAndFileName.setPreferredSize(new Dimension(200,25));
        panel.add(tf_pathAndFileName);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �إ��ɮ׿�ܾ�
                FileFilter filter = new FileNameExtensionFilter(
                        "�ϧ��ɮס]JPG/GIF/png/bmp)", "JPG", "JPEG", "GIF", "png", "bmp");// �إ߹L�o��
                fileChooser.setFileFilter(filter);// �]�w�L�o��
                int i = fileChooser.showOpenDialog(null);// ��ܶ}�ҥ�͵���
                if (i == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��o�Ŀ�Ϥ���File�ﹳ
                }
                if (imgFile != null) {
                    try {
                        buffImage = ImageIO.read(imgFile);// �غcBufferedImage�ﹳ
                        path = imgFile.getParent();
                        fileName = imgFile.getName();
                        pathAndFileName = path + "\\" + fileName;
                        tf_pathAndFileName.setText(pathAndFileName);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imagePanel.repaint();// �I�spaint()��k
            }
        });
        button.setText("��ܹϤ�");
        panel.add(button);

        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        final JLabel label_1 = new JLabel();
        label_1.setText("�п���ഫ�᪺�Ϥ��榡�G");
        panel_1.add(label_1);

        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(130,25));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"jpg", "gif", "png", "bmp"}));
        panel_1.add(comboBox);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    String extName = (String)comboBox.getSelectedItem();// �s�榡���X�R�W�A���t�I
                    String pathAndName = pathAndFileName.substring(0, pathAndFileName.lastIndexOf(".") + 1)+extName;// �ഫ�᪺�Ϥ�������|�M�ɮצW
                    File file = new File(pathAndName);// �إ��ഫ��Ϥ���File�ﹳ
                    ImageIO.write(buffImage, extName, file);// �N�w�Ĺϧ��x�s��Ϻ�
                    File oldFile = new File(pathAndFileName);// ��Ϥ���File�ﹳ
                    oldFile.delete();// �R����Ϥ��ɮ�
                    JOptionPane.showMessageDialog(null, "�ɮ׮榡��令�\�I");// ��ܴ��ܸ�T
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "�x�s����\n" + e1.getMessage());// ��ܴ��ܸ�T
                }
            }
        });
        button_1.setText("�O    �s");
        panel_1.add(button_1);
        
    }
    // �إ߭��O���O
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());
            g.drawImage(buffImage, 0, 0, this); // ø�s���w���Ϥ�
        }
    }
}
