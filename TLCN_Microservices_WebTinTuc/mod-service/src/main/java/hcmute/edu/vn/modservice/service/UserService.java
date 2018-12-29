package hcmute.edu.vn.modservice.service;


import hcmute.edu.vn.modservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService {
    CrudRepository<User, Long> getRepo();
    User findByEmailAndPassWord(String email, String passWord);
    User findByEmail(String email);
    User checkUser(String email);
}
