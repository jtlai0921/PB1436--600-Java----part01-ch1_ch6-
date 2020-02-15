package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;

/**
 * @author �i���[
 */
@SuppressWarnings("serial")
public class PrintLineReportFrame extends JFrame implements Printable {
    private PageFormat pf;
    private PreviewCanvas canvas;
    private boolean isPreview = false; // �O�_�i�H�C�L
    private boolean previewFlag = false; // �O�_�w�g�i��w��
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintLineReportFrame frame = new PrintLineReportFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public PrintLineReportFrame() {
        super();
        
        CategoryDataset dataset = createDataset();// �إ߸�ƶ��ﹳ
        JFreeChart chart = createChart(dataset);// �إ߹Ϫ�ﹳ
        String path = System.getProperty("user.dir") + "/src/chartImg/chart.jpg";// �Ϫ��x�s��m
        try {
            ChartUtilities.writeChartAsJPEG(new FileOutputStream(path), chart, 450, 360);// �N�Ϫ��x�s���Ϥ�
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        
        addWindowListener(new WindowAdapter() {
            public void windowActivated(final WindowEvent e) {
                if (previewFlag) {
                    isPreview = true;// �]�w���i�H�C�L
                    canvas.repaint();// �I�spaint()��k
                }
            }
        });
        addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {// ����j�p���ܮɰ���
                if (previewFlag) {
                    isPreview = true;// �]�w���i�H�C�L
                    canvas.repaint();// �I�spaint()��k
                }
            }
        });
        getContentPane().setBackground(new Color(232, 162, 255));
        setTitle("�C�L�a�E�X�u�Ϫ�����");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pf = new PageFormat();
        // pf.setOrientation(PageFormat.LANDSCAPE);// �C�L�_�l�I,�]�N�O�C�L��V
        canvas = new PreviewCanvas();
        
