package vn.hcmute.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.demo.entity.Role;
import vn.hcmute.demo.entity.User;
import vn.hcmute.demo.exception.UserNotFoundException;
import vn.hcmute.demo.repository.PersonRepository;
import vn.hcmute.demo.repository.RoleRepository;
import vn.hcmute.demo.repository.UserRepository;
import vn.hcmute.demo.service.UserService;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;
    @Override
    public User retrieveById(long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(!userOptional.isPresent())
            throw new UserNotFoundException("user not found");
        return userOptional.get();
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<User> users=userRepository.findAll();
        if(users.isEmpty())
            throw new UserNotFoundException("user not found");
        return users;
    }

    @Override
    public User registerUser(User user) {
    // register will set role default is ROLE_USER
        user.setStatus(1);
        Role role = roleRepository.findByRname("ROLE_USER").get();
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User updateRegisterUser(User user,long pid) {
        user.setPerson(personRepository.findById(pid).get());
        return userRepository.save(user);
    }
}
