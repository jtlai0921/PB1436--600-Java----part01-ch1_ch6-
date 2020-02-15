package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveNegativePictureFrame extends JFrame {
    private BufferedImage image;// �ŧi�w�Ĺϧιﹳ
    private NegativePicturePanel negativePicturePanel = null; // �ŧi�ϧέ��O�ﹳ
    private boolean negativeFlag = false;// �ϦV�аO
    public static void main(String args[]) {
        SaveNegativePictureFrame frame = new SaveNegativePictureFrame();
        frame.setVisible(true);
    }
    
    public SaveNegativePictureFrame() {
        super();
        setTitle("�ϦV���x�s�Ϥ�"); // �]�w������D
        setBounds(200, 160, 516, 458); // �]�w����j�p�M��m
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativePicturePanel = new NegativePicturePanel(); // �إ߹ϧέ��O�ﹳ
        add(negativePicturePanel); // �b����W�W�[�ϧέ��O�ﹳ
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256];// �إߪ���C��ϦV�����q�}�C
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// ���}�C������
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// �إߴM���ﹳ
                LookupOp op = new LookupOp(table,null);// �إ߹�{�q����ؼдM��ާ@��LookupOp�ﹳ
                image = op.filter(image, null);// �I�sLookupOp��H��filter()��k�A��{�ϧΤϦV�\��
                repaint();  // �I�spaint()��k
                negativeFlag = !negativeFlag;// �аO�O�_�w�ϦV
                if (negativeFlag){
                    button.setText("�٭�Ϥ�");
                }else{
                    button.setText("�ϦV�Ϥ�");
                }
            }
        });
        button.setText("�ϦV�Ϥ�");
        panel.add(button);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!negativeFlag) {
                    JOptionPane.showMessageDialog(null,"�٨S����ϦV�ާ@�C");// ��ܴ��ܸ�T
                    return;
                }
                FileDialog dialog = new FileDialog(SaveNegativePictureFrame.this,"�x�s");// �إߥ�͵���
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
    
    
    // �إ߭��O���O
    class NegativePicturePanel extends JPanel {
        public NegativePicturePanel(){
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