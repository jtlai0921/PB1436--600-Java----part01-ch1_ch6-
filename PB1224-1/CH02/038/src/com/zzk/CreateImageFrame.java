package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CreateImageFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateImageFrame frame = new CreateImageFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CreateImageFrame() {
        super();
        setTitle("�ϥι����Ȳ��͹ϧ�");
        setBounds(100, 100, 300, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new CreateImagePanel());
    }
    
    class CreateImagePanel extends JPanel {// �إߥι����Ȳ��͹ϧΪ����O���O
        public void paint(Graphics g) {
            int w = 300;// �e��
            int h = 220;// ����
            int pix[] = new int[w * h];// �x�s�����Ȫ��}�C
            int index = 0;// �x�s�}�C������
            for (int y = 0; y < h; y++) {// �b�����i��վ�A�q�¦⺥�h�����
                int red = (y * 255) / (h - 1);// �p�⫫�����C���
                for (int x = 0; x < w; x++) {// �b�����i��վ�A�q�¦⺥�h���Ŧ�
                    int blue = (x * 255) / (w - 1);// �p��������C���
                    // �z�L����B��M�޿�ιB��p�⹳���ȡA�õ����ȵ������}�C
                    pix[index++] = (255 << 24) | (red << 16) | blue;
                }
            }
            // �إߨϥΰ}�C��Image���͹����Ȫ�ImageProducer�ﹳ
            ImageProducer imageProducer = new MemoryImageSource(w, h, pix, 0, w);
            Image img = createImage(imageProducer);// �إ߹ϧιﹳ
            g.drawImage(img, 0, 0,getWidth(),getHeight(), this);// ø�s�ϧ�
        }
    }
}
