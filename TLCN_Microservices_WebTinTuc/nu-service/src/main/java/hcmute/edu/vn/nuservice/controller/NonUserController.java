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

import java.util.Date;
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
    public  DataReturnOne<UserDto> register(@RequestBody User user){
        User testUser = userServie.checkUser(user.getEmail());

        DataReturnOne<UserDto> dataReturnOne = new DataReturnOne<>();

        if(testUser != null){
            dataReturnOne.setMessage("Tai khoan da ton tai !!!");
            dataReturnOne.setSuccess("false");
        }
        else {
            Date date = new Date();
            user.setDateCreated(date);
            user.setDateUpdated(date);

            user.setUserCreated(user.getEmail());
            user.setUserUpdated(user.getEmail());

            user.setStatus(1);

            dataReturnOne.setMessage("Tao tai khoan thanh cong !!!");
            dataReturnOne.setSuccess("true");
            dataReturnOne.setData(userMapper.userToUserDto(userServie.registerUser(user)));
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
    @GetMapping("/find-user/{email}")
    public DataReturnOne<User> checkUser(@PathVariable String email)
    {
        DataReturnOne<User> dataReturnOne = new DataReturnOne<>();
        User user = new User();
        try {
            user = userServie.findByEmail(email);
            dataReturnOne.setMessage("Đã tìm thấy thành công");
            dataReturnOne.setData(user);
        }
        catch (NotFoundException ex) {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Không tồn tại");

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
