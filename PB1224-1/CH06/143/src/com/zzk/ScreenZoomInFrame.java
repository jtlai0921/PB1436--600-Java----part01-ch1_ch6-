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
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class ScreenZoomInFrame extends JFrame {
    private PartZoomInPanel partZoomInPanel = null; // �ŧi�ϧέ��O�ﹳ
    private int pressPanelX = 0, pressPanelY = 0;// ���Ы��U�I��X�BY�y��
    private int pressX = 0, pressY = 0;// ���Ы��U�I�b�̹��W��X�BY�y��
    private int releaseX = 0, releaseY = 0;// ���������I�b�̹��W��X�BY�y��
    private Robot robot = null; // �ŧiRobot�ﹳ
    private BufferedImage buffImage = null; // �ŧi�w�Ĺϧιﹳ
    private BufferedImage zoomBuffImage = null; // �ŧi�w�Ĺϧιﹳ
    private boolean flag = false; // �ŧi�аO�ܼơA��true����ܿ�ܰϰ쪺�x�ΡA�_�h�����
    private boolean mouseFlag = false;// �i���j�ϧΪ��аO�ܼơA��true�ɶi���j�A�_�h����j
    private Rectangle rect = null;
    private Dimension dim = null;
    
    public static void main(String args[]) {
        ScreenZoomInFrame frame;
        try {
            frame = new ScreenZoomInFrame("�̹���j��");
            frame.setVisible(true);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
    }
    
    public ScreenZoomInFrame(String title) throws AWTException {
        super(title);
        getContentPane().addMouseListener(new MouseAdapter() {
        	public void mouseClicked(final MouseEvent e) {
        		if (e.getButton()==MouseEvent.BUTTON3) {
        			System.exit(0);// �������Хk��h�X
        		}
        	}
        });
        setAlwaysOnTop(true);
        partZoomInPanel = new PartZoomInPanel(); // �إ߹ϧέ��O�ﹳ
        Toolkit toolkit = getToolkit();
        dim = toolkit.getScreenSize();
        setBounds(0, 0, dim.width, dim.height - 30); // �]�w����j�p�M��m
        setUndecorated(true);
        getContentPane().add(partZoomInPanel, BorderLayout.CENTER);
        AWTUtilities.setWindowOpacity(this, 0.01f);
        robot = new Robot();// �إ�Robot�ﹳ
        rect = new Rectangle(0, 0, dim.width, dim.height);// �إ�Rectangle�ﹳ
        buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
        addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(final WindowEvent e) {
            }
            
            public void windowLostFocus(final WindowEvent e) {
                flag = false;// ���аO�ܼƵ����Ȭ�true
                mouseFlag = false;// �N�аO�]�w��false
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 0.01f);
                buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                partZoomInPanel.repaint();
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 1f);
                robot.mouseMove(200, 730);
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        });
        //
        partZoomInPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent e) { // ��������U�ƥ�
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 0.01f);
                buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                partZoomInPanel.repaint();
                AWTUtilities.setWindowOpacity(ScreenZoomInFrame.this, 1f);
                pressPanelX = e.getX(); // ��o���Ы��U�I��X�y��
                pressPanelY = e.getY();// ��o���Ы��U�I��Y�y��
                pressX = e.getXOnScreen() + 1;// ���Ы��U�I�b�̹��W��X�y�Х[1�A�Y�h����ܽu
                pressY = e.getYOnScreen() + 1;// ���Ы��U�I�b�̹��W��Y�y�Х[1�A�Y�h����ܽu
                flag = true;// ���аO�ܼƵ����Ȭ�true
                mouseFlag = false;// �N�аO�]�w��false
            }
            
            public void mouseReleased(final MouseEvent e) { // ����������ƥ�
                releaseX = e.getXOnScreen() - 1;// ���������I�b�̹��W��X�y�д�1�A�Y�h����ܽu
                releaseY = e.getYOnScreen() - 1;// ���������I�b�̹��W��Y�y�д�1�A�Y�h����ܽu
                if (releaseX - pressX > 0 && releaseY - pressY > 0) {
                    Rectangle rect = new Rectangle(pressX, pressY, releaseX
                            - pressX, releaseY - pressY);// �إ�Rectangle�ﹳ
                    zoomBuffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                }
                flag = false;// ���аO�ܼƵ����Ȭ�false
                mouseFlag = true;// �аO��true�A�i���j
                partZoomInPanel.repaint();// �I�spaint()��k�A��{��j
            }
            
            public void mouseClicked(final MouseEvent e) { // ����������ƥ�
                if (e.getButton() == MouseEvent.BUTTON3) {// �������Хk��
                    System.exit(0);// �h�X�t��
                }
                mouseFlag = false;// �N�аO�]�w��false�A����j�ϧ�
                partZoomInPanel.repaint();// �I�sPartZoomInPanel���O��paint()��k
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
    
    class PartZoomInPanel extends JPanel {// �إ߭��O���O
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(buffImage, 0, 0, this);// ø�s�ϧ�
            g2.setColor(Color.BLACK);
            if (flag) {
                float[] arr = { 5.0f }; // �إߵ�u�Ҧ����}�C
                BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL, 1.0f, arr, 0); // �إ߼e�׬O1�����Y��u���e�ﹳ
                g2.setStroke(stroke);// �]�w���e�ﹳ
                g2.drawRect(pressPanelX, pressPanelY, releaseX - pressX,
                        releaseY - pressY);// ø�s�x�ο��
            }
            
            if (mouseFlag) {// ���󬰯u
                int zoomX = pressPanelX - (releaseX - pressX) / 4;// ��j���eø�s�I��x�y��
                int zoomY = pressPanelY - (releaseY - pressY) / 4;// ��j���eø�s�I��y�y��
                if (zoomX <= 0) {
                    zoomX = 0;// �y�ЭȤp�󵥩�0�A���y�ЭȬ�0
                }
                if (zoomY <= 0) {
                    zoomY = 0;// �y�ЭȤp�󵥩�0�A���y�ЭȬ�0
                }
                g2.drawImage(zoomBuffImage, zoomX, zoomY,
                        (int) ((releaseX - pressX) * 1.5f),
                        (int) ((releaseY - pressY) * 1.5f), this);// ø�s��j�᪺���e
            }
        }
    }
    
}
