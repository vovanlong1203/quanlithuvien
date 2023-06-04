package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	
	public static Connection getMysqlConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/manage_library";
			String user = "root"; 
			String password = "";
			Connection con =  DriverManager.getConnection(url, user, password);
			System.out.println("Ket Noi Thanh Cong");
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Ket Noi Khong Thanh Cong");
			e.printStackTrace();
		}
		return null;
		
	}
}
