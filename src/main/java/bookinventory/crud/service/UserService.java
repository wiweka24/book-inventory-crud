package bookinventory.crud.service;

import bookinventory.crud.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    
    Iterable<User> getAllUsers();

    User saveUser(User user, MultipartFile multipartFile) throws IOException;

    User getUserById(Long id);
	
    User updateUser(User user, MultipartFile multipartFile)  throws IOException;

    void deleteUserById(Long id);

    Iterable<User> findByName(String keyword);

    Integer countBook();
}
