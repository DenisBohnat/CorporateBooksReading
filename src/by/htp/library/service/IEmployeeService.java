package by.htp.library.service;

import by.htp.library.domain.Employee;

public interface IEmployeeService {

	void listEmployee();

	void insertEmployee(Employee employee);

	void deleteEmployee(int id);

	void deleteBook(Employee employee);

	void listEmployeeById(int id);

	void updateBook(Employee employee);

}
