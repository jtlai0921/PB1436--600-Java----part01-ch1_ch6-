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

public class TranslationAxisFrame extends JFrame {
    int flag = 0; // ��0���٭�y�жb�A��1�ɥ����y�жb
    TranslationAxisPanel axisPanel = new TranslationAxisPanel(); // �إ߭��O���O�����
    public static void main(String args[]) { // �D��k
        TranslationAxisFrame frame = new TranslationAxisFrame(); // �إߵ������O�����
        frame.setVisible(true); // ��ܵ���
    }
    
    public TranslationAxisFrame() {
        super(); // �I�s�W���O���غc��k
        setTitle("�����y�жb"); // ������D
        setBounds(100, 100, 338, 230); // ���骺��ܦ�m�M�j�p
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���������覡
        add(axisPanel); // �N���O���O����ҼW�[�쵡��e����
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(30);
        panel.setLayout(flowLayout);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton btn_deasil = new JButton();
        btn_deasil.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 1;             // �����y�жb�аO
                axisPanel.repaint();  // ���s�I�s���O��paint()��k
            }
        });
        btn_deasil.setText("�����y�жb");
        panel.add(btn_deasil);
        
        final JButton btn_restore = new JButton();
        btn_restore.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                flag = 0;           // �٭�y�жb�аO
                axisPanel.repaint();// ���s�I�s���O��paint()��k
            }
        });
        btn_restore.setText("�٭�y�жb");
        panel.add(btn_restore);
    }
    
    class TranslationAxisPanel extends JPanel { // �إߤ������O���O
        public void paint(Graphics g) { // ���s�w�qpaint()��k
            Graphics2D g2 = (Graphics2D) g; // ��oGraphics2D�ﹳ
            Rectangle2D.Float rect = new Rectangle2D.Float(10, 10, 80, 50);// �إ߯x�ιﹳ
            BasicStroke stroke = new BasicStroke(10); // �إ߼e�׬O10�����e�ﹳ
            g2.setStroke(stroke);// �]�w���e�ﹳ
            g2.clearRect(0, 0, 338, 230);  // �M���즳���e
            if (flag == 0) {
                g2.translate(0, 0);// �����y�жb
                g2.draw(rect);// ø�s�x��
            } else if (flag == 1) {
                g2.translate(120, 60);// �����y�жb
                g2.draw(rect);// ø�s�x��
            }
        }
    }
}
