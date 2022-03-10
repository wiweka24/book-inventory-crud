package bookinventory.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bookinventory.crud.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    Iterable<User> findByNameContains(String keyword);

    @Query(value = "SELECT count(id) FROM books", nativeQuery = true)
    Integer countBook();

}
    
