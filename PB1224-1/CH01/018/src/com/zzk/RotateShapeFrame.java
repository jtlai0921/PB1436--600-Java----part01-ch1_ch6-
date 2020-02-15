package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RotateShapeFrame extends JFrame {
    int flag = 0; // ��0����ܭ�ϧΡA��1�ɶ��ɰw����A��2�ɰf�ɰw����
    double rotateValue = 0.0;  // ���ܤj���ɰw����A���ܤp�f�ɰw����
    RotateShapePanel rotateShapePanel = new RotateShapePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        RotateShapeFrame frame = new RotateShapeFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public RotateShapeFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("����ϧ�"); // ������D
        setBounds(100, 100, 338, 220); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(rotateShapePanel); // �N���O���O����ҼW�[�쵡��e����
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_deasil = new JButton();
        btn_deasil.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // ���ɰw�аO
                rotateValue += 0.1;   // ���ɰw�����
                rotateShapePanel.repaint();  // ���s�I�s���O��paint()��k
            }
        });
        btn_deasil.setText("���ɰw");
        panel.add(btn_deasil);
        
        final JButton btn_widdershins = new JButton();
        btn_widdershins.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // �f�ɰw�аO
                rotateValue -= 0.1; // �f�ɰw�����
                rotateShapePanel.repaint();// ���s�I�s���O��paint()��k
            }
        });
        btn_widdershins.setText("�f�ɰw");
        panel.add(btn_widdershins);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // �٭�ϧ�
                rotateValue = 0;
                rotateShapePanel.repaint();// ���s�I�s���O��paint()��k
            }
        });
        btn_restore.setText("��    ��");
        panel.add(btn_restore);
    }
    
    class RotateShapePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g; // ��oGraphics2D�ﹳ
            Rectangle2D.Float rect = new Rectangle2D.Float(40, 40, 80, 50);// �إ߯x�ιﹳ
            BasicStroke stroke = new BasicStroke(10); // �إ߼e�׬O10�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            g2.clearRect(0, 0, 338, 220);  // �M���즳���e
            if (flag == 0) {
                g2.draw(rect);// ø�s��x��
            } else if (flag == 1) {
                g2.rotate(rotateValue);// ���ɰw����
                g2.draw(rect);// ø�s�x��
            } else if (flag == 2) {
                g2.rotate(rotateValue);// �f�ɰw����
                g2.draw(rect);// ø�s�x��
            }
        }
    }
}
