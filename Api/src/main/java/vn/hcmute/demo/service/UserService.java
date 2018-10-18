package vn.hcmute.demo.service;

import vn.hcmute.demo.entity.User;

import java.util.List;

public interface UserService {
    User retrieveById(long id);
    List<User> retrieveAllUsers();
    User registerUser(User user);
    User updateRegisterUser(User user, long pid);

}
