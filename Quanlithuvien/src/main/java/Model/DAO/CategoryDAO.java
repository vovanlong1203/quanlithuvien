package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.BO.BookBO;
import Model.Bean.Category;

public class CategoryDAO {

	Connection conn = null;
	Statement st = null;
	PreparedStatement prest = null;
	
	public Category findCategory(String id) throws SQLException {
	
			if(conn == null)
				conn = ConnectDatabase.getMysqlConnection();
			String sql = "Select * from Category where id = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, id);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				int _id = rs.getInt("Id");
				String  name = rs.getString("Name");
				Category category = new Category(_id, name);
				return category;
			}
			
			return null;
	}
	
	public ArrayList<Category> getAllCategory() throws SQLException{
		if (conn == null) {
			conn = ConnectDatabase.getMysqlConnection();
 		}
		
		ArrayList<Category> list = new ArrayList<Category>();
		String sql = "select * from Category";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			Category category = new Category(id, name);
			category.setId(id);
			category.setName(name);
			list.add(category);
			
			
		}
		return list;
	}
	
	public int insertCategory(Category category) throws SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		int result = 0;
		String insert = "INSERT INTO CATEGORY (name) VALUES (?)";
		prest = conn.prepareStatement(insert);
		prest.setString(1, category.getName());
		result = prest.executeUpdate();
		
		return result;
		
	}
	
	public int updateCategory(Category category) throws SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		
		String sql = "Update Category set Name = ? where id = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, category.getName());
		pstm.setFloat(2, category.getId());
		
		result = pstm.executeUpdate();
		return result;
		
	}
	
	public int deleteCategory (String id ) throws SQLException, ClassNotFoundException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		
		BookBO bookBO = new BookBO();
		bookBO.deleteBookCategory(id);
		String sql = "Delete From Category where id= ?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, id);
		result = pstm.executeUpdate();
		return result;
	}
	
}
