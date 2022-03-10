package bookinventory.crud.controller;

import bookinventory.crud.entity.User;
import bookinventory.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/login")
  public String viewLogin(Model model, User user, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return "index";
    }
    return "index";
  }

  @GetMapping("/register")
  public String showRegisterForm(Model model){
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public ModelAndView processRegistration(User user, ModelMap model){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    user.setRole("STAFF");
    userRepository.save(user);
    model.addAttribute("success", "success create user");
    return new ModelAndView("redirect:/login", model);
  }
}
