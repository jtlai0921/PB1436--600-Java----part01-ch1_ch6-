package com.zzk;

import java.awt.AWTException;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RefineryUtilities;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class PrintLineChartFrame extends JFrame {
    PrinterJob job = PrinterJob.getPrinterJob(); // ��o�C�L�ﹳ
    ChartPanel chartPanel = null;
    private Robot robot = null; // �ŧiRobot�ﹳ
    private BufferedImage buffImage = null; // �ŧi�w�Ĺϧιﹳ
    
    public static void main(String args[]) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintLineChartFrame frame = new PrintLineChartFrame();
                    RefineryUtilities.centerFrameOnScreen(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public PrintLineChartFrame() {
        super();
        setTitle("�C�L�E�X�u�Ϫ�");
        setBounds(0, 0, 500, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
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
                            int x = (int) (PrintLineChartFrame.this.getBounds()
                                    .getX()) + 8;// �I�����O�b�̹��W��X�y��
                            int y = (int) (PrintLineChartFrame.this.getBounds()
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
                                    imgHeight, PrintLineChartFrame.this);
                            return Printable.PAGE_EXISTS;
                        }
                    });
                    job.setJobName("�C�L�E�X�u�Ϫ�"); // �]�w�C�L�u�@���W��
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
        
        //
    }
    
    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();// �إ߸�ƶ��ﹳ
        ResultSet rs = QueryResultSet.gainRecord();// ��o�d�ߵ��G��
        try {
            while (rs.next()) {
                int value = rs.getInt(1);// ��o�����~��
                String sex = rs.getString(2);// ��o�ʧO
                String address = rs.getString(3);// ��o�a�}
                dataset.addValue(value, sex, address);// �V��ƶ��W�[���
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart("\n\n�έp�ӦۦU�����k�k���u�������~��", // �Ϫ����D�¤�r
                "�Ҧb����       ", // x�b�W�����Ҥ�r
                "�����~��", // y�b�W�����Ҥ�r
                dataset, // ��ƶ�
                PlotOrientation.VERTICAL, // ������V
                true, // ��ܹϨ�
                true, // ��ܻ�����r
                false // �������챵
                );
        chart.setBackgroundPaint(Color.white);// �]�w�I���C��
        CategoryPlot plot = (CategoryPlot) chart.getPlot();// ��o�Ϫ�CategoryPlot�ﹳ
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();// ��oy�b�����
        rangeAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// �]�wy�b��ܭȪ��r��
        rangeAxis.setLabelFont(new Font("����", Font.PLAIN, 12));// �]�wy�b�W���Ҥ�r���r��
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());// �]�wy�b��ܼзǪ���Ƴ椸
        CategoryAxis domainAxis = plot.getDomainAxis();// ��ox�b�����
        domainAxis.setTickLabelFont(new Font("Sans-serif", Font.PLAIN, 12));// �]�wx�b��ܸ�T���r��
        domainAxis.setLabelFont(new Font("�ө���", Font.PLAIN, 12));// �]�wx�b�W���Ҥ�r���r��
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions
                .createUpRotationLabelPositions(Math.PI / 6.0));// ���wx�b�W��ܸ�T����m
        TextTitle textTitle = chart.getTitle();// ��o���D�ﹳ
        textTitle.setFont(new Font("����", Font.PLAIN, 20));// �]�w���D���r��
        chart.getLegend().setItemFont(new Font("�ө���", Font.PLAIN, 12));// �]�w�Ϩү¤�r���r��
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);// �]�w�ϨҪ�����覡
        return chart;
    }
}
