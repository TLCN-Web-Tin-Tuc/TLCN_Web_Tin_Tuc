package vn.hcmute.demo.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import vn.hcmute.demo.api.data.DataReturnList;
        import vn.hcmute.demo.entity.User;
        import vn.hcmute.demo.service.UserService;

        import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public DataReturnList<User> findAllUser(){
        DataReturnList<User> dataReturnList= new DataReturnList<>();
        dataReturnList.setMessage("Success");
        dataReturnList.setData((List<User>) userService.getRepo().findAll());
        dataReturnList.setSuccess("true");
        return dataReturnList;
    }
}
