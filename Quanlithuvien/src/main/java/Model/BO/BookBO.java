package Model.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import Model.Bean.Book;
import Model.DAO.BookDAO;

public class BookBO {
	
	BookDAO bookDAO = new BookDAO();
	
	public Book findbook(String id) throws SQLException {
		return bookDAO.find(id);
	}
	
	public int insertBook(Book book) throws SQLException {
		int result = 0;
		result = bookDAO.insertBook(book);
		return result;
	}
	
	public ArrayList<Book> getAllbook() throws SQLException{
		return bookDAO.getAllBook();
	}
	
	public int updateBook(Book book) throws ClassNotFoundException, SQLException {
		int result = 0;
		result = bookDAO.updateBook(book);
		return result;
	}
	
	public ArrayList<Book> listBook() throws ClassNotFoundException, SQLException {
		return bookDAO.getAllBook();
	}
	public ArrayList<Book> searchBook(String name_search) throws ClassNotFoundException, SQLException {
		return bookDAO.getSearchBook(name_search);
	}

	public boolean deleteBook(String id) throws ClassNotFoundException, SQLException {
		int result = bookDAO.deleteBook(id);
		if (result != 0)
			return true;
		return false;
	}
	public int deleteBookCategory(String category_id) throws ClassNotFoundException, SQLException {
		return bookDAO.deleteBookCategory(category_id);
	}
	public boolean deleteAllBook() throws ClassNotFoundException, SQLException {
		int result = bookDAO.deleteAllBook();
		if (result != 0)
			return true;
		return false;
	}
}
