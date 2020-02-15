package com.zzk;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CircularRollPictureFrame extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CircularRollPictureFrame frame = new CircularRollPictureFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public CircularRollPictureFrame() {
        super();
        setTitle("�`�����ʹϤ�");
        setBounds(100, 100, 326, 202);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CircularRollPicturePanel panel = new CircularRollPicturePanel();// �إ߭��O�ﹳ
        getContentPane().add(panel);// �N���O�W�[�쵡��e��
        Thread thread = new Thread(panel);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
    }
    
    /**
     * @author �i���[
     * �إ߹�{Runnable���f���������O���O
     */
    private class CircularRollPicturePanel extends JPanel implements Runnable {
        int x = 0;// �w�q�Ϥ����ʦ�m��x�y��
        int y = 30;// �w�q�Ϥ����ʦ�m��y�y��
        URL imgUrl = CircularRollPictureFrame.class.getResource("/image/picture.png");// ��o�Ϥ��귽�����|
        Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧιﹳ
        public void paint(Graphics g){
            g.clearRect(0, 0, getWidth(), getHeight());// �M�����O�W�����e
            g.drawImage(img, x, y, this);// �b���O�����w��mø�s�ϧ�
        }
        public void run() {
            boolean flag = true;// �ŧi�аO�ܼ�
            while (true){
                if (flag) {// �аO�ܼƬ�true
                    x = x + 10;// �Ϥ�x�y�Эȥ[10
                    if (x >= getWidth() - img.getWidth(this)) {// �Ϥ���x�y�ЭȤj�󵥩󭱪O�P�Ϥ��e�ת��t
                        x = getWidth() - img.getWidth(this); // �Ϥ���x�y�Эȵ��󭱪O�P�Ϥ��e�ת��t
                        flag = false;// �]�w�аO�ܼƬ�false
                    }
                }else {// �аO�ܼƬ�false
                    x = x - 10;// �Ϥ�x�y�Эȴ�10
                    if ( x <= 0 ) {// �Ϥ���x�y�ЭȤp�󵥩�0
                        x = 0;// �Ϥ���x�y�Эȵ���0
                        flag = true;// �]�w�аO�ܼƬ�true
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