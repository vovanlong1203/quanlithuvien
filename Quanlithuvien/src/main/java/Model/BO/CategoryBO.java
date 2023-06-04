package Model.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.Category;
import Model.DAO.CategoryDAO;

public class CategoryBO {

	CategoryDAO categoryDAO = new CategoryDAO();
	
	public Category findCategory(String id) throws SQLException {
		return categoryDAO.findCategory(id);
	}
	
	public int insertCategory(Category category) throws SQLException {
		int result = 0;
		result = categoryDAO.insertCategory(category);
		return result;
	}
	
	public ArrayList<Category> listCategory() throws SQLException{
		return categoryDAO.getAllCategory();
	}
	
	public int updateCategory(Category category) throws SQLException {
		return categoryDAO.updateCategory(category);
	}
	
	public boolean deleteCategory(String id) throws ClassNotFoundException, SQLException {
		int result = categoryDAO.deleteCategory(id);
		if (result != 0)
			return true;
		return false;
	}
	
}
