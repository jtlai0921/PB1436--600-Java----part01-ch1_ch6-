package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class CaptureScreenImageFrame extends JFrame {
    private PartZoomInPanel partZoomInPanel = null;// �ŧi�ϧέ��O�ﹳ
    private int pressPanelX = 0, pressPanelY = 0;// ���Ы��U�I��X�BY�y��
    private int pressX = 0, pressY = 0;// ���Ы��U�I�b�̹��W��X�BY�y��
    private int releaseX = 0, releaseY = 0;// ���������I�b�̹��W��X�BY�y��
    private Robot robot = null;// �ŧiRobot�ﹳ
    private BufferedImage buffImage = null;// �ŧi�w�Ĺϧιﹳ
    private boolean flag = false;// �ŧi�аO�ܼơA��true����ܿ�ܰϰ쪺�x�ΡA�_�h�����
    private Rectangle rect = null;
    
    public static void main(String args[]) {
        CaptureScreenImageFrame frame;
        try {
            frame = new CaptureScreenImageFrame("�̹���ϵ{��");
            frame.setVisible(true);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public CaptureScreenImageFrame(String title) throws AWTException {
        super(title);
        setAlwaysOnTop(true);
        partZoomInPanel = new PartZoomInPanel(); // �إ߹ϧέ��O�ﹳ
        Toolkit toolkit = getToolkit();
        Dimension dim = toolkit.getScreenSize();
        setBounds(0, 0, dim.width, dim.height - 30); // �]�w����j�p�M��m
        setUndecorated(true);// ��������׹�
        AWTUtilities.setWindowOpacity(this, 0.01f);// �]�w����z��
        getContentPane().add(partZoomInPanel, BorderLayout.CENTER);
        robot = new Robot();// �إ�Robot�ﹳ
        rect = new Rectangle(0, 0, dim.width, dim.height);// �إ�Rectangle�ﹳ
        buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
        partZoomInPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { // ��������U�ƥ�
                buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                partZoomInPanel.repaint();
                AWTUtilities.setWindowOpacity(CaptureScreenImageFrame.this, 1f);// �]�w���鬰���z��
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
                    if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                        Rectangle rect = new Rectangle(pressX, pressY, releaseX
                                - pressX, releaseY - pressY);// �إ�Rectangle�ﹳ
                        buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                        FileOutputStream out = new FileOutputStream("c:/zzkkee.jpg");// �x�s��m����X�y�ﹳ
                        ImageIO.write(buffImage, "jpg", out);// �g�J�Ϻ�
                        out.flush();
                        out.close();
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                flag = false;   // ���аO�ܼƵ����Ȭ�false
                if (e.getButton() == MouseEvent.BUTTON1) {// �������Хk��
                    CaptureScreenImageFrame.this.dispose();
                    CaptureScreenImageFrame frame;
                    try {
                        frame = new CaptureScreenImageFrame("�̹���ϵ{��");
                        AWTUtilities.setWindowOpacity(frame, 0.01f);
                        frame.setVisible(true);
                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            
            public void mouseClicked(final MouseEvent e) { // ����������ƥ�
                if (e.getButton() == MouseEvent.BUTTON3) {// �������Хk��
                    System.exit(0);// �h�X�t��
                }
            }
        });
        partZoomInPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {// ���Щ�ʨƥ�
                if (flag) {
                    releaseX = e.getXOnScreen();// ��o���������I�b�̹��W��X�y��
                    releaseY = e.getYOnScreen();// ��o���������I�b�̹��W��Y�y��
                    partZoomInPanel.repaint();// �I�sPartZoomInPanel���O��paint()��k
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �]�w���������Ҧ�
    }
    
    class PartZoomInPanel extends JPanel {// �إ�ø�s��ϧΪ����O���O
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(buffImage, 0, 0, PartZoomInPanel.this);
            g2.setColor(Color.BLACK);
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
    
}
