package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NegativeImageFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private NegativeImagePanel negativeImagePanel = null; // �ŧi�ϧέ��O�ﹳ
    
    public static void main(String args[]) {
        NegativeImageFrame frame = new NegativeImageFrame();
        frame.setVisible(true);
    }
    
    public NegativeImageFrame() {
        super();
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��ϦV�S��"); // �]�w������D
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativeImagePanel = new NegativeImagePanel(); // �إ߹ϧέ��O�ﹳ
        this.add(negativeImagePanel); // �b����W�W�[�ϧέ��O�ﹳ
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256*1];// �إߪ���C��ϦV�����q�}�C
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// ���}�C������
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// �إߴM���ﹳ
                LookupOp op = new LookupOp(table,null);// �إ߹�{�q����ؼдM��ާ@��LookupOp�ﹳ
                image = op.filter(image, null);// �I�sLookupOp��H��filter()��k�A��{�ϧΤϦV�\��
                repaint();  // �I�spaint()��k
            }
        });
        button.setText("��    �V");
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
    
    
    // �إ߭��O���O
    class NegativeImagePanel extends JPanel {
        public NegativeImagePanel(){
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