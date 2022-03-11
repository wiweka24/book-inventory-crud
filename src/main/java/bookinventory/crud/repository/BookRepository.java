package bookinventory.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookinventory.crud.entity.Book;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>{
  @Query(value = "SELECT count(id) FROM books", nativeQuery = true)
  Integer countBook();
}