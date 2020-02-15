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

public class ZoomShapeFrame extends JFrame {
    int flag = 0; // ��0����ܭ�j�p�A��1�ɩ�j�ϧΡA��2���Y�p�ϧ�
    ZoomShapePanel zoomPanel = new ZoomShapePanel(); // �إ߭��O���O�����
    
    public static void main(String args[]) { // �D��k
        ZoomShapeFrame frame = new ZoomShapeFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public ZoomShapeFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�Y��ϧ�"); // ������D
        setBounds(100, 100, 338, 220); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(zoomPanel); // �N���O���O����ҼW�[�쵡��e����
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_zoomin = new JButton();
        btn_zoomin.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // ��j�ϧμаO
                zoomPanel.repaint();  // ���s�I�s���O��paint()��k
            }
        });
        btn_zoomin.setText("��    �j");
        panel.add(btn_zoomin);
        
        final JButton btn_zoomout = new JButton();
        btn_zoomout.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // �Y�p�ϧμаO
                zoomPanel.repaint();// ���s�I�s���O��paint()��k
            }
        });
        btn_zoomout.setText("�Y    �p");
        panel.add(btn_zoomout);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // �٭�ϧ�
                zoomPanel.repaint();// ���s�I�s���O��paint()��k
            }
        });
        btn_restore.setText("��    ��");
        panel.add(btn_restore);
    }
    
    class ZoomShapePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g; // ��oGraphics2D�ﹳ
            Rectangle2D.Float rect = new Rectangle2D.Float(120, 50, 80, 50);// �إ߯x�ιﹳ
            BasicStroke stroke = new BasicStroke(10); // �إ߼e�׬O10�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            g2.clearRect(0, 0, 338, 220);  // �M���즳���e
            if (flag == 0) {
                g2.draw(rect);// ø�s��x��
            } else if (flag == 1) {
                g2.scale(1.3, 1.3);// ��j1.3��
                g2.draw(rect);// ø�s�x��
            } else if (flag == 2) {
                g2.scale(0.5, 0.5);// �Y�p0.5��
                g2.draw(rect);// ø�s�x��
            }
        }
    }
}
