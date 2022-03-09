package bookinventory.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bookinventory.crud.entity.Categories;


public interface CategoriesRepository extends JpaRepository<Categories, Long> {
  
}
