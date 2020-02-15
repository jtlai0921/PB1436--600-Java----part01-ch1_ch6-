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

public class ShearShapeFrame extends JFrame {
    int flag = 0; // ��0����ܭ�ϧΡA��1�ɦV�U�פ��A��2�ɦV�W�פ�
    ShearShapePanel shearShapePanel = new ShearShapePanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        ShearShapeFrame frame = new ShearShapeFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public ShearShapeFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�פ��ϧ�"); // ������D
        setBounds(100, 100, 338, 230); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(shearShapePanel); // �N���O���O����ҼW�[�쵡��e����
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_deasil = new JButton();
        btn_deasil.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // �U�פ��аO
                shearShapePanel.repaint();  // ���s�I�s���O��paint()��k
            }
        });
        btn_deasil.setText("�U�פ�");
        panel.add(btn_deasil);
        
        final JButton btn_widdershins = new JButton();
        btn_widdershins.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 2;           // �W�פ��аO
                shearShapePanel.repaint();// ���s�I�s���O��paint()��k
            }
        });
        btn_widdershins.setText("�W�פ�");
        panel.add(btn_widdershins);

        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // �٭�ϧ�
                shearShapePanel.repaint();// ���s�I�s���O��paint()��k
            }
        });
        btn_restore.setText("��    ��");
        panel.add(btn_restore);
    }
    
    class ShearShapePanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g; // ��oGraphics2D�ﹳ
            Rectangle2D.Float rect = new Rectangle2D.Float(120, 50, 80, 50);// �إ߯x�ιﹳ
            BasicStroke stroke = new BasicStroke(10); // �إ߼e�׬O10�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            g2.clearRect(0, 0, 338, 230);  // �M���즳���e
            if (flag == 0) {
                g2.draw(rect);// ø�s��x��
            } else if (flag == 1) {
                g2.shear(0.2,0.2);// �V�U�פ�
                g2.draw(rect);// ø�s�x��
            } else if (flag == 2) {
                g2.shear(-0.2,-0.2);// �V�W�פ�
                g2.draw(rect);// ø�s�x��
            }
        }
    }
}