        this.setSize(new Dimension(633, 931));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        setLayout(borderLayout);
        add(canvas, BorderLayout.CENTER);
        
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton previewButton = new JButton();
        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �C�L�w��
                if (QueryResultSet.gainRecord() != null) {
                    isPreview = true;// ��ܥi�H�C�L
                    previewFlag = true;// �]�w���w�g�C�L�w��
                    PrinterJob job = PrinterJob.getPrinterJob();// ��o�C�L�ﹳ
                    pf = job.pageDialog(pf);// ��ܭק�PageFormat��Ҫ���͵���
                    canvas.repaint();// �I�spaint()��k
                }
            }
        });
        previewButton.setText("�w������");
        panel.add(previewButton);
        
        final JButton printButton = new JButton();
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!previewFlag) {
                    return;
                }
                PrinterJob job = PrinterJob.getPrinterJob(); // ��oPrinterJob��H�����
                if (!job.printDialog())// �}�ҦC�L��͵���
                    return;
                // �]�w�C�L���e
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat,
                            int pageIndex) throws PrinterException {
                        isPreview = true; // �]�w�i�H�C�L
                        if (pageIndex < 1) {
                            printPicture(graphics, pageFormat, pageIndex); // ø�s�C�L���e
                            return Printable.PAGE_EXISTS;
                        } else {
                            return Printable.NO_SUCH_PAGE;
                        }
                    }
                    
                });
                job.setJobName("�C�L����");
                try {
                    job.print();// �I�sprint()��k�A��{�C�L
                } catch (PrinterException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
        printButton.setText("�C�L����");
        panel.add(printButton);
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
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        printPicture(graphics, pageFormat, pageIndex);// ø�s�C�L���e
        return Printable.PAGE_EXISTS;// �Ǧ^PAGE_EXISTS
    }
    
    // �e��
    class PreviewCanvas extends Canvas {
        public void paint(Graphics g) {
            try {
                super.paint(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.translate(10, 10);// ����ø�ϤW�U��
                int x = (int) (pf.getImageableX() - 1);// ��o�i�C�L�ϰ쪺x�y�а����A�Ω�ø�s��u
                int y = (int) (pf.getImageableY() - 1);// ��o�i�C�L�ϰ쪺y�y�а��W�A�Ω�ø�s��u
                int width = (int) (pf.getImageableWidth() + 1);// ��o�i�C�L�ϰ쪺�e�װ��k�A�Ω�ø�s��u
                int height = (int) (pf.getImageableHeight() + 1);// ��o�i�C�L�ϰ쪺���װ��U�A�Ω�ø�s��u
                int mw = (int) pf.getWidth();// ��o�C�L�����e��
                int mh = (int) pf.getHeight();// ��o�C�L��������
                g2.drawRect(0, 0, mw, mh);// ø�s��u�~��
                g2.setColor(new Color(255, 253, 234));// �]�w�e����
                g2.fillRect(1, 1, mw - 1, mh - 1);// ø�s��R�ϰ�
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));// ���w��u�Ҧ�
                g2.setColor(Color.BLACK);// �]�w�e����
                g2.drawRect(x, y, width, height);// ø�s��u����
                g2.setColor(Color.WHITE);// �]�w�e����
                g2.fillRect(x + 1, y + 1, width - 1, height - 1);// ø�s��R�ϰ�
                PrintLineReportFrame.this.print(g, pf, 0);// �I�sprint()��k
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * ø�s�C�L���e
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     */
    public void printPicture(Graphics graphics, PageFormat pageFormat,
            int pageIndex) {
        int x = (int) pageFormat.getImageableX();// ��o�i�C�L�ϰ�y�Ъ�X��m
        int y = (int) pageFormat.getImageableY();// ��o�i�C�L�ϰ�y�Ъ�Y��m
        int w = (int) pageFormat.getImageableWidth();// ��o�i�C�L�ϰ쪺�e��
        Graphics2D g2 = (Graphics2D) graphics;// �ରGraphics2D�ﹳ
        ResultSet rs = QueryResultSet.gainReport();// ��o�d�ߵ��G��
        if (rs != null && isPreview) {
            g2.setColor(Color.BLUE);// �]�w���Ŧ�
            g2.setFont(new Font("�ؤ�淢", Font.BOLD, 40));// �]�w�r��
            g2.drawString("���u�򥻸�T����", x + 60, y + 50);// ø�s������D
            g2.setColor(Color.BLACK);// �]�w���¦�
            g2.setFont(new Font("�ө���", Font.PLAIN, 12));// �]�w�r��
            g2.setStroke(new BasicStroke(1f));// ���w��u�Ҧ�
            try {
                y = y + 80;// �վ�C�L��m��y��
                int y1 = y;// �x�s�վ��C�L��m��y��
                /************** ø�s�������D *******************/
                g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// ø�s�������u
                g2.drawString("�s��", x + 20, y);// ø�s�����e
                g2.drawString("�m�W", x + 60, y);// ø�s�����e
                g2.drawString("�ʧO", x + 110, y);// ø�s�����e
                g2.drawString("�~��", x + 150, y);// ø�s�����e
                g2.drawString("�pô�a�}", x + 190, y);// ø�s�����e
                g2.drawString("�l�F�g�{��", x + 290, y);// ø�s�����e
                g2.drawString("�q�ܸ��X", x + 360, y);// ø�s�����e
                g2.drawLine(x + 10, y1 - 20, x + 10, y + 10);// ø�s�������u
                g2.drawLine(x + 50, y1 - 20, x + 50, y + 10);// ø�s�������u
                g2.drawLine(x + 100, y1 - 20, x + 100, y + 10);// ø�s�������u
                g2.drawLine(x + 140, y1 - 20, x + 140, y + 10);// ø�s�������u
                g2.drawLine(x + 180, y1 - 20, x + 180, y + 10);// ø�s�������u
                g2.drawLine(x + 280, y1 - 20, x + 280, y + 10);// ø�s�������u
                g2.drawLine(x + 350, y1 - 20, x + 350, y + 10);// ø�s�������u
                g2.drawLine(x + w - 10, y1 - 20, x + w - 10, y + 10);// ø�s�������u
                /************************************************/
                while (rs.next()) {
                    y = y + 30;// �վ�C�L��m��y��
                    g2.drawLine(x + 10, y - 20, x + w - 10, y - 20);// ø�s�������u
                    g2.drawString(String.valueOf(rs.getInt(1)), x + 30, y);// ø�s�����e
                    g2.drawString(rs.getString(2), x + 60, y);// ø�s�����e
                    g2.drawString(rs.getString(3), x + 110, y);// ø�s�����e
                    g2.drawString(String.valueOf(rs.getInt(4)), x + 150, y);// ø�s�����e
                    g2.drawString(rs.getString(5), x + 190, y);// ø�s�����e
                    g2.drawString(rs.getString(6), x + 290, y);// ø�s�����e
                    g2.drawString(rs.getString(7), x + 360, y);// ø�s�����e
                }
                g2.drawLine(x + 10, y1 + 10, x + 10, y + 10);// ø�s�������u
                g2.drawLine(x + 50, y1 + 10, x + 50, y + 10);// ø�s�������u
                g2.drawLine(x + 100, y1 + 10, x + 100, y + 10);// ø�s�������u
                g2.drawLine(x + 140, y1 + 10, x + 140, y + 10);// ø�s�������u
                g2.drawLine(x + 180, y1 + 10, x + 180, y + 10);// ø�s�������u
                g2.drawLine(x + 280, y1 + 10, x + 280, y + 10);// ø�s�������u
                g2.drawLine(x + 350, y1 + 10, x + 350, y + 10);// ø�s�������u
                g2.drawLine(x + w - 10, y1 + 10, x + w - 10, y + 10);// ø�s�������u
                g2.drawLine(x + 10, y + 30 - 20, x + w - 10, y + 30 - 20);// ø�s�������u
                /********************* ø�s�Ϫ�Ϥ� *****************************/
                URL imgUrl = PrintLineReportFrame.class.getResource("/chartImg/chart.jpg");// ��o�Ϥ��귽�����|
                Image img = Toolkit.getDefaultToolkit().getImage(imgUrl); // ��o�Ϫ�Ϥ����ϧιﹳ
                g2.drawImage(img, x + 10, y + 20, 420, 300, this);// �b�C�L��ø�s�Ϫ�Ϥ�
                
                rs.close();// �������G��
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        isPreview = true; // �]�w���i�H�C�L
        previewFlag = true;
    }
}
