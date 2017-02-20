package by.htp.library.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import by.htp.library.domain.Book;
import by.htp.library.domain.Employee;
import by.htp.library.service.BookServiceImpl;
import by.htp.library.service.EmployeeServiceImpl;
import by.htp.library.service.IBookService;
import by.htp.library.service.IEmployeeService;

public class ViewMenu {

	public ViewMenu() {
		this.MainMenuView();
		this.MainMenu();
	}

	/* Вывод на консоль главного меню */
	public void MainMenuView() {
		System.out.println("1 - Book menu");
		System.out.println("2 - Employee menu");
		System.out.println("3 - Operations");
		System.out.println("4 - Exit");
	}

	/* Вывод на консоль меню с книгами */
	public void BookMenuView() {
		System.out.println("1 - List all books");
		System.out.println("2 - Insert a book in the db");
		System.out.println("3 - Delete a book by id from the db");
		System.out.println("4 - Delete a book by entity from the db");
		System.out.println("5 - Find a book by id");
		System.out.println("6 - Update a book by id");
		System.out.println("7 - Exit");
	}

	/* Вывод на консоль меню с работниками */
	public void EmployeeMenuView() {
		System.out.println("1 - List all employees");
		System.out.println("2 - Insert an employee in the db");
		System.out.println("3 - Delete an employee by id from the db");
		System.out.println("4 - Delete an employee by entity from the db");
		System.out.println("5 - Find an employee by id");
		System.out.println("6 - Update an employee by id");
		System.out.println("7 - Exit");
	}

	/* Вывод на консоль меню с запросами */
	public void OperationMenuView() {
		System.out.println("1 - Employees who have read more than 1 book");
		System.out.println("2 - Employees who have read less than 2 book");
		System.out.println("3 - Rename book title");
		System.out.println("4 - Fill the table randomly generated values");
		System.out.println("5 - Rename book title with mask");
		System.out.println("6 - Exit");
	}

	/* Вывод результатов действий методов по запросу пользователя */
	public void MainMenu() {
		System.out.println("Enter a number ");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		switch (i) {
		case 1:
			this.BookMenuView();
			this.BookMenu();
			break;
		case 2:
			this.EmployeeMenuView();
			this.EmployeeMenu();
			break;
		case 3:
			this.OperationMenuView();
			this.OperationMenu();
			break;
		case 4:
			break;
		default:
			break;
		}
	}

	/* Выполнение действий с Book entity */
	public void BookMenu() {
		System.out.println("Enter a number");
		IBookService service = new BookServiceImpl();
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		String title;
		int year = 0;
		String author;
		int id = 0;
		int i = scInt.nextInt();
		switch (i) {
		case 1:
			service.listBooks();
			this.MainMenuView();
			this.MainMenu();
			break;
		case 2:
			System.out.println("Enter a title");
			title = scString.nextLine();
			System.out.println("Enter a year");
			year = scInt.nextInt();
			System.out.println("Enter an author");
			author = scString.nextLine();
			Book bookInsert = new Book();
			bookInsert.setTitle(title);
			bookInsert.setYear(year);
			bookInsert.setAuthor(author);
			service.insertBook(bookInsert);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 3:
			System.out.println("Enter an id");
			id = scInt.nextInt();
			service.deleteBook(id);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 4:
			System.out.println("Enter a title");
			title = scString.nextLine();
			System.out.println("Enter a year");
			year = scInt.nextInt();
			System.out.println("Enter an author");
			author = scString.nextLine();
			Book bookDelete = new Book();
			bookDelete.setTitle(title);
			bookDelete.setYear(year);
			bookDelete.setAuthor(author);
			service.deleteBook(bookDelete);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 5:
			System.out.println("Enter an id");
			id = scInt.nextInt();
			service.listBookById(id);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 6:
			System.out.println("Enter an id");
			id = scInt.nextInt();
			System.out.println("Enter a title");
			title = scString.nextLine();
			System.out.println("Enter a year");
			year = scInt.nextInt();
			System.out.println("Enter a author");
			author = scString.nextLine();
			Book bookUpdate = new Book();
			bookUpdate.setId(id);
			bookUpdate.setTitle(title);
			bookUpdate.setYear(year);
			bookUpdate.setAuthor(author);
			service.updateBook(bookUpdate);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 7:
			this.MainMenuView();
			this.MainMenu();
			break;
		default:
			break;
		}
	}

