package com.zzk;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PartImageFrame extends JFrame {
    private Image img = null;  // �ŧi�ϧιﹳ
    private PartImagePanel imagePanel = null;  // �ŧi�ϧέ��O�ﹳ
    private int dx1, dy1, dx2, dy2;   // �ؼЯx�βĤ@�Ө��P�ĤG�Ө���X�BY�y��
    private int sx1, sy1, sx2, sy2;   // ���x�βĤ@�Ө��P�ĤG�Ө���X�BY�y��
    public static void main(String args[]) {
        PartImageFrame frame = new PartImageFrame();
        frame.setVisible(true);
    }
    public PartImageFrame() {
        super();
        URL imgUrl = PartImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        dx2 = sx2 = 340; // ��l�ƹϧΤj�p
        dy2 = sy2 = 200; // ��l�ƹϧΤj�p
        imagePanel = new PartImagePanel();  // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 355, 276); // �]�w����j�p�M��m
        this.add(imagePanel); // �b���餤����m�W�[�ϧέ��O�ﹳ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("½��ϧ�"); // �]�w������D
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JButton btn_h = new JButton();
        btn_h.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �U��3��{���X�Ω�洫sx1�Msx2����
                int x = sx1;
                sx1 = sx2;
                sx2 = x;
                imagePanel.repaint();  // ���s�I�s���O���O��paint()��k
            }
        });
        btn_h.setText("����½��");
        panel.add(btn_h);
        final JButton btn_v = new JButton();
        btn_v.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �U��3��{���X�Ω�洫sy1�Msy2����
                int y = sy1;
                sy1 = sy2;
                sy2 = y;
                imagePanel.repaint();// ���s�I�s���O���O��paint()��k
            }
        });
        btn_v.setText("����½��");
        panel.add(btn_v);
    }
    // �إ߭��O���O
    class PartImagePanel extends JPanel {
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �M��ø�ϤW�U�媺���e
            g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, this);// ø�s�ϧ�
        }
    }
}
