package com.zzk;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;

public class BlurImageFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private BlurImagePanel blurImagePanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        BlurImageFrame frame = new BlurImageFrame();
        frame.setVisible(true);
    }
    
    public BlurImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��ҽk�S��"); // �]�w������D
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        blurImagePanel = new BlurImagePanel(); // �إ߹ϧέ��O�ﹳ
        this.add(blurImagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = new float[9];// �w�q��ܹ������q���}�C
                for (int i = 0; i < 9; i++) {
                    elements[i] = 0.11f;// ���}�C������
                }
                convolve(elements);// �I�s��k�A��{�ҽk�\��
            }
        });
        button.setText("��    �k");
        panel.add(button);
        
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
    class BlurImagePanel extends JPanel {
        public BlurImagePanel(){
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