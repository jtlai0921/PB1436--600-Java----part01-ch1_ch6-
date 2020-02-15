package com.zzk;

import java.sql.*;
import javax.swing.JOptionPane;

public class DAO {
    private static DAO dao = new DAO(); // �ŧiDAO���O���R�A���
    
    /**
     * �غc��k�A���J��ƨ�Ʈw�X��
     */
    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // ���J��ƨ�Ʈw�X��
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "��ƨ�Ʈw�X�ʸ��J���ѡA�бNMySQL�X�ʳ]�w��غc���|���C\n" + e.getMessage());
        }
    }
    
    /**
     * ��o��ƨ�Ʈw�s������k
     * 
     * @return Connection
     */
    public static Connection getConn() {
        try {
            Connection conn = null; // �w�q��ƨ�Ʈw�s��
            String url = "jdbc:mysql://localhost:3306/db_database?useUnicode=true&characterEncoding=utf-8"; // ��ƨ�Ʈwdb_database��URL
            String username = "root"; // ��ƨ�Ʈw���ϥΪ̦W��
            String password = "111"; // ��ƨ�Ʈw�K�X
            conn = DriverManager.getConnection(url, username, password); // �إ߳s��
            return conn; // �Ǧ^�s��
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "��ƨ�Ʈw�s�����ѡC" + e.getMessage());
            return null;
        }
    }
}