package hcmute.edu.vn.userservice.service;

import hcmute.edu.vn.userservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService {
    CrudRepository<User, Long> getRepo();
    User findByEmailAndPassWord(String userName, String passWord);
    User findByEmail(String email);
    User retrieveUserByEmail(String email);
    User retrieveUserByIdOrEmail(long id, String email);
    User updateProfile(User user);
}
