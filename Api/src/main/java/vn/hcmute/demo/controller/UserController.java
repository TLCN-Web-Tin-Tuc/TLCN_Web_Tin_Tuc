package vn.hcmute.demo.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import vn.hcmute.demo.api.data.DataReturnList;
        import vn.hcmute.demo.entity.Person;
        import vn.hcmute.demo.entity.RegisterModel;
        import vn.hcmute.demo.entity.User;
        import vn.hcmute.demo.service.PersonService;
        import vn.hcmute.demo.service.UserService;

        import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterModel registerModel){
        System.out.println(registerModel);
        User user=new User();
        user.setEmail(registerModel.getEmail());
        user.setPassword(registerModel.getPassword());
        Person person=new Person();
        person.setFirstName(registerModel.getFirstName());
        person.setLastName(registerModel.getLastName());
        person.setPhone(registerModel.getPhone());
        person.setSex(registerModel.getSex());
        person.setDateOfBirth(registerModel.getDob());
        person.setAddress(registerModel.getAddress());

        User userCreated=userService.registerUser(user);
        Person personCreated=personService.createPerson(person,userCreated.getId());
        User userUpdated=userService.updateRegisterUser(userCreated,personCreated.getId());
        return userUpdated;
    }
}
