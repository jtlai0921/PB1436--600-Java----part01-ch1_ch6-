package com.zzk;
import java.sql.*;
import javax.swing.JOptionPane;

public class DAO {
    private static DAO dao = new DAO(); // 宣告DAO類別的靜態實例
    
    /**
     * 建構方法，載入資料函數庫驅動
     */
    public DAO() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // 載入資料函數庫驅動
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "資料函數庫驅動載入失敗，請將JTDS驅動設定到建構路徑中。\n"
                    + e.getMessage());
        }
    }
    
    /**
     * 獲得資料函數庫連接的方法
     * 
     * @return Connection
     */
    public static Connection getConn() {
        try {
            Connection conn = null; // 定義資料函數庫連接
            String url = "jdbc:jtds:sqlserver://localhost:1433/db_database"; // 資料函數庫db_database的URL
            String username = "sa"; // 資料函數庫的使用者名稱
            String password = "1"; // 資料函數庫密碼
            conn = DriverManager.getConnection(url, username, password); // 建立連接
            return conn; // 傳回連接
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "資料函數庫連接失敗。\n請檢查是否安裝了SP4更新，\n以及資料函數庫使用者名稱和密碼是否正確。"
                            + e.getMessage());
            return null;
        }
    }
}