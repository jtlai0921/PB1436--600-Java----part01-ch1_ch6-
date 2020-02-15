package com.zzk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author 張振坤
 */
public class QueryResultSet {
    /**
     * 定義靜態敘述塊，用於載入資料函數庫驅動
     */
    static {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 載入資料函數庫驅動
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 獲得查詢結果集
     */
    public static ResultSet gainRecord() {
        Connection conn = null; // 宣告連接
        Statement st = null; // 宣告Statement對像
        ResultSet rs = null; // 宣告結果集對像
        try {
            String url = "jdbc:jtds:sqlserver://localhost:1433/db_database"; // 資料函數庫db_database的URL
            String username = "sa"; // 資料函數庫的使用者名稱
            String password = "1"; // 資料函數庫密碼
            conn = DriverManager.getConnection(url, username, password); // 建立連接
            st = conn.createStatement(); // 建立Statement對像
            String sql = "select * from tb_employee"; // 定義SQL查詢敘述
            rs = st.executeQuery(sql); // 執行SQL敘述獲得結果集
            return rs;// 傳回結果集
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "資料函數庫例外：\n" + e.getMessage());
            return null;
        } 
    }
}
