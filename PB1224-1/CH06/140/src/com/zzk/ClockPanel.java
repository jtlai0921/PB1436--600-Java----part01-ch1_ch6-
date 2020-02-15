package com.zzk;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * �������O
 * 
 * @author �i���[
 */
@SuppressWarnings("serial")
public class ClockPanel extends JPanel {
    // 3�ӫ��Ъ��ʲ�
    private static final BasicStroke HOURS_POINT_WIDTH = new BasicStroke(4);
    private static final BasicStroke MINUETES_POINT_WIDTH = new BasicStroke(3);
    private static final BasicStroke SEC_POINT_WIDTH = new BasicStroke(2);
    ImageIcon background;// �I���Ϥ��ﹳ
    private int centerX;// ���߮y��
    private int centerY;// ���߮y��
    private final static int secLen = 60; // ���Ъ���
    private final static int minuesLen = 55; // ���Ъ���
    private final static int hoursLen = 36; // ���Ъ���
    
    /**
     * �غc��k
     */
    public ClockPanel() {
        setToolTipText("�p���+�B-�վ�z���סACrtl+Shift+X�h�X");// ���ܸ�T
        setOpaque(false);
        
        background = new ImageIcon(getClass().getResource("clock.jpg"));// ���J�Ϥ�
        int iconWidth = background.getIconWidth();// ��o�Ϥ��j�p
        centerX = iconWidth / 2;// ��o�Ϥ������y��
        int iconHeight = background.getIconHeight();
        centerY = iconHeight / 2;// ��o�Ϥ������y��
        setPreferredSize(new Dimension(iconWidth, iconHeight));
    }
    
    @Override
    public void paint(Graphics g) {// ���s�w�q�����O��k
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background.getImage(), 0, 0, this);// ø�s�I���Ϥ�
        Calendar calendar = Calendar.getInstance();// ��o���ﹳ
        int millisecond = calendar.get(MILLISECOND);// ��o�@���
        int sec = calendar.get(SECOND);// ��o���
        int minutes = calendar.get(MINUTE);// ��o����
        int hours = calendar.get(HOUR);// ��o�ɭ�
        String dateStr = calendar.get(YEAR) + "�~" + (calendar.get(MONTH) + 1)
                + "��" + calendar.get(DAY_OF_MONTH) + "��";// �ثe����r��
        int weekDay = calendar.get(DAY_OF_WEEK) - 1;// �ثe�P����
        String weekStr = "";
        if (weekDay == 0) {
            weekStr = "�P����";
        } else {
            weekStr = "�P��" + weekDay;
        }
        
        {// ø�s����P�P��
            g2.setColor(Color.BLACK);// �]�w�I����
            g2.setFont(new Font("����", Font.BOLD, 12));// �]�w�r��
            // ��o�r��ۦ�W�U��
            FontRenderContext fontRenderContext = g2.getFontRenderContext();
            // �p�����r�ꪺ��ɤj�p
            Rectangle2D dateStrBounds = getFont().getStringBounds(dateStr,
                    fontRenderContext);
            // �b�����U�b��ø�s���
            g2.drawString(dateStr,
                    (int) (centerX - dateStrBounds.getWidth() / 2),
                    centerY + 20);
            // ���ܦr��j�p
            g2.setFont(new Font("����", Font.BOLD, 14));
            // �p��P���r����ɤj�p
            Rectangle2D weekStrBounds = getFont().getStringBounds(weekStr,
                    fontRenderContext);
            // ø�s�P���r��
            g2.drawString(weekStr,
                    (int) (centerX - weekStrBounds.getWidth() / 2),
                    centerY + 35);
        }
        double secAngle = (60 - sec) * 6 - (millisecond / 150); // ��w����
        int minutesAngle = (60 - minutes) * 6;// ���w����
        int hoursAngle = (12 - hours) * 360 / 12 - (minutes / 2);// �ɰw����
        // �p���w�B���w�B�ɰw���V���y��
        int secX = (int) (secLen * Math.sin(Math.toRadians(secAngle)));// ��w���V�I��X�y��
        int secY = (int) (secLen * Math.cos(Math.toRadians(secAngle))); // ��w���V�I��Y�y��
        int minutesX = (int) (minuesLen * Math
                .sin(Math.toRadians(minutesAngle))); // ���w���V�I��X�y��
        int minutesY = (int) (minuesLen * Math
                .cos(Math.toRadians(minutesAngle))); // ���w���V�I��Y�y��
        int hoursX = (int) (hoursLen * Math.sin(Math.toRadians(hoursAngle))); // �ɰw���V�I��X�y��
        int hoursY = (int) (hoursLen * Math.cos(Math.toRadians(hoursAngle))); // �ɰw���V�I��Y�y��
        // ���Oø�s�ɰw�B���w�B��w
        g2.setStroke(HOURS_POINT_WIDTH);// �]�w�ɰw���e��
        g2.setColor(Color.BLACK);// �]�w�ɰw���C��
        g2.drawLine(centerX, centerY, centerX - hoursX, centerY - hoursY);// ø�s�ɰw
        g2.setStroke(MINUETES_POINT_WIDTH);// �]�w���w���e��
        if (minutesAngle != hoursAngle) // ���w�B�ɰw���|�ܦ�
            g2.setColor(new Color(0x2F2F2F));// �]�w�����|�ɪ��C��
        else {
            g2.setColor(Color.GREEN);// �]�w���|�ɪ��C��
        }
        g2.drawLine(centerX, centerY, centerX - minutesX, centerY - minutesY);// ø�s���w
        g2.setStroke(SEC_POINT_WIDTH);// �]�w��w���e��
        if (secAngle != hoursAngle && secAngle != minutesAngle)// ���w�B�ɰw�B��w���|�ܦ�
            g2.setColor(Color.ORANGE);// �]�w�����|�ɪ��C��
        else {
            g2.setColor(Color.GREEN);// �]�w���|�ɪ��C��
        }
        // ø�s3�ӫ��Ъ����߶�M��w
        g2.fillOval(centerX - 5, centerY - 5, 10, 10);// ø�s���߶�
        g2.drawLine(centerX, centerY, centerX - secX, centerY - secY);// ø�s��w
        g2.drawLine(centerX + 1, centerY + 1, centerX - secX + 1, centerY
                - secY + 1);// ø�s��w
    }
}
