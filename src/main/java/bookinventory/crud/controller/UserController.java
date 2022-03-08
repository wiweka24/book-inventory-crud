package bookinventory.crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
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
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("picture") String file) throws IOException {
        //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(file);
        // user.setPicture(fileName);
        // User saveUser = userService.saveUser(user);

        // String uploadDir = "./user-pictures/" + saveUser.getId();

        // Path uploadPath = Paths.get(uploadDir);

        // if(!Files.exists(uploadPath)){
        //     Files.createDirectories(uploadPath);
        // }
        
        // try( InputStream inputStream = file.getInputStream()) {
        //     Path filePath = uploadPath.resolve(fileName);
        //     System.out.println(filePath.toFile().getAbsolutePath());
        //     Files.copy(inputStream, filePath ,StandardCopyOption.REPLACE_EXISTING);
        // } catch (IOException e) {
        //     throw new IOException("Failed to store file " + fileName + "!");
        // }
       
       


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
            Model model) {

        // get book from database by id
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        // save updated user object
        userService.updateUser(existingUser);
        return "redirect:/users";
    }

    // handler method to handle delete book request
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
