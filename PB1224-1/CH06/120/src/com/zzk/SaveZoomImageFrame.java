package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveZoomImageFrame extends JFrame {
    private JButton button_3;
    private JButton button_2;
    private JButton button_1;
    private JButton button;
    private JPanel panel;
    private Image img = null; // �ŧi�ϧιﹳ
    private ZoomImagePanel imagePanel = null; // �ŧi�ϧέ��O�ﹳ
    private int imgWidth, imgHeight;// �Ω��x�s�Ϥ����e�שM����
    private int newW, newH;// �Ω��x�s�Ϥ��Y��᪺�e�שM����
    private float value = 50.0f;// ����ϧΤj�p���ܼ�
    
    public static void main(String args[]) {
        SaveZoomImageFrame frame = new SaveZoomImageFrame();
        frame.setVisible(true);
    }
    
    public SaveZoomImageFrame() {
        super();
        this.setTitle("�Y��Ϥ����x�s"); // �]�w������D
        URL imgUrl = SaveZoomImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imagePanel = new ZoomImagePanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 355, 253); // �]�w����j�p�M��m
        this.add(imagePanel); // �b���餤����m�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        getContentPane().add(getPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * @return
     */
    protected JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.add(getButton());
            panel.add(getButton_1());
            panel.add(getButton_2());
            panel.add(getButton_3());
        }
        return panel;
    }
    
    /**
     * @return
     */
    protected JButton getButton() {
        if (button == null) {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value += 5;// �վ�value���ȡA�Ω��j�Ϥ�
                    if (value >= 200.0f) {// �p�Gvalue���Ȥj�󵥩�200
                        value = 200.0f;// ��value���ȵ���200
                    }
                    imagePanel.repaint();// ���s�I�s���O���O��paint()��k
                }
            });
            button.setText("��  �j");
        }
        return button;
    }
    
    /**
     * @return
     */
    protected JButton getButton_1() {
        if (button_1 == null) {
            button_1 = new JButton();
            button_1.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    value -= 5;// �վ�value���ȡA�Ω��Y�p�Ϥ�
                    if (value <= 0.0f) {// �p�Gvalue���Ȥp�󵥩�0
                        value = 0.0f;// ��value���ȵ���0
                    }
                    imagePanel.repaint();// ���s�I�s���O���O��paint()��k
                }
            });
            button_1.setText("�Y    �p");
        }
        return button_1;
    }
    
    /**
     * @return
     */
    protected JButton getButton_2() {
        if (button_2 == null) {
            button_2 = new JButton();
            button_2.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    if (newW <= 0 || newH <= 0) {
                        JOptionPane.showMessageDialog(null, "�ϧΪ��e�שM���ץ����j��0");// ��ܴ��ܸ�T
                        return;
                    }
                    FileDialog dialog = new FileDialog(SaveZoomImageFrame.this,
                            "�x�s");// �إߥ�͵���
                    dialog.setMode(FileDialog.SAVE);// �]�w��͵������x�s��͵���
                    dialog.setVisible(true);// ����x�s��͵���
                    String path = dialog.getDirectory();// ��o�ɮת��x�s���|
                    String fileName = dialog.getFile();// ��o�x�s���ɮצW
                    if (path == null || fileName == null) {
                        return;
                    }
                    String fileExtName = fileName.substring(fileName
                            .indexOf(".") + 1);// �ɮ��X�R�W,���t�I
                    String pathAndName = path + "\\" + fileName;// �ɮת�������|
                    BufferedImage bufferImage = new BufferedImage(newW, newH,
                            BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
                    Graphics g = bufferImage.getGraphics();// ��oø�ϤW�U��ﹳ
                    g.drawImage(img, 0, 0, newW, newH, null);// �b�w�ĹϧΤWø�s�ϧ�
                    try {
                        ImageIO.write(bufferImage, fileExtName, new File(
                                pathAndName));// �N�w�Ĺϧ��x�s��Ϻ�
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, "�x�s����\n"
                                + e1.getMessage());// ��ܴ��ܸ�T
                    }
                }
            });
            button_2.setText("�O    �s");
        }
        return button_2;
    }
    
    /**
     * @return
     */
    protected JButton getButton_3() {
        if (button_3 == null) {
            button_3 = new JButton();
            button_3.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    System.exit(0);
                }
            });
            button_3.setText("�h    �X");
        }
        return button_3;
    }
    
    // �إ߭��O���O
    class ZoomImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �M��ø�ϤW�U�媺���e
            imgWidth = img.getWidth(this); // ��o�Ϥ��e��
            imgHeight = img.getHeight(this); // ��o�Ϥ�����
            newW = (int) (imgWidth * value / 100);// �p��Ϥ��Y��᪺�e��
            newH = (int) (imgHeight * value / 100);// �p��Ϥ��Y��᪺����
            g.drawImage(img, 0, 0, newW, newH, this);// ø�s���w�j�p���Ϥ�
        }
    }
    
}
