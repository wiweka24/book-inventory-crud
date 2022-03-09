package bookinventory.crud.service;

import java.util.List;

import bookinventory.crud.entity.Categories;

public interface CategoriesService {
  List<Categories> getAllCategories();

  Categories saveCategories(Categories categories);
  Categories getCategoriesById(Long id);
  Categories updateCategories(Categories categories);
  void deleteCategoriesById(Long id);
}
