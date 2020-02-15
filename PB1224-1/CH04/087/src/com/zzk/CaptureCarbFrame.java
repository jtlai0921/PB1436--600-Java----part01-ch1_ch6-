package com.zzk;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CaptureCarbFrame extends JFrame implements Runnable {
    private JLabel[] carb; // �s����ܿ��ɪ����Ұ}�C
    private ImageIcon imgCarb; // ���ɹϤ��ﹳ
    private MouseCrab mouseCrab;// ���Ҫ��ƥ��ť��
    
    /**
     * ��ܿ��ɪ����ұ�������Шƥ��ť��
     * @author �i���[
     */
    private final class MouseCrab implements MouseListener {
        private final Cursor cursor1;// ���йϼ�1
        private final Cursor cursor2;// ���йϼ�2
        
        /**
         * �غc��k
         * 
         * @param cursor1
         * @param cursor2
         */
        private MouseCrab(Cursor cursor1, Cursor cursor2) {
            this.cursor1 = cursor1;
            this.cursor2 = cursor2;
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(cursor1);// ���Ы�������ɳ]�w���Ь�cursor1
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            setCursor(cursor2);// ���Ы�����U�ɳ]�w���Ь�cursor2
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(cursor1);// �������}����ϰ�ɳ]�w���Ь�cursor1
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
    
    /**
     * ���г����ƥ��ť���A�Ω���ܹ��Ъ��ϼСC
     * 
     * @author �i���[
     */
    private final class Catcher extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // ��o�ƥ󷽡A�Y���ɼ���
            if (source instanceof JLabel) { // �p�G�ƥ󷽬O���Ҥ���
                JLabel carb = (JLabel) source; // �j���ରJLabel����
                if (carb.getIcon() != null)
                    carb.setIcon(imgCarb2); // ���Ӽ��ҼW�[���ɹϤ�
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON1)
                return;
            Object source = e.getSource(); // ��o�ƥ󷽡A�Y���ɼ���
            if (source instanceof JLabel) { // �p�G�ƥ󷽬O���Ҥ���
                JLabel carb = (JLabel) source; // �j���ରJLabel����
                carb.setIcon(null);// �M�����Ҥ������ɹϤ�
            }
        }
    }
    
    private ImageIcon imgCarb2;
    
    /**
     * �{���J�f��k
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // �إߵ{������
                    CaptureCarbFrame frame = new CaptureCarbFrame();
                    frame.setVisible(true);// ��ܵ���
                    new Thread(frame).start();// �إ߽u�{�ñҰ�
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * �غc��k
     */
    public CaptureCarbFrame() {
        super();
        // �إ߲Ĥ@�ӹ��йϼ�
        ImageIcon icon = new ImageIcon(getClass().getResource("hand.jpg"));
        // �إ߲ĤG�ӹ��йϼ�
        ImageIcon icon2 = new ImageIcon(getClass().getResource("hand2.jpg"));
        // ��o�C�ӹϼЪ��Ϥ�
        Image image = icon.getImage();
        Image image2 = icon2.getImage();
        // �ϥιϤ��إ�2�ӹ��Х��йﹳ
        final Cursor cursor1 = getToolkit().createCustomCursor(image,
                new Point(0, 0), "hand1");
        final Cursor cursor2 = getToolkit().createCustomCursor(image2,
                new Point(0, 0), "hand2");
        // ��l����ܿ��ɼ��Ҥ��󪺨ƥ��ť��
        mouseCrab = new MouseCrab(cursor1, cursor2);
        setResizable(false); // �T��վ㵡��j�p
        getContentPane().setLayout(null); // ���餣�ϥΧG���޲z��
        setTitle("���y������"); // �]�w������D
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ��l�ƭI���Ϥ��ﹳ
        ImageIcon img = new ImageIcon(getClass().getResource("background.jpg"));
        // ��l�ƿ��ɹϤ��ﹳ
        imgCarb = new ImageIcon(getClass().getResource("crab.png"));
        imgCarb2 = new ImageIcon(getClass().getResource("crab2.png"));
        carb = new JLabel[6]; // �إ���ܿ��ɪ����Ұ}�C
        Catcher catcher = new Catcher();// ���Ҫ����г����ƥ��ť��
        for (int i = 0; i < 6; i++) { // �ˬd�}�C
            carb[i] = new JLabel(); // ��l�ƨC�@�Ӱ}�C����
            // �]�w���һP���ɹϤ��ۦP�j�p
            carb[i].setSize(imgCarb.getIconWidth(), imgCarb.getIconHeight());
            // �����ҼW�[�ƥ��ť��
            carb[i].addMouseListener(catcher);
            carb[i].addMouseListener(mouseCrab);
            getContentPane().add(carb[i]); // �W�[��ܿ��ɪ����Ҩ쵡��
        }
        
        carb[0].setLocation(253, 315); // �]�w�C�Ӽ��Ҫ���m
        carb[1].setLocation(333, 265);
        carb[2].setLocation(388, 311);
        carb[3].setLocation(362, 379);
        carb[4].setLocation(189, 368);
        carb[5].setLocation(240, 428);
        
        final JLabel backLabel = new JLabel(); // �إ���ܭI��������
        // �]�w���һP�I���Ϥ��ۦP�j�p
        backLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        // �]�w�������I���Ϥ��j�p
        setBounds(100, 100, img.getIconWidth(), img.getIconHeight() + 30);
        backLabel.setIcon(img); // �W�[�I�������
        getContentPane().add(backLabel); // �W�[�I�����Ҩ쵡��
        setCursor(cursor1);// �]�w�w�]�ϥβĤ@�ӹ��Х���
        addMouseListener(mouseCrab);// �����O�W�[���Шƥ��ť��
    }
    
    /**
     * �u�{���֤ߤ�k
     */
    public void run() {
        while (true) { // �ϥεL���`��
            try {
                Thread.sleep(1000); // �Ͻu�{��v1��
                int index = (int) (Math.random() * 6);// �����H�������ɯ���
                if (carb[index].getIcon() == null) {// �p�G���ɼ��ҨS���]�w�Ϥ�
                    carb[index].setIcon(imgCarb);// ���Ӽ��ҼW�[���ɹϤ�
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
