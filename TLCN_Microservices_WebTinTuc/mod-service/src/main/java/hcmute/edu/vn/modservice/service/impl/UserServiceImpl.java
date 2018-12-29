package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.modservice.exception.NotFoundException;
import hcmute.edu.vn.modservice.model.User;
import hcmute.edu.vn.modservice.repository.UserRepository;
import hcmute.edu.vn.modservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CrudRepository<User, Long> getRepo() {
        return userRepository;
    }

    @Override
    public User findByEmailAndPassWord(String email, String passWord) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, passWord);
        if(!user.isPresent())
            throw new NotFoundException("User Not Found!!!");
        return user.get();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new NotFoundException("User Not Found!!!");
        return user.get();
    }

    @Override
    public User checkUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            return null;
        return user.get();
    }
}
