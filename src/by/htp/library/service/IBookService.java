package by.htp.library.service;

import by.htp.library.domain.Book;

public interface IBookService {

	void listBooks();

	void insertBook(Book book);

	void deleteBook(int id);

	void deleteBook(Book book);

	void listBookById(int id);

	void updateBook(Book book);

}
