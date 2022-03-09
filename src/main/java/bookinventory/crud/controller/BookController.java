package bookinventory.crud.controller;

import java.io.*;
import java.nio.file.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import bookinventory.crud.entity.Book;
import bookinventory.crud.service.BookService;

@Controller
public class BookController {
	
	private BookService bookService;

	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	// handler method to handle list books and return mode and view
	@GetMapping("/books")
	public String listBooks(Model model) {
		model.addAttribute("books", bookService.getAllBooks());
		return "books";
	}
	
	@GetMapping("/books/new")
	public String createBookForm(Model model) {
		// create book object to hold book form data
		Book book = new Book();
		model.addAttribute("book", book);
		return "create_book";		
	}
	
	@PostMapping("/books")
	public String saveBook(@ModelAttribute("book") Book book, @RequestParam("file") MultipartFile file) throws IOException {

		bookService.saveBook(book, file);

		return "redirect:/books";
	}
	
	@GetMapping("/books/edit/{id}")
	public String editBookForm(@PathVariable Long id, Model model) {
		model.addAttribute("book", bookService.getBookById(id));
		return "edit_book";
	}

	@PostMapping("/books/{id}")
	public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book book, Model model) {
		
		// get book from database by id
		Book existingBook = bookService.getBookById(id);
		existingBook.setId(id);
		existingBook.setTitle(book.getTitle());
		existingBook.setCategory(book.getCategory());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setPublisher(book.getPublisher());
		existingBook.setDate(book.getDate());
		existingBook.setStock(book.getStock());
		existingBook.setDescription(book.getDescription());
		existingBook.setCover(book.getCover());
		
		// save updated book object
		bookService.updateBook(existingBook);
		return "redirect:/books";		
	}
	
	// handler method to handle delete book request
	
	@GetMapping("/books/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		return "redirect:/books";
	}
}
