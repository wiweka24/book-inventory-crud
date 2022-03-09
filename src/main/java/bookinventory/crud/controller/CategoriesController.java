package bookinventory.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bookinventory.crud.entity.Categories;
import bookinventory.crud.service.CategoriesService;

@Controller
public class CategoriesController {
  
  private CategoriesService categoriesService;

  public CategoriesController(CategoriesService categoriesService){
    super();
    this.categoriesService = categoriesService;
  }

  @GetMapping("/categories")
  public String listCategoString(Model model){
    model.addAttribute("categories", categoriesService.getAllCategories());
    return "categories";
  }

  @GetMapping("/categories/new")
  public String createCategoriesForm(Model model){
    Categories categories = new Categories();
    model.addAttribute("categories", categories);
    return "create_categories";
  }

  @PostMapping("/categories")
  public String saveCategories(@ModelAttribute("categories") Categories categories){
    categoriesService.saveCategories(categories);
    return "redirect:/categories";
  }

  @GetMapping("/categories/edit/{id}")
  public String editCategoriesForm(@PathVariable Long id, Model model){
    model.addAttribute("categories", categoriesService.getCategoriesById(id));
    return "edit_categories";
  }

  @PostMapping("/categories/{id}")
  public String updateCategories(@PathVariable Long id, @ModelAttribute("categories") Categories categories, Model model){
    Categories existingCategories = categoriesService.getCategoriesById(id);
    existingCategories.setId(id);
    existingCategories.setName(categories.getName());

    categoriesService.updateCategories(existingCategories);
    return "redirect:/categories";
  }

  @GetMapping("/categories/{id}")
  public String deleteCategories(@PathVariable Long id){
    categoriesService.deleteCategoriesById(id);
    return "redirect:/categories";
  }

  

}
