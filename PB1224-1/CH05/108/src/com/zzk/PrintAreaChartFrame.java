package com.zzk;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class PrintAreaChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��o�C�L�ﹳ
    ChartPanel chartPanel = null;
    private Robot robot = null; // �ŧiRobot�ﹳ
    private BufferedImage buffImage = null; // �ŧi�w�Ĺϧιﹳ
    
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintAreaChartFrame frame = new PrintAreaChartFrame();
                    RefineryUtilities.centerFrameOnScreen(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintAreaChartFrame() {
        super();
        setTitle("�C�L�ϰ�Ϫ�");
        setBounds(0, 0, 820, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        XYDataset xydataset = createDataset();              // �إ߸�ƶ��ﹳ
        JFreeChart chart = createChart(xydataset);          // �إ�JFreeChart�ﹳ
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
        chartPanel.setMouseWheelEnabled(true);
        getContentPane().add(chartPanel);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        try {
            robot = new Robot();
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
        final JButton button = new JButton();
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrinterJob job = PrinterJob.getPrinterJob(); // ��o�C�L�ﹳ
                    if (!job.printDialog()) // �}�ҦC�L��͵���
                        return;// �����C�L��͵������������s�������C�L��͵��������{��������
                    job.setPrintable(new Printable() {
                        // ��{print()��k�Aø�s�C�L���e
                        public int print(Graphics graphics, PageFormat pf,
                                int pageIndex) throws PrinterException {
                            if (pageIndex > 0)
                                return Printable.NO_SUCH_PAGE;
                            Graphics2D g2 = (Graphics2D) graphics; // ��o�ϧΤW�U��ﹳ
                            int x = (int) (PrintAreaChartFrame.this.getBounds()
                                    .getX()) + 8;// �I�����O�b�̹��W��X�y��
                            int y = (int) (PrintAreaChartFrame.this.getBounds()
                                    .getY()) + 30;// �I�����O�b�̹��W��Y�y��
                            int w = (int) chartPanel.getBounds().getWidth();// �Ϫ��e��
                            int h = (int) chartPanel.getBounds().getHeight();// �Ϫ�����
                            Rectangle rect = new Rectangle(x, y, w, h);// �إ�Rectangle�ﹳ
                            buffImage = robot.createScreenCapture(rect);// ��o�w�Ĺϧιﹳ
                            int imgWidth = buffImage.getWidth();// �ϧΪ��e��
                            int imgHeight = buffImage.getHeight();// �ϧΪ�����
                            float wh = imgWidth / imgHeight;// �ϧμe����
                            int printX = (int) pf.getImageableX();// ��o�i�C�L�ϰ쪺x�y��
                            int printY = (int) pf.getImageableY();// ��o�i�C�L�ϰ쪺y�y��
                            int width = (int) pf.getImageableWidth();// ��o�i�C�L�ϰ쪺�e��
                            int height = (int) pf.getImageableHeight();// ��o�i�C�L�ϰ쪺����
                            if (imgWidth > width) { // �p�G�e�j��i�C�L�ϰ�
                                imgWidth = width;
                                imgHeight = (int) (imgHeight * wh);
                            }
                            if (imgHeight > height) { // �p�G���j��i�C�L�ϰ�
                                imgHeight = height;
                                imgWidth = (int) (imgWidth * wh);
                            }
                            g2.drawImage(buffImage, printX, printY, imgWidth,
                                    imgHeight, PrintAreaChartFrame.this);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("�C�L�ϰ�Ϫ�"); // �]�w�C�L�u�@���W��
                    job.print(); // �I�sprint()��k�}�l�C�L
                } catch (PrinterException ee) {
                    ee.printStackTrace();
                }
            }
        });
        button.setText("��    �L");
        button.setBounds(28, 56, 86, 28);
        final JButton button_1 = new JButton();
        panel.add(button_1);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h    �X");
        button_1.setBounds(151, 56, 86, 28);
    }
    
    
    private XYDataset createDataset() {
        long value = 0;
        Day day = new Day(1, 1, 2009);// ��o2009�~1��1�骺Day�ﹳ
        long seed = System.currentTimeMillis();// ��o�ثe�ɶ����@���
        Random ran = new Random(seed);// �إ��H���ƺؤl��seed��Random�ﹳ
        TimeSeries soft = new TimeSeries("�����޹Ϯ�");// �إ߮ɶ��ǦC
        for (int i = 0; i < 365; i++) {                 //�W�[�@�~365�Ѫ����
            value += ran.nextInt() / 10000;// �H����o���
            soft.add(day, value);// �W�[���
            day = (Day) day.next();// ��o�U�@�Ӥ����Day�ﹳ
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection(soft);// �إ߸�ƶ��ﹳ
        return dataset;
    }

    @SuppressWarnings("deprecation")
    private JFreeChart createChart(XYDataset xydataset) {
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 24));//�]�w���D�r��
        standardChartTheme.setRegularFont(new Font("�ө���", Font.BOLD, 14));//�]�w�ϨҪ��r��
        standardChartTheme.setLargeFont(new Font("�ө���", Font.BOLD, 18));//�]�w�b�V���r��
        ChartFactory.setChartTheme(standardChartTheme);//�]�w�D�D�˦�
        JFreeChart jfreechart = ChartFactory.createXYAreaChart(
                "�����޹ϮѡA�~�רϥΪ̺��N�ײέp", // �Ϫ���D
                "�~�έp���", // X�b���D
                "�ϥΪ̺��N��", // Y�b���D
                xydataset, // �s�Ϫ���ƶ�
                PlotOrientation.VERTICAL, // �w�q�ϰ�Ϫ���V������
                false,      // �O�_��ܹϨҼлx
                true,       // �O�_��ܴ��ܸ�T
                false);     // �O�_�䴩�W�챵
        jfreechart.setBackgroundPaint(Color.PINK); // �]�w�I��
        XYPlot xyplot = (XYPlot) jfreechart.getPlot(); //��oXYPlot�ﹳ
        xyplot.setDomainGridlinePaint(Color.GREEN);// �]�w�Ϫ����u���C��
        xyplot.setDomainGridlineStroke(new BasicStroke(1f));// �]�w����u���ʲ�
        XYPlot plot = jfreechart.getXYPlot();// ��o�Ϫ�ø�s�ݩ�
        // �إ߫��w�˦�������榡�ﹳ
        DateFormat format = new SimpleDateFormat("MM���");
        DateAxis domainAxis = new DateAxis("2009�~�έp���           ");// �إ߮ɶ��b�ﹳ
        DateTickUnit dtu = new DateTickUnit(DateTickUnit.DAY, 29, format);
        domainAxis.setTickUnit(dtu);       // �]�w��b�W���ɶ���ת���ܮ榡
        domainAxis.setLowerMargin(0.0); // �]�w�Ϫ�ť�
        domainAxis.setUpperMargin(0.0); // �]�w�Ϫ�ť�
        domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 14)); // �]�w�b�аO�r��
        domainAxis.setLabelFont(new Font("�ө���", Font.ITALIC, 20));   // �]�w��b�r��
        plot.setDomainAxis(domainAxis); // ��ø���ݩʼW�[��b�ﹳ
        plot.setForegroundAlpha(0.5f);// �]�w�e���z���׬�50%
        return jfreechart;
    }
    
    /////
}
