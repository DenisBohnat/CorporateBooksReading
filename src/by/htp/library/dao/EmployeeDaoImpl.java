package by.htp.library.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.htp.library.domain.Employee;

public class EmployeeDaoImpl extends ConnectorDao implements ICRUDDao<Employee> {

	private static final String SQL_SELECT_ALL_EMPLOYEE = "SELECT * FROM employee";
	private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employee (`Name`, `Date_of_birth`, `E-mail`) VALUES(?,?,?)";
	private static final String SQL_DELETE_EMPLOYEE_ID = "DELETE FROM employee WHERE id=?";
	private static final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id=?";
	private static final String SQL_SELECT_MORE_THAN_ONE_BOOK = "select employee.Name, count(employee_book.Employee_id) as count from employee join employee_book on employee.id=employee_book.Employee_id group by employee.name having count(employee_book.Employee_id)>1 order by count(employee_book.Employee_id)";
	private static final String SQL_SELECT_LESS_THAN_TWO_BOOK = "select employee.Name, employee.Date_of_birth as date, count(employee_book.Employee_id) as count from employee join employee_book on employee.id=employee_book.Employee_id group by employee.name having count(employee_book.Employee_id)<=2 order by employee.Date_of_birth , count(employee_book.Employee_id)";
	private static final String SQL_DELETE_EMPLOYEE_ENTITY = "DELETE FROM employee WHERE name=? AND Date_of_birth=? AND `E-mail`=?";
	private static final String SQL_UPDATE_EMPLOYEE_BY_ID = "UPDATE employee SET Name=?, Date_of_birth=?, `E-mail`=? WHERE id=?";

	public EmployeeDaoImpl() {
		super();
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = new ArrayList<>();
		Statement st = null;
		try {
			st = this.getStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_EMPLOYEE);
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("Name"));
				employee.setDateOfBirth(resultSet.getDate("Date_of_birth"));
				employee.seteMail(resultSet.getString("E-mail"));
				employees.add(employee);
				System.out.println(employee.toString());
			}
		} catch (SQLException e) {
			System.err.println("Database reading error:" + e);
		} finally {
			this.closeConnection();
		}
		return employees;
	}

	@Override
	public boolean create(Employee employee) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_INSERT_EMPLOYEE);
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = dateFormat.format(employee.getDateOfBirth()).toString();
			ps.setString(1, employee.getName());
			ps.setDate(2, Date.valueOf(sDate));
			ps.setString(3, employee.geteMail());
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
		PreparedStatement ps = this.getPreparedStatement(SQL_DELETE_EMPLOYEE_ID);
		try {
			ps.setInt(1, id);
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
	public Employee findEntityById(int id) {
		Employee employee = new Employee();
		PreparedStatement ps = this.getPreparedStatement(SQL_SELECT_EMPLOYEE_BY_ID);
		try {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			employee.setId(resultSet.getInt("id"));
			employee.setName(resultSet.getString("Name"));
			employee.setDateOfBirth(resultSet.getDate("Date_of_birth"));
			employee.seteMail(resultSet.getString("E-mail"));
		} catch (SQLException e) {
			System.err.println("Database reading error:" + e);
		} finally {
			this.closeConnection();
		}
		return employee;
	}

	@Override
	public boolean delete(Employee employee) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_DELETE_EMPLOYEE_ENTITY);
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = dateFormat.format(employee.getDateOfBirth()).toString();
			ps.setString(1, employee.getName());
			ps.setDate(2, Date.valueOf(sDate));
			ps.setString(3, employee.geteMail());
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
	public boolean update(Employee employee) {
		boolean flag = false;
		PreparedStatement ps = this.getPreparedStatement(SQL_UPDATE_EMPLOYEE_BY_ID);
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sDate = dateFormat.format(employee.getDateOfBirth()).toString();
			ps.setString(1, employee.getName());
			ps.setDate(2, Date.valueOf(sDate));
			ps.setString(3, employee.geteMail());
			ps.setInt(4, employee.getId());
			ps.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			System.err.println("Database update error:" + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}
	/*Поиск читателей у которых есть более одной книги*/
	public List<String> findMoreThanOneBook() {
		List<String> resultList = new ArrayList<>();
		Statement st = null;
		try {
			st = this.getStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_MORE_THAN_ONE_BOOK);
			while (resultSet.next()) {
				String tempString = resultSet.getString("Name") + " : " + resultSet.getString("count");
				resultList.add(tempString);
			}
		} catch (SQLException e) {
			System.err.println("query execution error: " + e);
		} finally {
			this.closeConnection();
		}
		return resultList;
	}
	/*Поиск читателей у которых есть менее либо равно двум книгам*/
	public List<String> findLessThanTwoBook() {
		List<String> resultList = new ArrayList<>();
		Statement st = null;
		try {
			st = this.getStatement();
			ResultSet resultSet = st.executeQuery(SQL_SELECT_LESS_THAN_TWO_BOOK);
			while (resultSet.next()) {
				String tempString = resultSet.getString("Name") + resultSet.getDate("date").toString() + " : "
						+ resultSet.getString("count");
				resultList.add(tempString);
			}
		} catch (SQLException e) {
			System.err.println("query execution error: " + e);
		} finally {
			this.closeConnection();
		}
		return resultList;
	}
	/*Заполнение таблицы Employee_Book случайными числами из таблиц Book и Employee*/
	public boolean fillRandomEmployeeBookTable() {
		boolean flag = false;
		int numberRandom = 0;
		numberRandom = (int) (100 + (Math.random() * (50 - 10)));
		Statement st = null;
		try {
			st = this.getStatement();
			ResultSet result = st.executeQuery("SELECT employee.id FROM employee ORDER BY RAND() LIMIT 1");
			result.next();
			int ignoreId = result.getInt("id");
			PreparedStatement ps;
			for (int i = 0; i < numberRandom; i++) {
				ps = this.getPreparedStatement(
						"insert into employee_book (employee_book.Employee_id,employee_book.Book_id) values ((SELECT employee.id FROM employee where employee.id<>? ORDER BY RAND() LIMIT 1), (SELECT book.id FROM book ORDER BY RAND() LIMIT 1))");
				ps.setInt(1, ignoreId);
				ps.executeUpdate();
			}
			flag = true;
		} catch (SQLException e) {
			System.err.println("query execution error: " + e);
		} finally {
			this.closeConnection();
		}
		return flag;
	}

}
