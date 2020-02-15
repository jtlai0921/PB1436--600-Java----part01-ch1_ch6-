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

public class EdgeDetectImageFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private EdgeDetectImagePanel edgeDetectImagePanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        EdgeDetectImageFrame frame = new EdgeDetectImageFrame();
        frame.setVisible(true);
    }
    
    public EdgeDetectImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��ӫG��t�S��"); // �]�w������D
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        edgeDetectImagePanel = new EdgeDetectImagePanel(); // �إ߹ϧέ��O�ﹳ
        this.add(edgeDetectImagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float[] elements = {0.0f,-1.0f,0.0f,-1.0f,4.0f,-1.0f,0.0f,-1.0f,0.0f};// �ŧi��ܹ������q���}�C
                convolve(elements);// �I�s��k��{�Ϥ��ӫG��t�\��
            }
        });
        button.setText("�ӫG��t");
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
        Kernel kernel = new Kernel(3, 3, elements);             // �إ� Kernel�ﹳ
        ConvolveOp op = new ConvolveOp(kernel);         // �إ�ConvolveOp�ﹳ
        if (image == null) {
            return;
        }
        image = op.filter(image, null);                     // �L�o�w�Ĺϧιﹳ
        repaint();                                  // �I�spaint()��k
    }

    
    // �إ߭��O���O
    class EdgeDetectImagePanel extends JPanel {
        public EdgeDetectImagePanel(){
            Image img = null;// �ŧi�إ߹ϧιﹳ
            try {
                img = ImageIO.read(new File("src/img/image.jpg"));  // �إ߹ϧιﹳ
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