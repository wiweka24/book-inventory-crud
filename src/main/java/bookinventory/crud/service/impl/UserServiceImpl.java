package bookinventory.crud.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bookinventory.crud.entity.User;
import bookinventory.crud.repository.UserRepository;
import bookinventory.crud.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    // public void uploadFile(MultipartFile file) throws IllegalStateException, IOException {
    //     file.transferTo(dest);
    // }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
 
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
