package hcmute.edu.vn.nuservice.controller;


import hcmute.edu.vn.nuservice.api.v1.dto.UserDto;
import hcmute.edu.vn.nuservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.nuservice.model.User;
import hcmute.edu.vn.nuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/nuser/")
public class NonUserController {
    @Autowired
    private UserService userServie;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public UserDto register(@RequestBody User user){
        user.setStatus(1);
        return userMapper.userToUserDto(userServie.registerUser(user));
    }

    @PostMapping("/login/{userName}/{passWord}")
    public UserDto login(@PathVariable String userName, @PathVariable String passWord){
        return userMapper.userToUserDto(userServie.findByEmailAndPassWord(userName, passWord));
    }

}
