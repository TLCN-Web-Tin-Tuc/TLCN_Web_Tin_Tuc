package hcmute.edu.vn.adminservice.service.impl;

import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.Role;
import hcmute.edu.vn.adminservice.model.User;
import hcmute.edu.vn.adminservice.repository.RoleRepository;
import hcmute.edu.vn.adminservice.repository.UserRepository;
import hcmute.edu.vn.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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
    public List<User> retrieveAllUsers() {
        List<User> users=userRepository.findAll();
        if(users.isEmpty())
            throw new NotFoundException("user not found");
        return users;
    }
    @Override
    public User updateRoleForUser(long uid, long rid) {
        Optional<User> userOptional =userRepository.findById(uid);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update role for this user");
        Optional<Role> roleOptional=roleRepository.findById(rid);
        if(!roleOptional.isPresent())
            throw new NotFoundException("Role not found. Please check again");
        User user=userOptional.get();
        Role role=roleOptional.get();
        Set<Role> userRoles=user.getRoles();
        if(userRoles.contains(role))
            userRoles.remove(role);
        else
            userRoles.add(role);
        user.setRoles(userRoles);
        return userRepository.save(user);
    }

    @Override
    public User updateUserStatus(long uid){
        Optional<User> userOptional=userRepository.findById(uid);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update status for this user");
        User user=userOptional.get();
        if(user.getStatus()==0)
            user.setStatus(1);
        else user.setStatus(0);
        return userRepository.save(user);
    }

    @Override
    public User retrieveUserByEmail(String email){
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update role for this user");
        return userOptional.get();
    }

    @Override
    public User retrieveUserByIdOrEmail(long id, String email){
        Optional<User> userOptional=userRepository.findByIdOrEmail(id,email);
        if(!userOptional.isPresent())
            throw new NotFoundException("User not found. Could not update role for this user");
        return userOptional.get();
    }



}
