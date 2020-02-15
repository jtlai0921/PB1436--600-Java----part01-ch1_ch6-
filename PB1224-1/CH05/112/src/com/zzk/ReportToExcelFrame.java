package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReportToExcelFrame extends JFrame {
    
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
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportToExcelFrame frame = new ReportToExcelFrame();
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
    public ReportToExcelFrame() {
        super();
        setTitle("�ץX�����Excel���");
        setBounds(100, 100, 420, 281);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    writeExcel("c:/report.xls");// �I�s��k�A�ץX�����Excel
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("�ץX��Excel");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�h�X�t��");
        panel.add(button_1);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(getDataVectors(), getTitleVector());
        scrollPane.setViewportView(table);
        // 
    }
    
    @SuppressWarnings("deprecation")
    public void writeExcel(String filename) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();// �إߤu�@ï�ﹳ
        HSSFSheet sheet = workbook.createSheet("ReportToExcel");// �إߦW�٬��uReportToExcel�v���u�@��
        HSSFRow row = sheet.createRow(0);// �b�u�@���إߦ�A�����ެ�0
        HSSFCell cell = null;// �ŧi�椸��
        // �ץX������bExcel�������D
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell((short) i);// �إ߳椸��
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);// �]�w�椸�櫬�A
            cell.setCellValue(title[i]);// �]�w�椸�檺���e
        }
        // �ץX������bExcel�������e
        ResultSet rs = QueryResultSet.gainRecord();// ��o��ƨ�Ʈw�����G���ﹳ
        int rowIndex = 1;// �椸�椺�e������ޭ�
        try {
            while (rs.next()) {// �ˬd���G��
                row = sheet.createRow(rowIndex);
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// �w�q�s��椸�椺�e���ܼ�
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// �q�O������o�椸�椺�e
                    } else {
                        cellContent = rs.getString(i + 1);// �q�O������o�椸�椺�e
                    }
                    cell = row.createCell((short) i);// �إ߳椸��ﹳ
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);// �]�w�椸�櫬�A
                    cell.setCellValue(cellContent);// ���w�椸�檺��
                }
                rowIndex++;// �վ�椸�椺�e������ޭ�
            }
            FileOutputStream fout = new FileOutputStream(filename);// �إ߶ץXExcel����X�y
            workbook.write(fout);// �N�u�@ï�g�J��X�y
            fout.flush();// ��s��X�y�ñj��g�X�Ҧ��w�Ī���X�r�`
            fout.close();// ������X�y�ﹳ
            JOptionPane.showMessageDialog(null, "�w�g���\�a�N����\n�ץX��uC:/report.xls�v���C");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
