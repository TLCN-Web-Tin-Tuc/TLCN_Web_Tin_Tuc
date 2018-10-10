package hcmute.edu.vn.nuservice.controller;


import hcmute.edu.vn.nuservice.api.v1.dto.AccountDto;
import hcmute.edu.vn.nuservice.api.v1.mapper.AccountMapper;
import hcmute.edu.vn.nuservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/nuser/")
public class NonUserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

   /* @PostMapping("/register")
    public UserDto register(@RequestBody User user){
        user.setStatus(1);
        return userMapper.userToUserDto(userServie.registerUser(user));
    }*/

    @PostMapping("/login/{userName}/{passWord}")
    public AccountDto login(@PathVariable String userName, @PathVariable String passWord){
        return accountMapper.accountToAccountDto(accountService.findByUserNameAndPassWord(userName, passWord));
    }
}
