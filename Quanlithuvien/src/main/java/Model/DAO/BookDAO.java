package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.BO.CategoryBO;
import Model.BO.ReaderBO;
import Model.Bean.Book;
import Model.Bean.Category;

public class BookDAO {
	
	Connection conn = null;
	PreparedStatement pstm = null;
	Statement st = null;
	CategoryBO categoryBO = new CategoryBO();
	ReaderBO readerBO= new ReaderBO();
	
	
	public Book find(String id) throws SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		
		String sql = "Select * from Book where id = ?";
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, (id));
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			String name = rs.getString("Name");
			String category_id = rs.getString("category_id");
			Date date = rs.getDate("create_day");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = dateFormat.format(date);
			Category category = new Category();
			
			category = categoryBO.findCategory(category_id);
			
			String amount = rs.getString("amount");
			String image = rs.getString("image");
			Book book = new Book();
			book.setId(id);
			book.setName(name);
			book.setCategory(category);
			book.setImage(image);
			book.setDay(strDate);
			return book;
		}
		return null;
		
	}
	
	public int insertBook(Book book) throws SQLException {
		
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		
		
		int result = 0;
		String insert = "INSERT INTO BOOK(name, category_id,amount, image ) VALUES (?,?,?,?)";
		pstm = conn.prepareStatement(insert);
		pstm.setString(1, book.getName());
		pstm.setString(2, Integer.toString(book.getCategory().getId()));
		pstm.setString(3, book.getAmount());
		pstm.setString(4, book.getImage());
		
		result =  pstm.executeUpdate();
		System.out.println("Ket qua: "+ result);
		return result;
	}
	
	public ArrayList<Book> getAllBook() throws SQLException{
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		
		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "SELECT * from  book ORDER BY create_day DESC";
		
		pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String category_id = rs.getString("category_id");
			Date date = rs.getDate("create_day");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = dateFormat.format(date);
			
			Category category = new Category();
			
			category = categoryBO.findCategory(category_id);
			
			String amount = rs.getString("amount");
			String image = rs.getString("image");
			Book book = new Book();
			book.setId(id);
			book.setName(name);
			book.setCategory(category);
			book.setAmount(amount);
			book.setImage(image);
			book.setDay(strDate);
			list.add(book);
		}
		return list;
		
	}
	
	public ArrayList<Book> getSearchBook(String name_search) throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		System.out.println("Day"+name_search);
		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "Select * from Book where name like '%"+name_search+"%';";
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String category_id = rs.getString("category_id");
			Date date = rs.getDate("create_day");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = dateFormat.format(date);
			Category category = new Category();
				category = categoryBO.findCategory(category_id);
			
			String amount = rs.getString("amount");
			String image = rs.getString("image");
			Book book = new Book();
			book.setId(id);
			book.setName(name);
			book.setCategory(category);
			book.setAmount(amount);
			book.setImage(image);
			book.setDay(strDate);
			list.add(book);
			System.out.println(book.getName());
		}
		System.out.println(list);
		return list;
	}
	
	public int updateBook(Book book) throws SQLException, ClassNotFoundException {
		
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		String sql = "Update Book set name =?,category_id =?,amount =?,image =?  where id=? ";
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		
		pstm.setString(1, book.getName());
		pstm.setString(2, Integer.toString(book.getCategory().getId()));
		pstm.setString(3, book.getAmount());
		pstm.setString(4, book.getImage());
		pstm.setString(5, book.getId());
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteAllBook() throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		readerBO.deleteAllReader();
		String sql = "Delete From Book";
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteBook(String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();

		readerBO.deleteBookReader(id);
		String sql = "Delete From Book where id= ?";
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteBookCategory(String category_id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		readerBO.deleteBookReaderCategory(category_id);
		String sql = "Delete From Book where category_id= ?";
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, category_id);
		result = pstm.executeUpdate();
		return result;
	}	
	
}
