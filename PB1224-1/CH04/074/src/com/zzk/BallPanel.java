package com.zzk;
import java.awt.*;
import javax.swing.JLabel;
/**
 * @author �i���[
 */
public class BallPanel extends JLabel implements Runnable {
    private int r = 10;// �p�y�b�|
    private int width = r * 2;// �y�e��
    private int height = r * 2;// �y����
    private Color ballColor = Color.BLUE;// �w�]�C��
    public BallPanel() {
        setPreferredSize(new Dimension(width, height));// ��l�Ƥj�p
        new Thread(this).start();// �Ұʤp�y���D�u�{
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ballColor);// �]�w�w�]�C��
        g.fillOval(0, 0, width, height);// �b���ҤWø�s�y��
    }
    @Override
    public void run() {
        Container parent = getParent();/// ��o�ثe���Ҫ����Ůe���ﹳ
        Point myPoint = getLocation();// ��o��l��m
        while (true) {// �`��Ū�����e���ﹳ
            if (parent == null) {
                try {
                    Thread.sleep(50);// �u�{��v
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myPoint = getLocation();// ��o��l��m
                parent = getParent();// ��o�ثe���Ҫ����Ůe���ﹳ
            } else {// �p�G�w�g��o����e��
                break;// ���X�`��
            }
        }
        int sx = myPoint.x;// X�y��
        int sy = myPoint.y;// y�y��
        int step = (int) (Math.random() * 10) % 3 + 1;// ���ʨB�i
        int dx = (Math.random() * 100) >= 50 ? step : -step;// �����B�i��
        step = (int) (Math.random() * 10) % 3 + 1;// ���ʨB�i
        int dy = (Math.random() * 100) >= 50 ? step : -step;// �����B�i��
        int stime = (int) (Math.random() * 80 + 10);// �H�����ʳt��
        while (parent.isVisible()) {
            int parentWidth = parent.getWidth();// �e���e��
            int parentHeight = parent.getHeight();// �e������
            setLocation(sx, sy);// ���w�p�y����m
            try {
                Thread.sleep(stime);// �z�L��v���ܲ��ʳt��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sx = sx + dx * 5;// �����y�а���5�ӹ���
            sy = sy + dy * 5;// �����y�а���5�ӹ���
            // �˴��������
            if (sy > parentHeight - height || sy < 0)
                dy = -dy;// ����y�жW�X�������
            // �˴��������
            if (sx > parentWidth - width || sx < 0)
                dx = -dx;// ����y�жW�X�������
        }
    }
}
