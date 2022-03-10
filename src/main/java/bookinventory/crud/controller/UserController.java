package bookinventory.crud.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bookinventory.crud.entity.User;
import bookinventory.crud.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    // handler methot to handle list books and return mode and view
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/users/new")
    public String createUserForm(Model model) {
        // create book object to hold book form data
        model.addAttribute("user", new User());
        return "create_user";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file) throws IOException {
        user.setPassword(userService.ecryptPassword(user.getPassword()));

        userService.saveUser(user, file);

        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id,
            @ModelAttribute("user") User user,
            Model model){

        // get book from database by id
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setPicture(user.getPicture());
        existingUser.setAddress(user.getAddress());
        if(user.getPassword() == null){
            user.setPassword(null);
        } else {
            existingUser.setPassword(userService.ecryptPassword(existingUser.getPassword()));
        }
        System.out.println(user.getPicture());
        // save updated user object
        //userService.updateUser(existingUser);
        return "redirect:/users";
    }

    // handler method to handle delete book request
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
