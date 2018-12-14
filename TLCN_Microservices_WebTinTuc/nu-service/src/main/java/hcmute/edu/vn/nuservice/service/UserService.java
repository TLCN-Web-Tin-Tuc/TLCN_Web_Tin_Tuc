package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService {
    CrudRepository<User, Long> getRepo();
    User findByEmailAndPassWord(String email, String passWord);
    User registerUser(User user);
    User findByEmail(String email);

}
