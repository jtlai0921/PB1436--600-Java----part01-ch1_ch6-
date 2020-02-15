package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ReportToPdfFrame extends JFrame {
    
    private JTable table;
    private String[] title = { "�s��", "�m�W", "�ʧO", "�~��", "�a�}", "�l���ϸ�", "�q��" };
    
    /**
     * @return JTable��檺���D�V�q
     */
    public Vector getTitleVector() {
        Vector titleVec = new Vector();
        for (int i = 0; i < title.length; i++) {
            titleVec.add(title[i]);
        }
        return titleVec;
    }
    
    /**
     * @return JTable��檺���e�V�q
     */
    public Vector getDataVectors() {
        ResultSet rs = QueryResultSet.gainRecord();
        Vector dataVector = new Vector();
        try {
            while (rs.next()) {
                Vector rowVector = new Vector();
                for (int i = 0; i < title.length; i++) {
                    if (i == 0 || i == 3) {
                        rowVector.add(rs.getInt(i + 1));
                    } else {
                        rowVector.add(rs.getString(i + 1));
                    }
                }
                dataVector.add(rowVector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataVector;
    }
    
    public void toPdf() {
        try {
            Document document = new Document(PageSize.A4, 71.4f, 71.4f, 71.4f, 71.4f);// �w�q���HA4�ȱi���榡�C�L
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("c:\\report.pdf"));// �]�w��X����m���ɮצW
            document.open();// �}�Ҥ��
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// �w�q�򥻦r��
            Font titleFont = new Font(bfChinese, 12, Font.BOLD);// �w�q����r��A�Ω�]�w�����D���r��
            Font contentFont = new Font(bfChinese, 12);// �w�q���q�r��
            PdfPCell cell = null;// �w�qPDF�椸��
            PdfPTable table = new PdfPTable(new float[] { 40.9f, 40.9f, 40.9f,
                    40.9f, 79.1f, 40.9f, 79.1f });// �إߤ@��7�C��pdf���
            table.setTotalWidth(460);// �]�w��檺�e�׬�460
            table.setLockedWidth(true);// ��w��檺�e��
            for (int i = 0; i < title.length; i++) {
                cell = new PdfPCell(new Paragraph(title[i], titleFont));// �إ߳椸��ﹳ
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// �椸�椺�e�����~��
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// �椸�椺�e�����~��
                cell.setFixedHeight(20.0f);// �]�w�椸�檺����
                table.addCell(cell);// �����W�[�椸��
            }
            
            ResultSet rs = QueryResultSet.gainRecord();// ��o��ƨ�Ʈw�����G���ﹳ
            while (rs.next()) {// �ˬd���G��
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// �w�q�s��椸�椺�e���ܼ�
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// �q�O������o�椸�椺�e
                    } else {
                        cellContent = rs.getString(i + 1);// �q�O������o�椸�椺�e
                    }
                    cell = new PdfPCell(new Paragraph(cellContent, contentFont));// �إ߳椸��ﹳ
                    if (i == 6) {
                        cell.setBorderWidthRight(0.01f);// �椸��k��ت��e��
                    } else {
                        cell.setBorderWidthRight(0f);// �椸��k��ت��e��
                    }
                    cell.setBorderWidthTop(0f);// �椸��W��ت��e��
                    cell.setBorderWidthBottom(0.01f);// �椸��U��ت��e��
                    cell.setFixedHeight(20.0f);// �椸�檺����
                    table.addCell(cell);// �����W�[�椸��
                }
            }
            titleFont = new Font(bfChinese, 36, Font.BOLD);// �w�q����r��A�Ω�]�w���D���r��
            Paragraph pdfTitle = new Paragraph("���u�򥻸�T����\n\n", titleFont);// �إߥΩ���w���D��Paragraph�ﹳ
            pdfTitle.setAlignment(Element.ALIGN_CENTER);// �~��Paragraph�ﹳ
            document.add(pdfTitle);// ��PDF���W�[���D
            document.add(table);// ��PDF���W�[���
            document.close();// �������
            JOptionPane.showMessageDialog(null, "�w�g���\�a�N����\n�ץX��uC:/report.pdf�v���C");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportToPdfFrame frame = new ReportToPdfFrame();
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
    public ReportToPdfFrame() {
        super();
        setTitle("�ץX�����PDF���");
        setBounds(100, 100, 383, 264);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        table = new JTable(getDataVectors(), getTitleVector());
        scrollPane.setViewportView(table);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                toPdf();
            }
        });
        button.setText("�ץX��PDF");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h�X�t��");
        panel.add(button_1);
        //
    }
}
