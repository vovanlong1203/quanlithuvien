package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Bean.User;

public class UserDAO {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement preSt = null;
	
	public User getUser(String username, String password) throws SQLException {
		if (conn == null) {
			conn = ConnectDatabase.getMysqlConnection();
			String sql = "select * from User where username = ? and password = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				return user;
			}
		}
		return null;
	}
}
