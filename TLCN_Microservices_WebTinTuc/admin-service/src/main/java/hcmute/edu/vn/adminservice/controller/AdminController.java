package hcmute.edu.vn.adminservice.controller;

import hcmute.edu.vn.adminservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.adminservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.adminservice.api.v1.dto.UserDto;
import hcmute.edu.vn.adminservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.User;
import hcmute.edu.vn.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    @Autowired
    private UserService userService;


    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value = "", //
            method = RequestMethod.GET)
    public String admin(){
        return "permit admin";
    }

    // retrieve user by id //
    @GetMapping("/users/search")
    public DataReturnOne<UserDto> retrieveUserById(@RequestParam(required = false) long id, @RequestParam(required = false) String email){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userMapper.userToUserDto(userService.retrieveUserByIdOrEmail(id,email)));
        return dataReturnOne;
    }

    // retrieve all users
    @GetMapping("/users")
    public DataReturnList<User> retrieveAllUsers(){
        DataReturnList<User> dataReturnList=new DataReturnList<>();
        List<User> users=new ArrayList<User>();
        users = userService.retrieveAllUsers();
        if(users.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        dataReturnList.setMessage("success !");
        dataReturnList.setData(users);
        return dataReturnList;
    }
    // update role for user
    @PutMapping("/users/role/{uid}/{rid}")
    public DataReturnOne<UserDto> updateUserRole(@PathVariable long uid, @PathVariable long rid){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setMessage("success");
        dataReturnOne.setSuccess("true");
        dataReturnOne.setData(userMapper.userToUserDto(userService.updateRoleForUser(uid,rid)));
        return dataReturnOne;
    }

    // update status for user
    @PutMapping("/users/status/{uid}")
    public DataReturnOne<UserDto> updateUserStatus(@PathVariable long uid){
        DataReturnOne<UserDto> dataReturnOne=new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(userMapper.userToUserDto(userService.updateUserStatus(uid)));
        return dataReturnOne;
    }

}
