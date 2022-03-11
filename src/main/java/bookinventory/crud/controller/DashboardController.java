package bookinventory.crud.controller;

import bookinventory.crud.service.BookService;
import bookinventory.crud.service.CategoriesService;
import bookinventory.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  private UserService userService;
  private CategoriesService categoriesService;
  private BookService bookService;

  public DashboardController(UserService userService, CategoriesService categoriesService, BookService bookService){
    super();
    this.userService = userService;
    this.categoriesService = categoriesService;
    this.bookService = bookService;
  }

  @GetMapping("/dashboard")
  public String showDashboardView(Model model){
    model.addAttribute("total_book", bookService.countBook());
    model.addAttribute("total_user", userService.countUser());
    model.addAttribute("total_category", categoriesService.countCategory());
    return "dashboard";
  }
}
