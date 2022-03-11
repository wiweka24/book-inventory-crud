package bookinventory.crud.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
	public Book saveBook(Book book, MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		book.setCover(fileName);

		Book savedBook = bookRepository.save(book);
		
		String uploadDir = "./src/main/resources/static/img/book-cover/" + savedBook.getId();
		
		Path uploadPath = Paths.get(uploadDir);

		try {
            if(!fileName.isEmpty()){
                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
            }

        } catch (IOException e){
            throw new IOException("Failed to create directory");
        }
         
        if(!fileName.isEmpty()){
            try( InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath , StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Failed to store file " + fileName + "!");
            }
        } else {
            book.setCover(null);
        } 

		return savedBook;
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

	@Override
	public Integer countBook(){
		return bookRepository.countBook();
	}
}
