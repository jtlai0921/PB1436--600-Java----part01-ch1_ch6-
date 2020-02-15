package com.zzk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class MultiColorToGrayFrame extends JFrame {
    private BufferedImage image;
    private ColorToGrayPanel colorToGrayPanel = new ColorToGrayPanel();
    
    public static void main(String args[]) {
        MultiColorToGrayFrame frame = new MultiColorToGrayFrame();
        frame.setVisible(true);
    }
    
    public MultiColorToGrayFrame() {
        super();
        setBounds(100, 100, 357, 276);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("�m��ϧ��ର�Ƕ�");
        Image img = null;
        try {
            img = ImageIO.read(new File("src/img/image.jpg"));  // �إ߹ϧιﹳ
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = new BufferedImage(img.getWidth(this), img.getHeight(this),
                BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
        image.getGraphics().drawImage(img, 0, 0, null);// �b�w�Ĺϧιﹳ�Wø�s�ϧ�
        
        getContentPane().add(colorToGrayPanel, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                ColorSpace colorSpace1 = ColorSpace
                        .getInstance(ColorSpace.CS_GRAY);// �إߤ��ؽu�ʬ��Ƕ����C��Ŷ�
                ColorSpace colorSpace2 = ColorSpace
                .getInstance(ColorSpace.CS_LINEAR_RGB);// �إߤ��ؽu�ʬ� RGB���C��Ŷ�
                ColorConvertOp op = new ColorConvertOp(colorSpace1,colorSpace2,
                        null);// �إ߶i���C���ഫ����H
                image = op.filter(image, null);// ��w�Ĺϧζi���C���ഫ
                repaint();// ���sø�s�ϧ�
            }
        });
        button.setText("�ର�Ƕ�");
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
    
    class ColorToGrayPanel extends JPanel {
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);  // �N�w�Ĺϧιﹳø�s�쭱�O�W
            }
        }
    }
}
