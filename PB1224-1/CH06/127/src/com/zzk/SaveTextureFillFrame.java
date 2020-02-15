package com.zzk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveTextureFillFrame extends JFrame {
    private TextureFillPanel textureFillPanel = null; // �ŧi���O�ﹳ
    private int newW, newH;// �Ω��x�s�Ϥ��Y��᪺�e�שM����
    
    public static void main(String args[]) {
        SaveTextureFillFrame frame = new SaveTextureFillFrame();
        frame.setVisible(true);
    }
    
    public SaveTextureFillFrame() {
        super();
        setTitle("��R���z���x�s���Ϥ�"); // �]�w������D
        textureFillPanel = new TextureFillPanel(); // �إ߹ϧέ��O�ﹳ
        setBounds(200, 160, 346, 285); // �]�w����j�p�M��m
        add(textureFillPanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                FileDialog dialog = new FileDialog(SaveTextureFillFrame.this,
                        "�x�s");// �إߥ�͵���
                dialog.setMode(FileDialog.SAVE);// �]�w��͵������x�s��͵���
                dialog.setVisible(true);// ����x�s��͵���
                String path = dialog.getDirectory();// ��o�ɮת��x�s���|
                String fileName = dialog.getFile();// ��o�x�s���ɮצW
                if (path == null || fileName == null) {
                    JOptionPane.showMessageDialog(null, "�Ы��w�ɮת��x�s���|�M�ɮצW�C");// ��ܴ��ܸ�T
                    return;
                }
                String fileExtName = fileName
                        .substring(fileName.indexOf(".") + 1);// �ɮ��X�R�W,���t�I
                String pathAndName = path + "\\" + fileName;// �ɮת�������|
                BufferedImage image = new BufferedImage(200, 200,
                        BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
                Graphics2D g2 = image.createGraphics();// ��o�w�Ĺϧι�H��ø�ϤW�U��ﹳ
                g2.setColor(Color.BLUE);// �]�w�C��
                g2.fillRect(0, 0, 90, 90);// ø�s��R�x��
                g2.setColor(Color.RED);// �]�w�C��
                g2.fillOval(95, 0, 90, 90);// ø�s�a��R�⪺���
                g2.setColor(Color.GREEN);// �]�w�C��
                g2.fillRect(95, 95, 90, 90);// ø�s��R�x��
                g2.setColor(Color.ORANGE);// �]�w�C��
                g2.fillOval(0, 95, 90, 90);// ø�s�a��R�⪺���
                Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// �إ�Rectangle2D�ﹳ
                TexturePaint textPaint = new TexturePaint(image, rect);// �إ߯��z��R�ﹳ
                BufferedImage bufferImage = new BufferedImage(newW, newH,
                        BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
                Graphics g = bufferImage.getGraphics();// ��o�w�ĹϧΪ�ø�ϤW�U��ﹳ
                Graphics2D graphics2 = (Graphics2D) g;// �ഫø�ϤW�U��ﹳ��Graphics2D���A
                graphics2.setPaint(textPaint);// ��ø�ϤW�U��ﹳ���w���z��R�ﹳ
                Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW,
                        newH);// �إ߯x�ιﹳ
                graphics2.fill(ellipse2);// ø�s��R���z���x��
                try {
                    ImageIO.write(bufferImage, fileExtName, new File(
                            pathAndName));// �N�w�Ĺϧ��x�s��Ϻ�
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "�x�s����\n"
                            + e1.getMessage());// ��ܴ��ܸ�T
                }
            }
        });
        button.setText("�x�s���Ϥ�");
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
    class TextureFillPanel extends JPanel {
        public void paint(Graphics g) {
            BufferedImage image = new BufferedImage(200, 200,
                    BufferedImage.TYPE_INT_RGB);// �إ߽w�Ĺϧιﹳ
            Graphics2D g2 = image.createGraphics();// ��o�w�Ĺϧι�H��ø�ϤW�U��ﹳ
            g2.setColor(Color.BLUE);// �]�w�C��
            g2.fillRect(0, 0, 90, 90);// ø�s��R�x��
            g2.setColor(Color.RED);// �]�w�C��
            g2.fillOval(95, 0, 90, 90);// ø�s�a��R�⪺���
            g2.setColor(Color.GREEN);// �]�w�C��
            g2.fillRect(95, 95, 90, 90);// ø�s��R�x��
            g2.setColor(Color.ORANGE);// �]�w�C��
            g2.fillOval(0, 95, 90, 90);// ø�s�a��R�⪺���
            Rectangle2D rect = new Rectangle2D.Float(10, 10, 20, 20);// �إ�Rectangle2D�ﹳ
            TexturePaint textPaint = new TexturePaint(image, rect);// �إ߯��z��R�ﹳ
            Graphics2D graphics2 = (Graphics2D) g;// �ഫpaint()��k��ø�ϤW�U��ﹳ
            graphics2.setPaint(textPaint);// ��ø�ϤW�U��ﹳ�]�w���z��R�ﹳ
            newW = getWidth();
            newH = getHeight();
            Rectangle2D.Float ellipse2 = new Rectangle2D.Float(0, 0, newW, newH);// �إ߯x�ιﹳ
            graphics2.fill(ellipse2);// ø�s��R���z���x��
        }
    }
}
