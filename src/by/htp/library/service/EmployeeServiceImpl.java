package by.htp.library.service;

import java.util.List;

import by.htp.library.dao.EmployeeDaoImpl;
import by.htp.library.domain.Employee;

public class EmployeeServiceImpl implements IEmployeeService {

	@Override
	public void listEmployee() {
		EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
		List<Employee> employees = employeedao.findAll();
		System.out.println(employees.size());
		for (int i = 0; i < employees.size(); i++) {
			System.out.println(employees.get(i).toString());
		}
	}

	@Override
	public void insertEmployee(Employee employee) {
		boolean flag = false;
		EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
		flag = employeedao.create(employee);
		if (flag) {
			System.out.println("Insert employee completed successfully");
		} else {
			System.out.println("Insert employee not completed");
		}
	}

	@Override
	public void deleteEmployee(int id) {
		boolean flag = false;
		EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
		flag = employeedao.delete(id);
		if (flag) {
			System.out.println("Delete employee completed successfully");
		} else {
			System.out.println("Delete employee not completed");
		}
	}

	@Override
	public void listEmployeeById(int id) {
		EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
		Employee employee = employeedao.findEntityById(id);
		System.out.println(employee.toString());

	}

	public void viewReportMoreThanOneBook() {
		EmployeeDaoImpl operationdao = new EmployeeDaoImpl();
		List<String> viewReport = operationdao.findMoreThanOneBook();
		for (int i = 0; i < viewReport.size(); i++) {
			System.out.println(viewReport.get(i).toString());
		}
	}

	public void viewReportLessThanTwoBook() {
		EmployeeDaoImpl operationdao = new EmployeeDaoImpl();
		List<String> viewReport = operationdao.findLessThanTwoBook();
		for (int i = 0; i < viewReport.size(); i++) {
			System.out.println(viewReport.get(i).toString());
		}
	}

	public void randomEmployeeBookGenerator() {
		boolean flag = false;
		EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
		flag = employeedao.fillRandomEmployeeBookTable();
		if (flag) {
			System.out.println("Generated successfully");
		} else {
			System.out.println("Not generated");
		}
	}

	@Override
	public void deleteBook(Employee employee) {
		boolean flag = false;
		EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
		flag = employeedao.delete(employee);
		if (flag) {
			System.out.println("Delete employee completed successfully");
		} else {
			System.out.println("Delete employee not completed");
		}
	}

	@Override
	public void updateBook(Employee employee) {
		boolean flag = false;
		EmployeeDaoImpl employeedao = new EmployeeDaoImpl();
		flag = employeedao.update(employee);
		if (flag) {
			System.out.println("Update employee completed successfully");
		} else {
			System.out.println("Update employee not completed");
		}
	}

}
