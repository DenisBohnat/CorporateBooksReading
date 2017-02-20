package by.htp.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectorDao {

	protected Connection connection;

	public ConnectorDao() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksreading", "root", "pass");
		} catch (SQLException e) {
			System.err.println("Connection error:" + e);
		}
	}

	public Statement getStatement() throws SQLException {
		if (connection != null) {
			Statement statement = connection.createStatement();
			if (statement != null) {
				return statement;
			}
		}
		throw new SQLException("connection or statement is null");
	}

	public void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println("Close statement error:" + e);
			}
		}
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Close connection error:" + e);
			}
		}
	}

	public PreparedStatement getPreparedStatement(String sqlstr) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sqlstr);
		} catch (SQLException e) {
			System.err.println("Prepared statement error:" + e);
		}
		return ps;
	}

}
