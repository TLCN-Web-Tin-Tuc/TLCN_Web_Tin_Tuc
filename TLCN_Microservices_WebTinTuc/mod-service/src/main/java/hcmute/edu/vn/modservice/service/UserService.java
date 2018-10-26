package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.nuservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService {
    CrudRepository<User, Long> getRepo();
    User findByEmailAndPassWord(String userName, String passWord);
    User registerUser(User user);
}
