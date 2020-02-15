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
 * 時鐘面板
 * 
 * @author 張振坤
 */
@SuppressWarnings("serial")
public class ClockPanel extends JPanel {
    // 3個指標的粗細
    private static final BasicStroke HOURS_POINT_WIDTH = new BasicStroke(4);
    private static final BasicStroke MINUETES_POINT_WIDTH = new BasicStroke(3);
    private static final BasicStroke SEC_POINT_WIDTH = new BasicStroke(2);
    ImageIcon background;// 背景圖片對像
    private int centerX;// 中心座標
    private int centerY;// 中心座標
    private final static int secLen = 60; // 指標長度
    private final static int minuesLen = 55; // 指標長度
    private final static int hoursLen = 36; // 指標長度
    
    /**
     * 建構方法
     */
    public ClockPanel() {
        setToolTipText("小鍵碟+、-調整透明度，Crtl+Shift+X退出");// 提示資訊
        setOpaque(false);
        
        background = new ImageIcon(getClass().getResource("clock.jpg"));// 載入圖片
        int iconWidth = background.getIconWidth();// 獲得圖片大小
        centerX = iconWidth / 2;// 獲得圖片中間座標
        int iconHeight = background.getIconHeight();
        centerY = iconHeight / 2;// 獲得圖片中間座標
        setPreferredSize(new Dimension(iconWidth, iconHeight));
    }
    
    @Override
    public void paint(Graphics g) {// 重新定義父類別方法
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background.getImage(), 0, 0, this);// 繪製背景圖片
        Calendar calendar = Calendar.getInstance();// 獲得日曆對像
        int millisecond = calendar.get(MILLISECOND);// 獲得毫秒值
        int sec = calendar.get(SECOND);// 獲得秒值
        int minutes = calendar.get(MINUTE);// 獲得分值
        int hours = calendar.get(HOUR);// 獲得時值
        String dateStr = calendar.get(YEAR) + "年" + (calendar.get(MONTH) + 1)
                + "月" + calendar.get(DAY_OF_MONTH) + "日";// 目前日期字串
        int weekDay = calendar.get(DAY_OF_WEEK) - 1;// 目前星期值
        String weekStr = "";
        if (weekDay == 0) {
            weekStr = "星期日";
        } else {
            weekStr = "星期" + weekDay;
        }
        
        {// 繪製日期與星期
            g2.setColor(Color.BLACK);// 設定背景色
            g2.setFont(new Font("楷體", Font.BOLD, 12));// 設定字體
            // 獲得字體著色上下文
            FontRenderContext fontRenderContext = g2.getFontRenderContext();
            // 計算日期字串的邊界大小
            Rectangle2D dateStrBounds = getFont().getStringBounds(dateStr,
                    fontRenderContext);
            // 在時鐘下半部繪製日期
            g2.drawString(dateStr,
                    (int) (centerX - dateStrBounds.getWidth() / 2),
                    centerY + 20);
            // 改變字體大小
            g2.setFont(new Font("楷體", Font.BOLD, 14));
            // 計算星期字串邊界大小
            Rectangle2D weekStrBounds = getFont().getStringBounds(weekStr,
                    fontRenderContext);
            // 繪製星期字串
            g2.drawString(weekStr,
                    (int) (centerX - weekStrBounds.getWidth() / 2),
                    centerY + 35);
        }
        double secAngle = (60 - sec) * 6 - (millisecond / 150); // 秒針角度
        int minutesAngle = (60 - minutes) * 6;// 分針角度
        int hoursAngle = (12 - hours) * 360 / 12 - (minutes / 2);// 時針角度
        // 計算秒針、分針、時針指向的座標
        int secX = (int) (secLen * Math.sin(Math.toRadians(secAngle)));// 秒針指向點的X座標
        int secY = (int) (secLen * Math.cos(Math.toRadians(secAngle))); // 秒針指向點的Y座標
        int minutesX = (int) (minuesLen * Math
                .sin(Math.toRadians(minutesAngle))); // 分針指向點的X座標
        int minutesY = (int) (minuesLen * Math
                .cos(Math.toRadians(minutesAngle))); // 分針指向點的Y座標
        int hoursX = (int) (hoursLen * Math.sin(Math.toRadians(hoursAngle))); // 時針指向點的X座標
        int hoursY = (int) (hoursLen * Math.cos(Math.toRadians(hoursAngle))); // 時針指向點的Y座標
        // 分別繪製時針、分針、秒針
        g2.setStroke(HOURS_POINT_WIDTH);// 設定時針的寬度
        g2.setColor(Color.BLACK);// 設定時針的顏色
        g2.drawLine(centerX, centerY, centerX - hoursX, centerY - hoursY);// 繪製時針
        g2.setStroke(MINUETES_POINT_WIDTH);// 設定分針的寬度
        if (minutesAngle != hoursAngle) // 分針、時針重疊變色
            g2.setColor(new Color(0x2F2F2F));// 設定未重疊時的顏色
        else {
            g2.setColor(Color.GREEN);// 設定重疊時的顏色
        }
        g2.drawLine(centerX, centerY, centerX - minutesX, centerY - minutesY);// 繪製分針
        g2.setStroke(SEC_POINT_WIDTH);// 設定秒針的寬度
        if (secAngle != hoursAngle && secAngle != minutesAngle)// 分針、時針、秒針重疊變色
            g2.setColor(Color.ORANGE);// 設定未重疊時的顏色
        else {
            g2.setColor(Color.GREEN);// 設定重疊時的顏色
        }
        // 繪製3個指標的中心圓和秒針
        g2.fillOval(centerX - 5, centerY - 5, 10, 10);// 繪製中心圓
        g2.drawLine(centerX, centerY, centerX - secX, centerY - secY);// 繪製秒針
        g2.drawLine(centerX + 1, centerY + 1, centerX - secX + 1, centerY
                - secY + 1);// 繪製秒針
    }
}
