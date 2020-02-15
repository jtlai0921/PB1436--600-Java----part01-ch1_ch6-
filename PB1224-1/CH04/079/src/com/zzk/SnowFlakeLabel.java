package com.zzk;
import java.awt.*;
import javax.swing.*;
/**
 * @author �i���[
 */
public class SnowFlakeLabel extends JLabel implements Runnable {
    private final static ImageIcon snow = new ImageIcon(SnowFlakeLabel.class
            .getResource("/image/snowflake.png"));
    private int width = snow.getIconWidth();// �e��
    private int height = snow.getIconHeight();// ����
    /**
     * �غc��k
     */
    public SnowFlakeLabel() {
        setSize(new Dimension(width, height));// ��l�Ƥj�p
        setIcon(snow);// ���w�ϼ�
        new Thread(this).start();// �إߨñҰʽu�{
    }
    public void run() {
        Container parent = getParent();// ��o���e���ﹳ
        Point myPoint = getLocation();// ��o��l��m
        while (true) {// �`��Ū�����e���ﹳ
            if (parent == null) {
                try {
                    Thread.sleep(50);// �u�{��v
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myPoint = getLocation();// ��o��l��m
                parent = getParent();// ��o���e���ﹳ
            } else {// �p�G�w�g��o����e��
                break;// ���X�`��
            }
        }
        int sx = myPoint.x;// X�y��
        int sy = myPoint.y;// Y�y��
        int stime = (int) (Math.random() * 30 + 10);// �H�����ʳt��
        int parentHeight = parent.getHeight();// �e������
        while (parent.isVisible() && sy < parentHeight - height) {
            setLocation(sx, sy);// ���w��m
            try {
                Thread.sleep(stime);// �u�{��v
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sy += 2;// ��������2�ӹ���
        }
    }
}
