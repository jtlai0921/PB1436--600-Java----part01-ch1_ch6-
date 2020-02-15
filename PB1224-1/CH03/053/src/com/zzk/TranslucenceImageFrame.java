package com.zzk;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TranslucenceImageFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private TranslucenceImagePanel translucencePanel = null; // �ŧi�ϧέ��O�ﹳ
    private AlphaComposite alpha = AlphaComposite.SrcOver.derive(1.0f);// �إߪ�ܤ��z����AlphaComposite�ﹳ
    public static void main(String args[]) {
        TranslucenceImageFrame frame = new TranslucenceImageFrame();
        frame.setVisible(true);
    }
    
    public TranslucenceImageFrame() {
        super();
        URL imgUrl = TranslucenceImageFrame.class.getResource("/img/imag.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        translucencePanel = new TranslucenceImagePanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 316, 237); // �]�w����j�p�M��m
        this.add(translucencePanel); // �b����W�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("�Ϥ��b�z���S��"); // �]�w������D

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(0.5f);// ��o��ܥb�z����AlphaComposite�ﹳ
                translucencePanel.repaint();// �I�spaint()��k
            }
        });
        button.setText("�b�z��");
        panel.add(button);

        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                alpha = AlphaComposite.SrcOver.derive(1.0f);// ��o��ܤ��z����AlphaComposite�ﹳ
                translucencePanel.repaint();// �I�spaint()��k
            }
        });
        button_1.setText("���z��");
        panel.add(button_1);
    }
    
    // �إ߭��O���O
    class TranslucenceImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;// ��oGraphics2D�ﹳ
            g2.clearRect(0, 0,  getWidth(), getHeight());// �M��ø�ϤW�U�媺���e
            g2.setComposite(alpha);// ���wAlphaComposite�ﹳ
            g2.drawImage(img, 0, 0,  getWidth(), getHeight(), this);// ø�s�ϧ�
        }
        
    }
}
