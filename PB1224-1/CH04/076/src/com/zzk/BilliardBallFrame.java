package com.zzk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class BilliardBallFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BilliardBallFrame frame = new BilliardBallFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public BilliardBallFrame() {
        super();
        setTitle("���y�ʵe");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BilliardBallPanel panel = new BilliardBallPanel();// �إ߭��O�ﹳ
        getContentPane().add(panel);// �N���O�W�[�쵡��e��
        Thread thread = new Thread(panel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
    }
    
    /**
     * �إ߹�{Runnable���f���������O���O
     */
    private class BilliardBallPanel extends JPanel implements Runnable {
        int x1 = 0;// �w�q�Ĥ@�Ӳy���ʦ�m��x�y��
        int y1 = 60;// �w�q�Ĥ@�Ӳy���ʦ�m��y�y��
        int x2 = 326 - 30;// �w�q�ĤG�Ӳy���ʦ�m����lx�y�Ь�����e�״�y���e��
        int y2 = 60;// �w�q�ĤG�Ӳy���ʦ�m��y�y��
        int width = 30;// �w�q�y���e��
        int height = 30;// �w�q�y������
        public void paint(Graphics g) {
            g.clearRect(0, 0, getWidth(), getHeight());// �M�����O�W�����e
            g.setColor(Color.BLUE);// �]�w�C��
            g.fillOval(x1, y1, width, height);// ø�s�Ĥ@�Ӳy
            g.setColor(Color.RED);// �]�w�C��
            g.fillOval(x2, y2, width, height);// ø�s�ĤG�Ӳy
        }
        public void run() {
            boolean flag1 = true;// �ŧi�Ĥ@�Ӳy���аO�ܼ�
            boolean flag2 = true;// �ŧi�ĤG�Ӳy���аO�ܼ�
            while (true) {
                // �Ĥ@�Ӳy��x�y�Эȥ[�W�y���e�פj�󵥩�ĤG�Ӳy��x�y�Эȥ[1�A��ܨ�y�۹J
                if (x1 + width >= x2 + 1) {// ��y�ۼ�
                    x1 = x1 + 5;// �p��Ĥ@�Ӳy��x�y��
                    width = width - 10;// �y���e�״�10
                    x2 = x1 + width;// �p��ĤG�Ӳy��x�y��
                    flag1 = false;// �]�w�Ĥ@�Ӳy���аO�ܼƬ�false
                    flag2 = false;// �]�w�ĤG�Ӳy���аO�ܼƬ�false
                    repaint();// �I�spaint()��k
                } else {// ��y�S�ۼ�
                    if (flag1) {// �аO�ܼƬ�true�A�Ĥ@�Ӳy�k��
                        x1 = x1 + 10;// �Ϥ�x�y�Эȥ[10�A�Ĥ@�Ӳy�k��
                        width = 30;// �y���e��
                    } else {// �аO�ܼƬ�false�A�Ĥ@�Ӳy����
                        x1 = x1 - 10;// �Ϥ�x�y�Эȴ�10�A�Ĥ@�Ӳy����
                        width = 30;// �y���e��
                        if (x1 <= 0) {// �Ϥ���x�y�ЭȤp�󵥩�0
                            x1 = 0;// �Ϥ���x�y�Эȵ���0
                            flag1 = true;// �]�w�аO�ܼƬ�true
                        }
                    }
                    if (flag2) {// �аO�ܼƬ�true�A�ĤG�Ӳy����
                        x2 = x2 - 10;// �Ϥ�x�y�Эȴ�10�A�Y�ĤG�Ӳy����
                        width = 30;
                    } else {// �аO�ܼƬ�false�A�ĤG�Ӳy�k��
                        x2 = x2 + 10;// �Ϥ�x�y�Эȥ[10�A�Y�ĤG�Ӳy�k��
                        width = 30;// �y���e��
                        if (x2 >= getWidth() - width) {// �Ϥ���x�y�ЭȤj�󵥩󭱪O���e�׻P�y���e�פ��t
                            x2 = getWidth() - width ;// �Ϥ���x�y�Эȵ��󭱪O���e�׻P�y���e�פ��t
                            flag2 = true;// �]�w�аO�ܼƬ�true
                        }
                    }
                }
                try {
                    Thread.sleep(200);// ��v200�@��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();// �I�spaint()��k
            }
        }
    }
}
