package bookinventory.crud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bookinventory.crud.entity.Book;
import bookinventory.crud.repository.BookRepository;
import bookinventory.crud.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book getBookById(Long id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);	
	}
}
