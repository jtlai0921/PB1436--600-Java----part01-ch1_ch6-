package com.zzk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 * @author �i���[
 *
 */
public class QueryResultSet {
	/**
	 * �w�q�R�A�ԭz���A�Ω���J��ƨ�Ʈw�X��
	 */
	static {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");			// ���J��ƨ�Ʈw�X��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��o�d�ߵ��G��
	 */
	public static ResultSet gainRecord(){
	    Connection conn=null;                                  // �ŧi�s��
	    Statement st=null;                                      // �ŧiStatement�ﹳ
	    ResultSet rs=null;                                      // �ŧi���G���ﹳ
		try {
			String url="jdbc:jtds:sqlserver://localhost:1433/db_database";		// ��ƨ�Ʈwdb_database��URL
			String username="sa";									// ��ƨ�Ʈw���ϥΪ̦W��
			String password="1";									// ��ƨ�Ʈw�K�X
			conn = DriverManager.getConnection(url, username, password); 	// �إ߳s��
			st=conn.createStatement();								// �إ�Statement�ﹳ
			String sql="select avg(age) as avgAge,sex,address from tb_employee group by address,sex";					// �w�qSQL�d�߱ԭz
			rs=st.executeQuery(sql);								// ����SQL�ԭz��o���G��
			return rs;// �Ǧ^���G��
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��ƨ�Ʈw�ҥ~�G\n" + e.getMessage());
			return null;
		}
	}
}
