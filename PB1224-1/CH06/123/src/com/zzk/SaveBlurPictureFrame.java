package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveBlurPictureFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private BlurPicturePanel blurPicturePanel = null; // �ŧi�ϧέ��O�ﹳ
    private boolean blurFlag = false;// �ҽk�аO
    
    public static void main(String args[]) {
        SaveBlurPictureFrame frame = new SaveBlurPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveBlurPictureFrame() {
        super();
        setTitle("�ҽk�Ϥ����x�s"); // �]�w������D
        setBounds(200, 160, 439, 319); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        blurPicturePanel = new BlurPicturePanel(); // �إ߹ϧέ��O�ﹳ
        add(blurPicturePanel); // �b����W�W�[�ϧέ��O�ﹳ
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = new float[9];// �w�q��ܹ������q���}�C
                for (int i = 0; i < 9; i++) {
                    elements[i] = 0.11f;// ���}�C������
                }
                convolve(elements);// �I�s��k�A��{�ҽk�\��
                blurFlag = true;// �ҽk�Ϥ��аO
            }
        });
        button.setText("�ҽk�Ϥ�");
        panel.add(button);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!blurFlag) {
                    JOptionPane.showMessageDialog(null, "�٨S���ҽk�Ϥ��C");// ��ܴ��ܸ�T
                    return;
                }
                FileDialog dialog = new FileDialog(SaveBlurPictureFrame.this,
                        "�x�s");// �إߥ�͵���
                dialog.setMode(FileDialog.SAVE);// �]�w��͵������x�s��͵���
                dialog.setVisible(true);// ����x�s��͵���
                String path = dialog.getDirectory();// ��o�ɮת��x�s���|
                String fileName = dialog.getFile();// ��o�x�s���ɮצW
                if (path == null || fileName == null) {
                    return;
                }
                String fileExtName = fileName
                        .substring(fileName.indexOf(".") + 1);// �ɮ��X�R�W,���t�I
                String pathAndName = path + "\\" + fileName;// �ɮת�������|
                try {
                    ImageIO.write(image, fileExtName, new File(pathAndName));// �N�w�Ĺϧ��x�s��Ϻ�
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "�x�s����\n"
                            + e1.getMessage());// ��ܴ��ܸ�T
                }
            }
        });
        button_2.setText("�x�s�Ϥ�");
        panel.add(button_2);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h    �X");
        panel.add(button_1);
    }
    
    private void convolve(float[] elements) {
        Kernel kernel = new Kernel(3, 3, elements);// �إ� Kernel�ﹳ
        ConvolveOp op = new ConvolveOp(kernel);// �إ�ConvolveOp�ﹳ
        if (image == null) {
            return;
        }
        image = op.filter(image, null); // �L�o�w�Ĺϧιﹳ
        repaint();// �I�spaint()��k
    }
    
    // �إ߭��O���O
    class BlurPicturePanel extends JPanel {
        public BlurPicturePanel() {
            Image img = null;// �ŧi�إ߹ϧιﹳ
            try {
                img = ImageIO.read(new File("src/img/imag.jpg")); // �إ߹ϧιﹳ
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null), img.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
            image.getGraphics().drawImage(img, 0, 0, null);// �b�w�Ĺϧιﹳ�Wø�s�ϧ�
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// ø�s�w�Ĺϧιﹳ
            }
        }
    }
}