	/* Выполнение действий с Employee entity */
	public void EmployeeMenu() {
		System.out.println("Enter a number");
		IEmployeeService service = new EmployeeServiceImpl();
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		int i = scInt.nextInt();
		String name;
		String eMail;
		int id = 0;
		String dateString;
		Date dateOfBirth = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		switch (i) {
		case 1:
			service.listEmployee();
			this.MainMenuView();
			break;
		case 2:
			System.out.println("Enter a name");
			name = scString.nextLine();
			System.out.println("Enter a date(yyyy-mm-dd)");
			dateString = scString.nextLine();
			try {
				dateOfBirth = dateFormat.parse(dateString);
			} catch (ParseException e) {
				System.err.println("Date error" + e);
			}
			System.out.println("Enter an e-mail");
			eMail = scString.nextLine();
			Employee employeeInsert = new Employee();
			employeeInsert.setName(name);
			employeeInsert.seteMail(eMail);
			employeeInsert.setDateOfBirth(dateOfBirth);
			service.insertEmployee(employeeInsert);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 3:
			System.out.println("Enter an id");
			id = scInt.nextInt();
			service.deleteEmployee(id);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 4:
			System.out.println("Enter a name");
			name = scString.nextLine();
			System.out.println("Enter a date(yyyy-mm-dd)");
			dateString = scString.nextLine();
			try {
				dateOfBirth = dateFormat.parse(dateString);
			} catch (ParseException e) {
				System.err.println("Date error" + e);
			}
			System.out.println("Enter an e-mail");
			eMail = scString.nextLine();
			Employee employeeDelete = new Employee();
			employeeDelete.setName(name);
			employeeDelete.seteMail(eMail);
			employeeDelete.setDateOfBirth(dateOfBirth);
			service.deleteBook(employeeDelete);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 5:
			System.out.println("Enter an id");
			id = scInt.nextInt();
			service.listEmployeeById(id);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 6:
			System.out.println("Enter an id");
			id = scInt.nextInt();
			System.out.println("Enter a name");
			name = scString.nextLine();
			System.out.println("Enter a date(yyyy-mm-dd)");
			dateString = scString.nextLine();
			try {
				dateOfBirth = dateFormat.parse(dateString);
			} catch (ParseException e) {
				System.err.println("Date error" + e);
			}
			System.out.println("Enter an e-mail");
			eMail = scString.nextLine();
			Employee employeeUpdate = new Employee();
			employeeUpdate.setId(id);
			employeeUpdate.setName(name);
			employeeUpdate.seteMail(eMail);
			employeeUpdate.setDateOfBirth(dateOfBirth);
			service.updateBook(employeeUpdate);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 7:
			break;
		default:
			break;
		}
	}

	/* Выполнение различных запросов */
	public void OperationMenu() {
		System.out.println("Enter a number");
		EmployeeServiceImpl serviceEmp = new EmployeeServiceImpl();
		BookServiceImpl serviceBook = new BookServiceImpl();
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		int i = scInt.nextInt();
		String oldTitle;
		String newTitle;
		switch (i) {
		case 1:
			serviceEmp.viewReportMoreThanOneBook();
			this.MainMenuView();
			this.MainMenu();
			break;
		case 2:
			serviceEmp.viewReportLessThanTwoBook();
			this.MainMenuView();
			this.MainMenu();
			break;
		case 3:
			System.out.println("Enter an old and new title");
			oldTitle = scString.nextLine();
			newTitle = scString.nextLine();
			serviceBook.renameBookTitle(oldTitle, newTitle);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 4:
			serviceEmp.randomEmployeeBookGenerator();
			this.MainMenuView();
			this.MainMenu();
			break;
		case 5:
			System.out.println("Enter a mask and new title");
			oldTitle = scString.nextLine();
			newTitle = scString.nextLine();
			serviceBook.renameMaskBookTitle(oldTitle, newTitle);
			this.MainMenuView();
			this.MainMenu();
			break;
		case 6:
			break;
		default:
			break;
		}
	}
}
