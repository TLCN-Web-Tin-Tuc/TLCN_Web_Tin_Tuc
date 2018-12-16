package hcmute.edu.vn.userservice.service.impl;

import hcmute.edu.vn.userservice.exception.NotFoundException;
import hcmute.edu.vn.userservice.model.User;
import hcmute.edu.vn.userservice.repository.UserRepository;
import hcmute.edu.vn.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

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

    @Override
    public User updateProfile(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        userOptional.orElseThrow(()->new NotFoundException("Not Found User. Please check again!"));
        User userUpdate = userOptional.get();
        userUpdate.setAddress(user.getAddress());
        //userUpdate.setAssign_permissions(user.getAssign_permissions());
        userUpdate.setAvatar(user.getAvatar());
        //userUpdate.setComments(user.getComments());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setPhone(user.getPhone());
        userUpdate.setSex(user.getSex());
        //userUpdate.setPassword(user.getPassword());
        return userRepository.save(userUpdate);
    }

}
