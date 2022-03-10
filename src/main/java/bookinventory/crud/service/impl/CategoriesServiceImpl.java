package bookinventory.crud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bookinventory.crud.entity.Categories;
import bookinventory.crud.repository.CategoriesRepository;
import bookinventory.crud.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {

  private CategoriesRepository categoriesRepository;

  public CategoriesServiceImpl(CategoriesRepository categoriesRepository){
    super();
    this.categoriesRepository = categoriesRepository;
  }

  @Override
  public List<Categories> getAllCategories() {
    return categoriesRepository.findAll();
  }

  @Override
  public Categories saveCategories(Categories categories) {
    return categoriesRepository.save(categories);
  }

  @Override
  public Categories getCategoriesById(Long id) {
    return categoriesRepository.findById(id).get();
  }

  @Override
  public Categories updateCategories(Categories categories) {
    return categoriesRepository.save(categories);
  }

  @Override
  public void deleteCategoriesById(Long id) {
    categoriesRepository.deleteById(id);
  }

  public Integer countCategory(){
    return categoriesRepository.countCategory();
  }
}
