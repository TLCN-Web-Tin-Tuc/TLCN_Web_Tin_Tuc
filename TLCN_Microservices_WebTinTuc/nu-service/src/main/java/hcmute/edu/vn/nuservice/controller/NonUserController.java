package hcmute.edu.vn.nuservice.controller;


import hcmute.edu.vn.nuservice.api.v1.dto.AccountDto;
import hcmute.edu.vn.nuservice.api.v1.mapper.AccountMapper;
import hcmute.edu.vn.nuservice.model.Account;
import hcmute.edu.vn.nuservice.model.RegisterModel;
import hcmute.edu.vn.nuservice.model.User;
import hcmute.edu.vn.nuservice.service.AccountService;
import hcmute.edu.vn.nuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/nuser/")
public class NonUserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/login/{email}/{passWord}")
    public AccountDto login(@PathVariable String email, @PathVariable String passWord){
        return accountMapper.accountToAccountDto(accountService.findByUserNameAndPassWord(email, passWord));
    }

    @PostMapping("/register")
    public Account register(@RequestBody RegisterModel registerModel){
        System.out.println(registerModel);
        Account account=new Account();
        account.setEmail(registerModel.getEmail());
        account.setPassword(registerModel.getPassword());
        User user = new User();
        user.setFirstName(registerModel.getFirstName());
        user.setLastName(registerModel.getLastName());
        user.setAddress(registerModel.getAddress());
        user.setPhone(registerModel.getPhone());
        user.setSex(registerModel.getSex());
        user.setDateOfBirth(registerModel.getDateOfBirth());


//        Account accountCreated = accountService.registerAccount(account);
//        User userCreated=userService.createUser(user,accountCreated.getId());
//        Account accountUpdated=accountService.updateRegisterAccount(accountCreated,userCreated.getId());
        return accountService.registerAccount(account);

    }
}
