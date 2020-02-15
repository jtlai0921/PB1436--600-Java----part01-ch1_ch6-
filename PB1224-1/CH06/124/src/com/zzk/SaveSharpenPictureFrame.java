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
public class SaveSharpenPictureFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private SharpenPicturePanel sharpenPicturePanel = null; // �ŧi�ϧέ��O�ﹳ
    private boolean sharpenFlag = false;// �U�ƼаO
    public static void main(String args[]) {
        SaveSharpenPictureFrame frame = new SaveSharpenPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveSharpenPictureFrame() {
        super();
        setTitle("�U�ƹϤ����x�s"); // �]�w������D
        setBounds(200, 160, 446, 305); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        sharpenPicturePanel = new SharpenPicturePanel(); // �إ߹ϧέ��O�ﹳ
        add(sharpenPicturePanel); // �b����W�W�[�ϧέ��O�ﹳ
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = {0.0f,-1.0f,0.0f,-1.0f,5.0f,-1.0f,0.0f,-1.0f,0.0f};// �ŧi��ܹ������q���}�C
                convolve(elements);// �I�s��k��{�Ϥ��U�ƥ\��
                sharpenFlag = true;// �U�ƹϤ��аO
            }
        });
        button.setText("�U�ƹϤ�");
        panel.add(button);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!sharpenFlag) {
                    JOptionPane.showMessageDialog(null,"�٨S���U�ƹϤ��C");// ��ܴ��ܸ�T
                    return;
                }
                FileDialog dialog = new FileDialog(SaveSharpenPictureFrame.this,"�x�s");// �إߥ�͵���
                dialog.setMode(FileDialog.SAVE);// �]�w��͵������x�s��͵���
                dialog.setVisible(true);// ����x�s��͵���
                String path = dialog.getDirectory();// ��o�ɮת��x�s���|
                String fileName = dialog.getFile();// ��o�x�s���ɮצW
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// �ɮ��X�R�W,���t�I
                String pathAndName = path + "\\" + fileName;// �ɮת�������|
                try {
                    ImageIO.write(image, fileExtName, new File(pathAndName));// �N�w�Ĺϧ��x�s��Ϻ�
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"�x�s����\n"+e1.getMessage());// ��ܴ��ܸ�T
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
    class SharpenPicturePanel extends JPanel {
        public SharpenPicturePanel(){
            Image img = null;// �ŧi�إ߹ϧιﹳ
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // �إ߹ϧιﹳ
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
            image.getGraphics().drawImage(img, 0, 0,  null);// �b�w�Ĺϧιﹳ�Wø�s�ϧ�
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// ø�s�w�Ĺϧιﹳ
            }
        }
    }
}
