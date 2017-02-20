package by.htp.library.service;

import java.util.List;

import by.htp.library.dao.BookDaoImpl;
import by.htp.library.domain.Book;

public class BookServiceImpl implements IBookService{

	@Override
	public void listBooks() {
		
		BookDaoImpl bookdao=new BookDaoImpl();
		List<Book> books=bookdao.findAll();
		for(int i=0;i<books.size();i++){
			System.out.println(books.get(i).toString());
		}
	}

	@Override
	public void insertBook(Book book) {
		boolean flag=false;
		BookDaoImpl bookdao=new BookDaoImpl();
		flag=bookdao.create(book);
		if(flag){
			System.out.println("Insert book completed successfully");
		}else{
			System.out.println("Insert book not completed");
		}
	}

	@Override
	public void deleteBook(int id) {
		boolean flag=false;
		BookDaoImpl bookdao=new BookDaoImpl();
		flag=bookdao.delete(id);
		if(flag){
			System.out.println("Delete book completed successfully");
		}else{
			System.out.println("Delete book not completed");
		}
	}

	@Override
	public void listBookById(int id) {
		BookDaoImpl bookdao=new BookDaoImpl();
		Book book=bookdao.findEntityById(id);
		System.out.println(book.toString());
	}

	@Override
	public void deleteBook(Book book) {
		boolean flag=false;
		BookDaoImpl bookdao=new BookDaoImpl();
		flag=bookdao.delete(book);
		if(flag){
			System.out.println("Delete book completed successfully");
		}else{
			System.out.println("Delete book not completed");
		}
	}

	@Override
	public void updateBook(Book book) {
		boolean flag=false;
		BookDaoImpl bookdao=new BookDaoImpl();
		flag=bookdao.update(book);
		if(flag){
			System.out.println("Update book completed successfully");
		}else{
			System.out.println("Update book not completed");
		}	
	}

	public void renameBookTitle(String oldTitle,String newTitle){
		boolean flag=false;
		BookDaoImpl bookdao=new BookDaoImpl();
		flag=bookdao.renameBook(oldTitle, newTitle);
		if(flag){
			System.out.println("Rename book completed successfully");
		}else{
			System.out.println("Rename book not completed");
		}
	}
	
	public void renameMaskBookTitle(String maskTitle,String newTitle){
		boolean flag=false;
		BookDaoImpl bookdao=new BookDaoImpl();
		flag=bookdao.renameMaskBook(maskTitle, newTitle);
		if(flag){
			System.out.println("Rename book completed successfully");
		}else{
			System.out.println("Rename book not completed");
		}
	}
}
