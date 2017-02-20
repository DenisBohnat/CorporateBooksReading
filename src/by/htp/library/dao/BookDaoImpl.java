package by.htp.library.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.domain.Book;

public class BookDaoImpl extends ConnectorDao implements ICRUDDao<Book> {

	private static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM book";
	private static final String SQL_INSERT_BOOK = "INSERT INTO book(Title, Publish_year, Author ) VALUES(?,?,?)";
	private static final String SQL_DELETE_BOOK_ID = "DELETE FROM book WHERE id=?";
	private static final String SQL_DELETE_BOOK_ENTITY = "DELETE FROM book WHERE title=? AND Publish_year=? AND Author=?";
	private static final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM book WHERE id=?";
	private static final String SQL_UPDATE_BOOK_BY_ID = "UPDATE book SET Title=?, Publish_year=?, Author=? WHERE id=?";
	private static final String SQL_RENAME_BOOK = "UPDATE book SET title=? WHERE title=?";
	private static final String SQL_RENAME_MASK_BOOK = "UPDATE book SET title=? WHERE title like ?";

	public BookDaoImpl() {
		super();
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		Statement st = null;
		try {
			st = this.getStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_BOOKS);
			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("Title"));
				book.setYear(resultSet.getInt("Publish_year"));
				book.setAuthor(resultSet.getString("Author"));
				books.add(book);
			}
		} catch (SQLException e) {
			System.err.println("Database reading error:" + e);
		} finally {
			this.closeConnection();
		}
		return books;
	}

	@Override
	public boolean create(Book book) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_INSERT_BOOK);
		try {
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getYear());
			ps.setString(3, book.getAuthor());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("Database insert error:" + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_DELETE_BOOK_ID);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("Database delete error::" + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}

	@Override
	public Book findEntityById(int id) {
		Book book = new Book();
		PreparedStatement ps = this.getPreparedStatement(SQL_SELECT_BOOK_BY_ID);
		try {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			book.setId(resultSet.getInt("id"));
			book.setTitle(resultSet.getString("Title"));
			book.setYear(resultSet.getInt("Publish_year"));
			book.setAuthor(resultSet.getString("Author"));
		} catch (SQLException e) {
			System.err.println("Database reading error::" + e);
		} finally {
			this.closeConnection();
		}
		return book;
	}

	@Override
	public boolean delete(Book book) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_DELETE_BOOK_ENTITY);
		try {
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getYear());
			ps.setString(3, book.getAuthor());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("Database delete error:" + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}

	@Override
	public boolean update(Book book) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_UPDATE_BOOK_BY_ID);
		try {
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getYear());
			ps.setString(3, book.getAuthor());
			ps.setInt(4, book.getId());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("Database update error:" + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}
	/*Переименование книги*/
	public boolean renameBook(String oldTitle, String newTitle) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_RENAME_BOOK);
		try {
			ps.setString(1, newTitle);
			ps.setString(2, oldTitle);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("Rename book error:" + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}
	/*Переименование книги с маской*/
	public boolean renameMaskBook(String maskTitle, String newTitle) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_RENAME_MASK_BOOK);
		maskTitle += "%";
		try {
			ps.setString(1, newTitle);
			ps.setString(2, maskTitle);
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("Rename book error:" + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}

}
