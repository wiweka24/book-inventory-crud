package bookinventory.crud.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import bookinventory.crud.entity.User;
import bookinventory.crud.repository.UserRepository;
import bookinventory.crud.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        user.setPicture(fileName);

        User savedUser = userRepository.save(user);

        String uploadDir = "./src/main/resources/static/img/user-pictures/" + savedUser.getId();

        Path uploadPath = Paths.get(uploadDir);

        try {
            if(!fileName.isEmpty()){
                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
            }

        } catch (IOException e){
            throw new IOException("Failed to create directory");
        }

        if(!fileName.isEmpty()){
            try( InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath , StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Failed to store file " + fileName + "!");
            }
        } else {
            user.setPicture(null);
        }

        return savedUser;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public User updateUser(User user, MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.setPicture(fileName);

        User savedUser = userRepository.save(user);

        String uploadDir = "./src/main/resources/static/img/user-pictures/" + savedUser.getId();

        Path uploadPath = Paths.get(uploadDir);

        try {
            if(!fileName.isEmpty()){
                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
            }

        } catch (IOException e){
            throw new IOException("Failed to create directory");
        }

        if(!fileName.isEmpty()){
            try( InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath);
                Files.copy(inputStream, filePath , StandardCopyOption.REPLACE_EXISTING);
                //Files.delete(uploadDir);
            } catch (IOException e) {
                throw new IOException("Failed to store file " + fileName + "!");
            }
        } else {
            user.setPicture(null);
        }

        return savedUser;
    }
 
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Integer countUser(){
        return userRepository.countUser();
    }
}