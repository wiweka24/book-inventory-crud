package bookinventory.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookinventory.crud.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}