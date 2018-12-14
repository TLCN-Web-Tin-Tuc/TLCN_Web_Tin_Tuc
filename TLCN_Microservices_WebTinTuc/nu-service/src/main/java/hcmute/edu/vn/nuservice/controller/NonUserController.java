package hcmute.edu.vn.nuservice.controller;


import hcmute.edu.vn.nuservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.nuservice.api.v1.dto.CatDto;
import hcmute.edu.vn.nuservice.api.v1.dto.ItemDto;
import hcmute.edu.vn.nuservice.api.v1.dto.UserDto;
import hcmute.edu.vn.nuservice.api.v1.mapper.CatMapper;
import hcmute.edu.vn.nuservice.api.v1.mapper.ItemMapper;
import hcmute.edu.vn.nuservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.nuservice.exception.NotFoundException;
import hcmute.edu.vn.nuservice.model.Report;
import hcmute.edu.vn.nuservice.model.User;
import hcmute.edu.vn.nuservice.service.CatService;
import hcmute.edu.vn.nuservice.service.ItemService;
import hcmute.edu.vn.nuservice.service.ReportService;
import hcmute.edu.vn.nuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/nuser/")
@CrossOrigin
public class NonUserController {
    @Autowired
    private UserService userServie;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CatMapper catMapper;

    @Autowired
    private CatService catService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ReportService reportService;

    @PostMapping("/register")
    public  DataReturnOne<UserDto> register(@Valid @RequestBody User user){
        DataReturnOne<UserDto> dataReturnOne = new DataReturnOne<>();

        try {
            User user1 = userServie.registerUser(user);
            dataReturnOne.setMessage("Tạo thành công tài khoản");
            dataReturnOne.setData(userMapper.userToUserDto(user1));
        }
        catch (NotFoundException ex) {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Email đã có người sử dụng");

        }
        return dataReturnOne;
    }

    @PostMapping("/login/{email}/{passWord}")
    public DataReturnOne<UserDto> login(@PathVariable String email, @PathVariable String passWord){
        DataReturnOne<UserDto> dataReturnOne = new DataReturnOne<>();
        User user = new User();
        try {
            user = userServie.findByEmailAndPassWord(email, passWord);
            dataReturnOne.setMessage("Đăng nhập thành công");
            dataReturnOne.setData(userMapper.userToUserDto(userServie.findByEmailAndPassWord(email, passWord)));
        }
        catch (NotFoundException ex) {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Sai Email hoặc Mật khẩu");

        }


        return dataReturnOne;
    }
    @GetMapping("/check-user/{email}")
    public DataReturnOne<User> checkUser(@PathVariable String email)
    {
        DataReturnOne<User> dataReturnOne = new DataReturnOne<>();
        User user = new User();
        try {
            user = userServie.findByEmail(email);
            dataReturnOne.setMessage("Email đã tồn tại");
        }
        catch (NotFoundException ex) {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Email chưa tồn tại");

        }
        return dataReturnOne;
    }
    @GetMapping("/get-all-cat")
    public List<CatDto> getAllCat()
    {
                return catMapper.listcatTolistCatDto(catService.getRepo().findAll());
    }

    @GetMapping("/get-all-item")
    public List<ItemDto> getAllItem()
    {
        return itemMapper.listitemTolistItemDto(itemService.getRepo().findAll());
    }

    @PostMapping("/send-report")
    public DataReturnOne<Report> SendReport(@RequestBody Report report)
    {
        Report report1 = reportService.getRepo().save(report);
        DataReturnOne<Report> dataReturnOne = new DataReturnOne<>();
        if(report1!=null){
            dataReturnOne.setData(report1);
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Send Report success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Send Report Fail");
        }
        return dataReturnOne;
    }


}
