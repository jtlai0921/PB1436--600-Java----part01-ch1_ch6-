package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.FileDialog;
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
public class SaveMixPictureFrame extends JFrame {
    private Image img1 = null; // �ŧi�ϧιﹳ
    private Image img2 = null; // �ŧi�ϧιﹳ
    private boolean mixFlag = false;// �Ω�M�w�O�_���X�Ϥ��A��true�ɷ��X�Ϥ�
    private boolean firstOrSecondFlag = false;// ��false����ܲĤ@�i�Ϥ��A��true����ܲĤG�i�Ϥ�
    private MixPicturePanel mixPicturePanel = null; // �ŧi�ϧέ��O�ﹳ
    private int panelWidth = 0;// �ϧέ��O���e��
    private int panelHeight = 0;// �ϧέ��O������
    public static void main(String args[]) {
        SaveMixPictureFrame frame = new SaveMixPictureFrame();
        frame.setVisible(true);
    }
    
    public SaveMixPictureFrame() {
        super();
        URL imgUrl = SaveMixPictureFrame.class.getResource("/img/img.jpg");// ��o�Ϥ��귽�����|
        img1 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        imgUrl = SaveMixPictureFrame.class.getResource("/img/imag.jpg");// ��o�Ϥ��귽�����|
        img2 = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        mixPicturePanel = new MixPicturePanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 476, 336); // �]�w����j�p�M��m
        this.add(mixPicturePanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("���X��i�Ϥ����x�s"); // �]�w������D
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = false;// �аO��ø�s�ĤG�i�Ϥ�
                mixFlag = false;// �аO�S���X�Ϥ�
                mixPicturePanel.repaint();// �I�spaint()��kø�s�Ĥ@�i�Ϥ�
            }
        });
        button_2.setText("�Ĥ@�i");
        panel.add(button_2);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// �аOø�s�ĤG�i�Ϥ�
                mixFlag = false;// �аO�S���X�Ϥ�
                mixPicturePanel.repaint();// �I�spaint()��kø�s�Ĥ@�i�Ϥ�
            }
        });
        button_3.setText("�ĤG�i");
        panel.add(button_3);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                firstOrSecondFlag = true;// �аOø�s�ĤG�i�Ϥ�
                mixFlag = true;// �аO���X�Ϥ�
                mixPicturePanel.repaint();// �I�spaint()��kø�s�Ĥ@�i�Ϥ�
            }
        });
        button.setText("���X�Ϥ�");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!mixFlag) {
                    JOptionPane.showMessageDialog(null,"�٨S�����X�Ϥ��C");// ��ܴ��ܸ�T
                    return;
                }
                FileDialog dialog = new FileDialog(SaveMixPictureFrame.this,"�x�s");// �إߥ�͵���
                dialog.setMode(FileDialog.SAVE);// �]�w��͵������x�s��͵���
                dialog.setVisible(true);// ����x�s��͵���
                String path = dialog.getDirectory();// ��o�ɮת��x�s���|
                String fileName = dialog.getFile();// ��o�x�s���ɮצW
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// �ɮ��X�R�W,���t�I
                String pathAndName = path + "\\" + fileName;// �ɮת�������|
                BufferedImage bufferImage = new BufferedImage(panelWidth, panelHeight,
                        BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
                Graphics2D g2 = (Graphics2D)bufferImage.getGraphics();// ��oø�ϤW�U��ﹳ
                g2.drawImage(img1, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// ø�s�ϧ�
                    AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��o��ܳz���ת�AlphaComposite�ﹳ
                    g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
                    g2.drawImage(img2, 0, 0, panelWidth, panelHeight, SaveMixPictureFrame.this);// ø�s�վ�z���׫᪺�Ϥ�
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
        
        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_4.setText("�h    �X");
        panel.add(button_4);
        
    }
    
    // �إ߭��O���O
    class MixPicturePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            panelWidth = getWidth();// ��o�ϧέ��O���e��
            panelHeight = getHeight();// ��o�ϧέ��O������
            g2.drawImage(img1, 0, 0, panelWidth, panelHeight, this);// ø�s�ϧ�
            if (mixFlag) {// �аO���X�Ϥ�
                AlphaComposite alpha = AlphaComposite.SrcOver.derive(0.4f);// ��o��ܳz���ת�AlphaComposite�ﹳ
                g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
            }
            if (firstOrSecondFlag) {// �аOø�s�ĤG�i�Ϥ�
                g2.drawImage(img2, 0, 0, panelWidth, panelHeight, this);// ø�s�վ�z���׫᪺�Ϥ�
            }
        }
    }
}
