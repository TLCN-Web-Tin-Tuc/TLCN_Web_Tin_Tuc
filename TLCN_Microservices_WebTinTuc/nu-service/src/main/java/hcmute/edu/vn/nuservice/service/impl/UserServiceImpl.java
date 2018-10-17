package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.model.Account;
import hcmute.edu.vn.nuservice.model.User;
import hcmute.edu.vn.nuservice.repository.AccountRepository;
import hcmute.edu.vn.nuservice.repository.UserRepository;
import hcmute.edu.vn.nuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    UserRepository userRepository;

   @Autowired
    AccountRepository accountRepository;
    @Override
    public CrudRepository<User, Long> getRepo() {
        return userRepository;
    }

    @Override
    public User createUser(User user, Long aid) {
        user.setDateCreated(new Date());
        user.setAccount(accountRepository.findById(aid).get());
        user.setUserCreated(user.getAccount().getEmail());
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }


}
