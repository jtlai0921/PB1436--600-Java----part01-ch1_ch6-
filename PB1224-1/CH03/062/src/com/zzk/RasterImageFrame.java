package com.zzk;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class RasterImageFrame extends JFrame {
    private static final double XMIN = -2;// �ŧi�bx��V���̤p��
    private static final double XMAX = 2;// �ŧi�bx��V���̤j��
    private static final double YMIN = -2;// �ŧi�by��V���̤p��
    private static final double YMAX = 2;// �ŧi�by��V���̤j��
    private static final int MAX_ITERATIONS = 16;// �ŧi�̤j���N����
    
    public RasterImageFrame() {
        setTitle("���]�ϧ�");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage image = makeRasterImage(300, 240);
        add(new JLabel(new ImageIcon(image)));
    }
    
    public static void main(String[] args) {
        JFrame frame = new RasterImageFrame();
        frame.setVisible(true);
    }
    
    private BufferedImage makeRasterImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);// �إ߽w�Ĺϧιﹳ
        WritableRaster raster = image.getRaster();// ��o���ѹ����g�J�\�઺WritableRaster�ﹳ
        ColorModel model = image.getColorModel();// ��o�w�ĹϧΪ��C��ҫ�
        Color fractalColor = Color.RED;// �w�q��ܬ��⪺�C��ﹳ
        int argb = fractalColor.getRGB();// ��o����C�⪺RGB��
        Object colorData = model.getDataElements(argb, null);// �Ǧ^ColorModel�����w�������}�C��ܧΦ�
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // �p���I�]i,j�^�O�_�ɭP�P�I(a,b)�W����������
                double a = XMIN + i * (XMAX - XMIN) / width;
                double b = YMIN + j * (YMAX - YMIN) / height;
                if (!isOrNotConvergence(a, b)) {// �p�G�I�]i,j�^�ɭP�P�I(a,b)�W���������ġAescapesToInfinity()��k�Ǧ^false�A�h�i����]ø�s
                    raster.setDataElements(i, j, colorData);// �����ATransferType�򥻰}�C������@�����]�w���
                }
            }
        }
        return image;// �Ǧ^ø�s�����]�ϧΪ��w�Ĺϧιﹳ
    }
    
    private boolean isOrNotConvergence(double a, double b) {// �P�_�Ʀr�ǦC�W���I(a,b)�O���Ī��A�٬O�o����
        double x = 0.0D;// �p�Gx�j��2�A�Ʀr�ǦC�N�O�o����
        double y = 0.0D;// �p�Gy�j��2�A�Ʀr�ǦC�]�O�o����
        int iterations = 0;// �`���ܼ�
        while (x <= 2 && y <= 2 && iterations < MAX_ITERATIONS) {
            double xNew = x * x - y * y + a;// �p��C���I��x��
            double yNew = 2 * x * y + b;// �p��C���I��y��
            x = xNew;// �����ȵ��ܼ�x�A�Ω�P�_�O�����٬O�o��
            y = yNew;// �����ȵ��ܼ�y�A�Ω�P�_�O�����٬O�o��
            iterations++; // �վ㭡�N���ܼƪ���
        }
        return x > 2 || y > 2;// �Ǧ^false��ܦ��īh�i��ø�s�A��true��ܵo���h�z��
    }
}