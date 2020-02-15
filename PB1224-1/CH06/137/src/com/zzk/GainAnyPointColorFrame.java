package com.zzk;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GainAnyPointColorFrame extends JFrame {
    
    private JTextField tf_blue;
    private JTextField tf_green;
    private JTextField tf_red;
    private JTextField tf_y;
    private JTextField tf_x;
    private static final long serialVersionUID = -486745172657329259L;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GainAnyPointColorFrame frame = new GainAnyPointColorFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public GainAnyPointColorFrame() {
        super();
        GainColor gc = new GainColor();// �إ�GainColor�ﹳ
        Thread thread = new Thread(gc);// �إ߽u�{�ﹳ
        thread.start();// �Ұʽu�{�ﹳ
        getContentPane().setLayout(null);
        setTitle("��o���Цb���N��m���C���");
        setBounds(100, 100, 300, 207);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JLabel label = new JLabel();
        label.setText("���ЩҦb�I��X�y�СG");
        label.setBounds(26, 21, 126, 25);
        getContentPane().add(label);
        
        final JLabel label_1 = new JLabel();
        label_1.setText("���ЩҦb�I��Y�y�СG");
        label_1.setBounds(26, 45, 126, 25);
        getContentPane().add(label_1);
        
        tf_x = new JTextField();
        tf_x.setBounds(155, 22, 103, 22);
        getContentPane().add(tf_x);
        
        tf_y = new JTextField();
        tf_y.setBounds(155, 46, 103, 22);
        getContentPane().add(tf_y);
        
        final JLabel label_2 = new JLabel();
        label_2.setText("�C�⪺Red�ȡG");
        label_2.setBounds(26, 79, 97, 18);
        getContentPane().add(label_2);
        
        final JLabel label_3 = new JLabel();
        label_3.setText("�C�⪺Green�ȡG");
        label_3.setBounds(26, 103, 111, 18);
        getContentPane().add(label_3);
        
        final JLabel label_4 = new JLabel();
        label_4.setText("�C�⪺Blue�ȡG");
        label_4.setBounds(26, 127, 109, 18);
        getContentPane().add(label_4);
        
        tf_red = new JTextField();
        tf_red.setBounds(155, 77, 103, 22);
        getContentPane().add(tf_red);
        
        tf_green = new JTextField();
        tf_green.setBounds(155, 101, 103, 22);
        getContentPane().add(tf_green);
        
        tf_blue = new JTextField();
        tf_blue.setBounds(155, 125, 103, 22);
        getContentPane().add(tf_blue);
        
    }
    
    class GainColor implements Runnable {
        @Override
        public void run() {
            while (true) {
                PointerInfo mi = MouseInfo.getPointerInfo();// ���Ы��Хثe��m��PointerInfo�ﹳ
                Point p = mi.getLocation();// ��o�̹��W��ܫ��Юy�Ъ�Point�ﹳ
                int x = p.x;// ��oX�y��
                int y = p.y;// ��oY�y��
                try {
                    Robot robot = new Robot();// �إ�Robot�ﹳ
                    Color color = robot.getPixelColor(x, y);// ��o�̹����w��m���C��ﹳ
                    int r = color.getRed();// ��o�C�⪺R��
                    int g = color.getGreen();// ��o�C�⪺G��
                    int b = color.getBlue();// ��o�C�⪺B��
                    tf_x.setText(String.valueOf(x));// ���X�y�Э�
                    tf_y.setText(String.valueOf(y));// ���Y�y�Э�
                    tf_red.setText(String.valueOf(r));// ����C�⪺R��
                    tf_green.setText(String.valueOf(g));// ����C�⪺G��
                    tf_blue.setText(String.valueOf(b));// ����C�⪺B��
                    Thread.sleep(10);// �u�{��v10�@��
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
