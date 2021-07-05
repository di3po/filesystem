package project;
import java.sql.*;

import javax.swing.JOptionPane;

public class tables {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionProvider.getCon();
			st = con.createStatement();
			st.executeUpdate("create table users(name varchar(200), email varchar(200), password varchar(50))");
			st.executeUpdate("create table my_students(student_name varchar(50), student_group varchar(50), price double, receipt_img BLOB, status varchar(50))");
			JOptionPane.showMessageDialog(null, "Table created successfully!");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		finally {
			try {
				con.close();
				st.close();
			} catch(Exception e) {
				
			}
		}
	}
}
