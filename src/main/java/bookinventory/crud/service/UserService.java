package bookinventory.crud.service;

import bookinventory.crud.entity.User;

public interface UserService {
    
    Iterable<User> getAllUsers();

    User saveUser(User user);

    User getUserById(Long id);
	
	User updateUser(User user);
	
	void deleteUserById(Long id);

}
