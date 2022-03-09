package bookinventory.crud.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import bookinventory.crud.entity.Book;

public interface BookService {
	List<Book> getAllBooks();
	
	Book saveBook(Book book, MultipartFile multipartFile) throws IOException;

	Book getBookById(Long id);
	
	Book updateBook(Book book);
	
	void deleteBookById(Long id);
}