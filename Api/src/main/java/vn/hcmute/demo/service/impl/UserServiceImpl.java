package vn.hcmute.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import vn.hcmute.demo.entity.User;
import vn.hcmute.demo.repository.UserRepository;
import vn.hcmute.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CrudRepository<User, Long> getRepo() {
        return userRepository;
    }
}
