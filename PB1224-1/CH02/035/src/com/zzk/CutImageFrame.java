package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
public class CutImageFrame extends JFrame {
    private Image img = null; // �ŧi�ϧιﹳ
    private OldImagePanel oldImagePanel = null; // �ŧi�ϧέ��O�ﹳ
    private int pressPanelX = 0, pressPanelY = 0;// ���Ы��U�I��X�BY�y�� 
    private int pressX = 0, pressY = 0;// ���Ы��U�I�b�̹��W��X�BY�y��
    private int releaseX = 0, releaseY = 0;// ���������I�b�̹��W��X�BY�y��
    private Robot robot = null;  // �ŧiRobot�ﹳ
    private BufferedImage buffImage = null; // �ŧi�w�Ĺϧιﹳ
    private CutImagePanel cutImagePanel = new CutImagePanel(); // �إ�ø�s���ŵ��G�����O
    private boolean flag = false;  // �ŧi�аO�ܼơA��true����ܿ�ܰϰ쪺�x�ΡA�_�h�����
    public static void main(String args[]) {
        CutImageFrame frame = new CutImageFrame();
        frame.setVisible(true);
    }
    public CutImageFrame() {
        super();
        URL imgUrl = CutImageFrame.class.getResource("/img/image.jpg");// ��o�Ϥ��귽�����|
        img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�ϧθ귽
        oldImagePanel = new OldImagePanel(); // �إ߹ϧέ��O�ﹳ
        this.setBounds(200, 160, 355, 276); // �]�w����j�p�M��m
        final JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation((this.getWidth() / 2) - 10);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setLeftComponent(oldImagePanel);
        splitPane.setRightComponent(cutImagePanel);
        oldImagePanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) {  // ��������U�ƥ�
                pressPanelX = e.getX(); // ��o���Ы��U�I��X�y�� 
                pressPanelY = e.getY();// ��o���Ы��U�I��Y�y�� 
                pressX = e.getXOnScreen() + 1;// ���Ы��U�I�b�̹��W��X�y�Х[1�A�Y�h����ܽu
                pressY = e.getYOnScreen() + 1;// ���Ы��U�I�b�̹��W��Y�y�Х[1�A�Y�h����ܽu
                flag = true;// ���аO�ܼƵ����Ȭ�true
            }
            public void mouseReleased(final MouseEvent e) { // ����������ƥ�
                releaseX = e.getXOnScreen() - 1;// ���������I�b�̹��W��X�y�д�1�A�Y�h����ܽu
                releaseY = e.getYOnScreen() - 1;// ���������I�b�̹��W��Y�y�д�1�A�Y�h����ܽu
                try {
                    robot = new Robot();// �إ�Robot�ﹳ
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// �إ�Rectangle�ﹳ
                        buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                        cutImagePanel.repaint(); // �I�sCutImagePanel���O��paint()��k
                    }
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                flag = false;// ���аO�ܼƵ����Ȭ�false
            }
        });
        oldImagePanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {// ���Щ�ʨƥ�
                if (flag) {
                    releaseX = e.getXOnScreen();// ��o���������I�b�̹��W��X�y��
                    releaseY = e.getYOnScreen();// ��o���������I�b�̹��W��Y�y��
                    oldImagePanel.repaint();// �I�sOldImagePanel���O��paint()��k
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
        this.setTitle("���ŹϤ�"); // �]�w������D
    }
    
    class OldImagePanel extends JPanel {// �إ�ø�s��ϧΪ����O���O
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);// ø�s�ϧ�
            g2.setColor(Color.WHITE);
            if (flag) {
                float[] arr = { 5.0f }; // �إߵ�u�Ҧ����}�C
                BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // �إ߼e�׬O1�����Y��u���e�ﹳ
                g2.setStroke(stroke);// �]�w���e�ﹳ
                g2.drawRect(pressPanelX, pressPanelY, releaseX - pressX,
                        releaseY - pressY);// ø�s�x�ο��
            }
        }
    }
    
    class CutImagePanel extends JPanel {// �إ�ø�s���ŵ��G�����O���O
        public void paint(Graphics g) {
            g.clearRect(0, 0, this.getWidth(), this.getHeight());// �M��ø�ϤW�U�媺���e
            g.drawImage(buffImage, 0, 0, releaseX - pressX, releaseY - pressY,
                    this);// ø�s�ϧ�
        }
    }
}
