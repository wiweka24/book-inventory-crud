package bookinventory.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookinventory.crud.entity.Categories;
import org.springframework.data.jpa.repository.Query;


public interface CategoriesRepository extends JpaRepository<Categories, Long> {
  @Query(value = "SELECT count(id) FROM categories", nativeQuery = true)
  Integer countCategory();
}
