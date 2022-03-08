package bookinventory.crud.controller;

import bookinventory.crud.entity.User;
import bookinventory.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/")
  public String viewLogin(Model model){
    model.addAttribute("user", new User());
    return "index";
  }

  @GetMapping("/register")
  public String showRegisterForm(Model model){
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String processRegistration(User user, Model model){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    user.setRole("STAFF");
    userRepository.save(user);
    model.addAttribute("success", "success create user");
    return "index";
  }
}
