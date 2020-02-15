package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class ImageBrightenFrame extends JFrame {
    private BufferedImage image;// �Ω�վ�G�ת��w�Ĺϧιﹳ
    private BufferedImage oldImage;// �Ω�s��վ�G�פ��e����w�Ĺϧιﹳ
    private ImageBrightenPanel imageBrightenPanel = new ImageBrightenPanel();
    
    public static void main(String args[]) {
        ImageBrightenFrame frame = new ImageBrightenFrame();
        frame.setVisible(true);
    }
    
    public ImageBrightenFrame() {
        super();
        setBounds(100, 100, 357, 276);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("�վ�Ϥ����G��");
        Image img = null;
        try {
            img = ImageIO.read(new File("src/img/image.jpg"));  // �إ߹ϧιﹳ
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
        image.getGraphics().drawImage(img, 0, 0, null);// �b�w�Ĺϧιﹳ�Wø�s�ϧ�
        oldImage = image;// �x�s��Ӫ��ϧι�H�A�Ω�H�᪺��_�ާ@
        getContentPane().add(imageBrightenPanel, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float a = 1.0f;// �w�q�Y��]�l
                float b = 5.0f;// �w�q�����q
                RescaleOp op = new RescaleOp(a,b,null);// �إߨ㦳���w�Y��]�l�M�����q�� RescaleOp�ﹳ
                image = op.filter(image, null);// �﷽�ϧΤ�����ƶi��v�������Y��A�F���ܫG���ĪG
                repaint();// ���sø�s�ϧ�
            }
        });
        button.setText("��    �G");
        panel.add(button);
        
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                float a = 1.0f;// �w�q�Y��]�l
                float b = -5.0f;// �w�q�����q
                RescaleOp op = new RescaleOp(a,b,null);// �إߨ㦳���w�Y��]�l�M�����q�� RescaleOp�ﹳ
                image = op.filter(image, null);// �﷽�ϧΤ�����ƶi��v�������Y��A�F���ܷt���ĪG
                repaint();// ���sø�s�ϧ�
            }
        });
        button_3.setText("��    �t");
        panel.add(button_3);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                image = oldImage;  // ��o�ܫG�e���ϧ�
                imageBrightenPanel.repaint();// ���sø�s��ϧΡA�Y��_���ܫG�e���ϧ�
            }
        });
        button_2.setText("��    �_");
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
    
    class ImageBrightenPanel extends JPanel {
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);  // �N�w�Ĺϧιﹳø�s�쭱�O�W
            }
        }
    }
}
