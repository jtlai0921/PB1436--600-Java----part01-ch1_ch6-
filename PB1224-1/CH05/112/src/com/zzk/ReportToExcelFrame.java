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
    private String[] title = { "編號", "姓名", "性別", "年齡", "地址", "郵遞區號", "電話" };
    
    /**
     * @return JTable表格的標題向量
     */
    public Vector getTitleVector() {
        Vector titleVec = new Vector();
        for (int i = 0; i < title.length; i++) {
            titleVec.add(title[i]);
        }
        return titleVec;
    }
    
    /**
     * @return JTable表格的內容向量
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
        setTitle("匯出報表到Excel表格");
        setBounds(100, 100, 420, 281);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    writeExcel("c:/report.xls");// 呼叫方法，匯出報表到Excel
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("匯出到Excel");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退出系統");
        panel.add(button_1);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(getDataVectors(), getTitleVector());
        scrollPane.setViewportView(table);
        // 
    }
    
    @SuppressWarnings("deprecation")
    public void writeExcel(String filename) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();// 建立工作簿對像
        HSSFSheet sheet = workbook.createSheet("ReportToExcel");// 建立名稱為「ReportToExcel」的工作表
        HSSFRow row = sheet.createRow(0);// 在工作表中建立行，其行索引為0
        HSSFCell cell = null;// 宣告單元格
        // 匯出的報表在Excel中的標題
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell((short) i);// 建立單元格
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 設定單元格型態
            cell.setCellValue(title[i]);// 設定單元格的內容
        }
        // 匯出的報表在Excel中的內容
        ResultSet rs = QueryResultSet.gainRecord();// 獲得資料函數庫表的結果集對像
        int rowIndex = 1;// 單元格內容的行索引值
        try {
            while (rs.next()) {// 檢查結果集
                row = sheet.createRow(rowIndex);
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// 定義存放單元格內容的變數
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// 從記錄集獲得單元格內容
                    } else {
                        cellContent = rs.getString(i + 1);// 從記錄集獲得單元格內容
                    }
                    cell = row.createCell((short) i);// 建立單元格對像
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);// 設定單元格型態
                    cell.setCellValue(cellContent);// 指定單元格的值
                }
                rowIndex++;// 調整單元格內容的行索引值
            }
            FileOutputStream fout = new FileOutputStream(filename);// 建立匯出Excel的輸出流
            workbook.write(fout);// 將工作簿寫入輸出流
            fout.flush();// 更新輸出流並強制寫出所有緩衝的輸出字節
            fout.close();// 關閉輸出流對像
            JOptionPane.showMessageDialog(null, "已經成功地將報表\n匯出到「C:/report.xls」中。");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
