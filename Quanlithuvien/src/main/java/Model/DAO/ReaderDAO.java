package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.BO.BookBO;
import Model.BO.CategoryBO;
import Model.Bean.Book;
import Model.Bean.Reader;

public class ReaderDAO {
	
	Connection conn = null;
	Statement st = null;
	PreparedStatement pstm = null;
	CategoryBO categoryBO = new CategoryBO();
	
	public int insertReader(String name, String identify, String book_id, Timestamp end_day) throws SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
	
		st = (Statement) conn.createStatement();
	
		int result = 0;
		String insert = "INSERT INTO Reader (name, book_id, identity_card, end_day) VALUES (?,?, ?, ?)";
		pstm = conn.prepareStatement(insert);
		pstm.setString(1, name);
		pstm.setString(2, book_id);
		pstm.setString(3, identify);
		pstm.setTimestamp(4, end_day);
		result = pstm.executeUpdate();
		System.out.println("Ketqua" + result);
		return result;
	}
	
	public ArrayList<Reader> getListReader(String status) throws ClassNotFoundException, SQLException {
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		ArrayList<Reader> list = new ArrayList<Reader>();
		String sql = "Select * from reader where status = ?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);

		pstm.setInt(1, Integer.parseInt(status));
		
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			String book_id = rs.getString("book_id");
			String identity_card = rs.getString("identity_card");
			Date start = rs.getDate("start_day");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String start_day = dateFormat.format(start);
			Date end = rs.getDate("end_day");
			String end_day = dateFormat.format(end);
			Book book = new Book();
				BookBO bookBO = new BookBO();
				book =  bookBO.findbook(book_id);

			Reader reader = new Reader();
			reader.setId(id);
			reader.setBook_id(book_id);
			reader.setBook(book);
			reader.setName(name);
			reader.setIdentify(identity_card);
			reader.setStart_day(start_day);
			reader.setEnd_day(end_day);
			reader.setStatus(status);
			
			list.add(reader);
		}
		return list;
	}
	
	public int updateStatus(String id) throws SQLException, ClassNotFoundException {
		
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		String sql = "Update Reader set status=1  where id=? ";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, id);
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteAllReader() throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		String sql = "Delete From Reader";
		PreparedStatement pstm =  conn.prepareStatement(sql);
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteReader(String id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		String sql = "Delete From Reader where id= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteReaderBook(String book_id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		String sql = "Delete From Reader where book_id= ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, book_id);
		result = pstm.executeUpdate();
		return result;
	}
	
	public int deleteReaderBookCategory(String category_id) throws ClassNotFoundException, SQLException {
		int result = 0;
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		String sql = "DELETE Reader FROM Reader LEFT JOIN Book ON Reader.book_id = Book.id WHERE book.category_id=?";
		PreparedStatement pstm =  conn.prepareStatement(sql);
		pstm.setString(1, category_id);
		result = pstm.executeUpdate();
		return result;
	}
	
	public ArrayList<Reader> getListSearch(String data_search, String status) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		data_search="%"+data_search+"%";
		System.out.println(data_search+status+"");
		if (conn == null)
			conn = ConnectDatabase.getMysqlConnection();
		ArrayList<Reader> list = new ArrayList<Reader>();
		String sql = "Select * from Reader where name like ? and status=?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, data_search);
		pstm.setInt(2, Integer.parseInt(status));
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			System.out.println("Daaa");
			String id = rs.getString("id");
			String name = rs.getString("name");
			String book_id = rs.getString("book_id");
			String identity_card = rs.getString("identity_card");
			Date start = rs.getDate("start_day");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String start_day = dateFormat.format(start);
			Date end = rs.getDate("end_day");
			String end_day = dateFormat.format(end);
			Book book = new Book();
			
				BookBO bookBO = new BookBO();
				book =  bookBO.findbook(book_id);
			
			Reader reader = new Reader();
			reader.setId(id);
			reader.setBook_id(book_id);
			reader.setBook(book);
			reader.setName(name);
			reader.setIdentify(identity_card);
			reader.setStart_day(start_day);
			reader.setEnd_day(end_day);
			reader.setStatus(status);
			list.add(reader);
		}
		return list;
	}
}
