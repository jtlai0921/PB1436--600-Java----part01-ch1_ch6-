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
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BrowerPictureFrame extends JFrame {
    private static final long serialVersionUID = 2568309165342527300L;
    private JTextField tf_path;
    private BufferedImage img=null;
    private File imgFile = null;
    private DrawImagePanel imgPanel = null;
    private String filePath = null;
    private String currentFileName = null;
    private int currentFileIndex = 0;
    private List<String> fileNameList = new ArrayList<String>();
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BrowerPictureFrame frame = new BrowerPictureFrame();
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
    public BrowerPictureFrame() {
        super();
        setTitle("�Ϥ��s����");
        setBounds(100, 100, 457, 328);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgPanel = new DrawImagePanel();
        final JPanel panel = new JPanel();
        getContentPane().add(imgPanel, BorderLayout.CENTER);
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.NORTH);

        final JLabel label = new JLabel();
        label.setText("����ɮסG");
        panel.add(label);

        tf_path = new JTextField();
        tf_path.setPreferredSize(new Dimension(280,25));
        panel.add(tf_path);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();// �إ��ɮ׿�ܾ�
                FileFilter filter = new FileNameExtensionFilter(
                        "�ϧ��ɮס]JPG/GIF/BMP)", "JPG", "JPEG", "GIF", "BMP");// �إ߹L�o��
                fileChooser.setFileFilter(filter);// �]�w�L�o��
                int flag = fileChooser.showOpenDialog(null);// ��ܶ}�ҥ�͵���
                if (flag == JFileChooser.APPROVE_OPTION) {
                    imgFile = fileChooser.getSelectedFile(); // ��o�Ŀ�Ϥ���File�ﹳ
                }
                if (imgFile != null) {
                    try {
                        img = ImageIO.read(imgFile);// �غcBufferedImage�ﹳ
                        filePath = imgFile.getParent();// ��o�ɮת����|
                        tf_path.setText(filePath);// �b�¤�r�ؤ���ܸ��|
                        currentFileName = imgFile.getName();// ��o��ܪ��ɮצW�A�����ȵ��x�s�ثe�ɮת��ܼ�
                        final String extName = currentFileName.substring(currentFileName.lastIndexOf("."));// ��o�ɮת��X�R�W
                        File pathFile = new File(filePath);// �إ߸��|��File�ﹳ
                        String[] fileNames = pathFile.list(new FilenameFilter(){// ��o�����L�o�����ɮצW�}�C
                            @Override
                            public boolean accept(File dir, String name) {
                                return name.endsWith(extName);// �Ǧ^�����X�R�W���ɮצW
                            }
                        });
                        for (int i=0;i<fileNames.length;i++){// �ˬd�}�C�����ɮצW
                            if (fileNames[i].equals(currentFileName)){// �P�_�O�_���ثe�ɮ�
                                currentFileIndex = i;// �O�Хثe�ɮת����ޭ�
                            }
                            fileNameList.add(fileNames[i]);// �N�ɮ׼W�[�춰�X�C��
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                imgPanel.repaint();// �I�spaint()��k
            }
        });
        button.setText("��    ��");
        panel.add(button);

        final JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, BorderLayout.SOUTH);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex = 0;// �Ĥ@�i�Ϥ�������
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// �إߥثe���ޭȹ����Ϥ���File�ﹳ
                try {
                    img = ImageIO.read(imgFile);// �إߥثe�Ϥ����ϧιﹳ
                    imgPanel.repaint();// �I�spaint()��k
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_1.setText("�Ĥ@�i");
        panel_1.add(button_1);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex--;// �վ�ثe�Ϥ������ޭ�
                if (currentFileIndex < 0) {
                    currentFileIndex = fileNameList.size() - 1;// �ثe�Ϥ����ެ��̫�@�i�Ϥ�������
                }
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// �إߥثe���ޭȹ����Ϥ���File�ﹳ
                try {
                    img = ImageIO.read(imgFile);// �إߥثe�Ϥ����ϧιﹳ
                    imgPanel.repaint();// �I�spaint()��k�A��ܹϤ�
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_2.setText("�W�@�i");
        panel_1.add(button_2);

        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex++;// �վ�ثe�Ϥ������ޭ�
                if (currentFileIndex > fileNameList.size() - 1) {
                    currentFileIndex = 0;// �ثe�Ϥ����ެ��Ĥ@�i�Ϥ�������
                }
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// �إߥثe���ޭȹ����Ϥ���File�ﹳ
                try {
                    img = ImageIO.read(imgFile);// �إߥثe�Ϥ����ϧιﹳ
                    imgPanel.repaint();// �I�spaint()��k
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_3.setText("�U�@�i");
        panel_1.add(button_3);

        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                currentFileIndex = fileNameList.size() - 1;// �̫�@�i�Ϥ�������
                imgFile = new File(filePath+"/"+fileNameList.get(currentFileIndex).toString());// �إߥثe���ޭȹ����Ϥ���File�ﹳ
                try {
                    img = ImageIO.read(imgFile);// �إߥثe�Ϥ����ϧιﹳ
                    imgPanel.repaint();// �I�spaint()��k
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_4.setText("�̫�@�i");
        panel_1.add(button_4);
    }
    // �إ߭��O���O
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            if (img == null){
                return;
            }
            int imgW = img.getWidth(this);
            int imgH = img.getHeight(this);
            int panelW = getWidth();
            int panelH = getHeight();
            g.clearRect(0, 0, panelW, panelH);
            g.drawImage(img, 0, 0,imgW,imgH,this); // ø�s���w���Ϥ�
        }
    }
}
