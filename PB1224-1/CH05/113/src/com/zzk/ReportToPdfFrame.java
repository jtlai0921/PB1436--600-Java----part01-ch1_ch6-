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
    
    public void toPdf() {
        try {
            Document document = new Document(PageSize.A4, 71.4f, 71.4f, 71.4f, 71.4f);// 定義文件以A4紙張的格式列印
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("c:\\report.pdf"));// 設定輸出的位置及檔案名
            document.open();// 開啟文件
            BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 定義基本字體
            Font titleFont = new Font(bfChinese, 12, Font.BOLD);// 定義粗體字體，用於設定表格標題的字體
            Font contentFont = new Font(bfChinese, 12);// 定義普通字體
            PdfPCell cell = null;// 定義PDF單元格
            PdfPTable table = new PdfPTable(new float[] { 40.9f, 40.9f, 40.9f,
                    40.9f, 79.1f, 40.9f, 79.1f });// 建立一個7列的pdf表格
            table.setTotalWidth(460);// 設定表格的寬度為460
            table.setLockedWidth(true);// 鎖定表格的寬度
            for (int i = 0; i < title.length; i++) {
                cell = new PdfPCell(new Paragraph(title[i], titleFont));// 建立單元格對像
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 單元格內容水平居中
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 單元格內容垂直居中
                cell.setFixedHeight(20.0f);// 設定單元格的高度
                table.addCell(cell);// 為表格增加單元格
            }
            
            ResultSet rs = QueryResultSet.gainRecord();// 獲得資料函數庫表的結果集對像
            while (rs.next()) {// 檢查結果集
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// 定義存放單元格內容的變數
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// 從記錄集獲得單元格內容
                    } else {
                        cellContent = rs.getString(i + 1);// 從記錄集獲得單元格內容
                    }
                    cell = new PdfPCell(new Paragraph(cellContent, contentFont));// 建立單元格對像
                    if (i == 6) {
                        cell.setBorderWidthRight(0.01f);// 單元格右邊框的寬度
                    } else {
                        cell.setBorderWidthRight(0f);// 單元格右邊框的寬度
                    }
                    cell.setBorderWidthTop(0f);// 單元格上邊框的寬度
                    cell.setBorderWidthBottom(0.01f);// 單元格下邊框的寬度
                    cell.setFixedHeight(20.0f);// 單元格的高度
                    table.addCell(cell);// 為表格增加單元格
                }
            }
            titleFont = new Font(bfChinese, 36, Font.BOLD);// 定義粗體字體，用於設定標題的字體
            Paragraph pdfTitle = new Paragraph("員工基本資訊報表\n\n", titleFont);// 建立用於指定標題的Paragraph對像
            pdfTitle.setAlignment(Element.ALIGN_CENTER);// 居中Paragraph對像
            document.add(pdfTitle);// 為PDF文件增加標題
            document.add(table);// 為PDF文件增加表格
            document.close();// 關閉文件
            JOptionPane.showMessageDialog(null, "已經成功地將報表\n匯出到「C:/report.pdf」中。");
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
        setTitle("匯出報表到PDF文件");
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
        button.setText("匯出為PDF");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("退出系統");
        panel.add(button_1);
        //
    }
}
