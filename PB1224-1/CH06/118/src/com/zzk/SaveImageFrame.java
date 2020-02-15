package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
public class SaveImageFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
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
                    SaveImageFrame frame = new SaveImageFrame();
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
    public SaveImageFrame() {
        super();
        setTitle("�x�s�Ϥ��ɮ�"); // �]�w������D
        setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL imgUrl = SaveImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imagePanel = new DrawImagePanel(); // �إ߹ϧέ��O�ﹳ
        add(imagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveImageFrame.this), img
                        .getHeight(SaveImageFrame.this),
                        BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
                Graphics g = bufferImage.getGraphics();// ��oø�ϤW�U��ﹳ
                g.drawImage(img, 0, 0, null);// �b�w�ĹϧΤWø�s�ϧ�
                try {
                    ImageIO.write(bufferImage, "jpg", new File("c:/image.jpg"));// �N�w�Ĺϧ��x�s��Ϻ�
                    JOptionPane.showMessageDialog(null,
                            "�w�N�Ϥ�image.jpg\n���\�a�x�s��C:��");// ��ܴ��ܸ�T
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("�x�s�Ϥ�");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h    �X");
        panel.add(button_1);
        //
    }
    
    // �إ߭��O���O
    class DrawImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, this); // ø�s���w���Ϥ�
        }
    }
}
