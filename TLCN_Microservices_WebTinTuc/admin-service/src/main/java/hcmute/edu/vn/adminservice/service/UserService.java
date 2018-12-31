package hcmute.edu.vn.adminservice.service;

import hcmute.edu.vn.adminservice.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserService {
    CrudRepository<User, Long> getRepo();
    User findByEmailAndPassWord(String userName, String passWord);
    List<User> retrieveAllUsers();
    User updateRoleForUser(long uid, long rid);
    User updateUserStatus(long id, String userUpdate);
    User retrieveUserByEmail(String email);
    User retrieveUserByIdOrEmail(long id, String email);
    User retrieveUserById(long uid);
}
