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
    public UserDto register(@RequestBody User user){
        user.setStatus(1);
        return userMapper.userToUserDto(userServie.registerUser(user));
    }

    @PostMapping("/login/{email}/{passWord}")
    public DataReturnOne<UserDto> login(@PathVariable String email, @PathVariable String passWord){
        DataReturnOne<UserDto> dataReturnOne = new DataReturnOne<>();
        User user = new User();
        try {
            user = userServie.findByEmailAndPassWord(email, passWord);
            dataReturnOne.setMessage("Đang nhập thành công");
            dataReturnOne.setData(userMapper.userToUserDto(userServie.findByEmailAndPassWord(email, passWord)));
        }
        catch (NotFoundException ex) {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Sai Email hoặc mật khẩu");

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
