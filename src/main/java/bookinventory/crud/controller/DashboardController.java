package bookinventory.crud.controller;

import bookinventory.crud.service.CategoriesService;
import bookinventory.crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

  private UserService userService;
  private CategoriesService categoriesService;

  public DashboardController(UserService userService, CategoriesService categoriesService){
    super();
    this.userService = userService;
    this.categoriesService = categoriesService;
  }

  @GetMapping("/dashboard")
  public String showDashboardView(Model model){
    model.addAttribute("total_book", userService.countBook());
    model.addAttribute("total_category", categoriesService.countCategory());
    return "dashboard";
  }
}
