package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class SaveWatermarkPictureFrame extends JFrame {
    private boolean watermark = false;// ���L�аO�A��true��ø�s���L
    private Image img = null; // �ŧi�ϧιﹳ
    private DrawWatermarkPanel watermarkPanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        SaveWatermarkPictureFrame frame = new SaveWatermarkPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveWatermarkPictureFrame() {
        super();
        setTitle("���Ϥ��W�[���L���x�s"); // �]�w������D
        URL imgUrl = SaveWatermarkPictureFrame.class
                .getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        watermarkPanel = new DrawWatermarkPanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 420, 320); // �]�w����j�p�M��m
        this.add(watermarkPanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                watermark = true;
                watermarkPanel.repaint();// �I�spaint()��kø�s���L
            }
        });
        button.setText("�W�[���L");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!watermark) {
                    JOptionPane.showMessageDialog(null,"�٨S�����Ϥ��W�[���L�C");// ��ܴ��ܸ�T
                    return;
                }
                FileDialog dialog = new FileDialog(SaveWatermarkPictureFrame.this,"�x�s");// �إߥ�͵���
                dialog.setMode(FileDialog.SAVE);// �]�w��͵������x�s��͵���
                dialog.setVisible(true);// ����x�s��͵���
                String path = dialog.getDirectory();// ��o�ɮת��x�s���|
                String fileName = dialog.getFile();// ��o�x�s���ɮצW
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// �ɮ��X�R�W,���t�I
                String pathAndName = path + "\\" + fileName;// �ɮת�������|
                BufferedImage bufferImage = new BufferedImage(img
                        .getWidth(SaveWatermarkPictureFrame.this), img
                        .getHeight(SaveWatermarkPictureFrame.this),
                        BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// ��oø�ϤW�U��ﹳ
                g2.drawImage(img, 0, 0, img.getWidth(SaveWatermarkPictureFrame.this), 
                        img.getHeight(SaveWatermarkPictureFrame.this), null);// �b�w�ĹϧΤWø�s�ϧ�
                g2.rotate(Math.toRadians(-30));// ����ø�ϤW�U��ﹳ
                Font font = new Font("����", Font.BOLD, 72);// �إߦr��ﹳ
                g2.setFont(font);// ���w�r��
                g2.setColor(Color.RED);// ���w�C��
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��o��ܳz���ת�AlphaComposite�ﹳ
                g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
                g2.drawString("�s�{����", -30, 240);// ø�s�¤�r,��{���L
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// �N�w�Ĺϧ��x�s��Ϻ�
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"�x�s����\n"+e1.getMessage());// ��ܴ��ܸ�T
                }
            }
        });
        button_1.setText("�x�s�Ϥ�");
        panel.add(button_1);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_2.setText("�h    �X");
        panel.add(button_2);
    }
    
    // �إ߭��O���O
    class DrawWatermarkPanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);// ø�s�ϧ�
            if (watermark) {
                g2.rotate(Math.toRadians(-30));// ����ø�ϤW�U��ﹳ
                Font font = new Font("����", Font.BOLD, 72);// �إߦr��ﹳ
                g2.setFont(font);// ���w�r��
                g2.setColor(Color.RED);// ���w�C��
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��o��ܳz���ת�AlphaComposite�ﹳ
                g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
                g2.drawString("�s�{����", -30, 240);// ø�s�¤�r,��{���L
            }
        }
    }
}
