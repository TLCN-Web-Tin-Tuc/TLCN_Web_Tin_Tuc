package hcmute.edu.vn.userservice.controller;

import hcmute.edu.vn.userservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.userservice.api.v1.dto.UserDto;
import hcmute.edu.vn.userservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.userservice.exception.NotFoundException;
import hcmute.edu.vn.userservice.model.Item_Access;
import hcmute.edu.vn.userservice.model.Items;
import hcmute.edu.vn.userservice.model.User;
import hcmute.edu.vn.userservice.service.ItemAccessService;
import hcmute.edu.vn.userservice.service.ItemService;
import hcmute.edu.vn.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/user/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    ItemAccessService itemAccessService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/items/detail/{itemid}")
    public DataReturnOne<Items> Detail(@PathVariable("itemid") long itemid){

        Items items1 = itemService.itemDetail(itemid);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(items1!=null){
            dataReturnOne.setData(items1);
            dataReturnOne.setMessage("View Item success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("View item fail");
        }
        return dataReturnOne;
    }

    @PostMapping("/items/like/{itemid}/{email}")
    public DataReturnOne<Items> LikeItem(@PathVariable("itemid") long itemid,@PathVariable("email") String email){

        Items items1 = itemService.likeItem(itemid);
        Item_Access item_access = itemAccessService.userLike(itemid,email);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(items1!=null && item_access !=null){
            dataReturnOne.setData(items1);
            dataReturnOne.setMessage("Like Item success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Like item fail");
        }
        return dataReturnOne;
    }

    @PostMapping("/updateprofile")
    public DataReturnOne<User> updateProfile(@RequestBody User user){
        DataReturnOne<User> userDataReturnOne=new DataReturnOne<>();
        try {
            user.setUserUpdated(user.getEmail());
            user.setDateUpdated(new Date());
            User userUpdate = userService.updateProfile(user);
            userDataReturnOne.setSuccess("true");
            userDataReturnOne.setMessage("success");
            userDataReturnOne.setData(userUpdate);
        }catch (NotFoundException ex){
            userDataReturnOne.setSuccess("false");
            userDataReturnOne.setMessage("error");
            userDataReturnOne.setData(null);
        }
        return userDataReturnOne;
    }

    @GetMapping("/profile/{email}")
    public DataReturnOne<User> Profile(@PathVariable("email") String email){
        DataReturnOne<User> userDataReturnOne=new DataReturnOne<>();
        try {
            User userUpdate = userService.findByEmail(email);
            userDataReturnOne.setSuccess("true");
            userDataReturnOne.setMessage("success");
            userDataReturnOne.setData(userUpdate);
        }catch (NotFoundException ex){
            userDataReturnOne.setSuccess("false");
            userDataReturnOne.setMessage("error");
            userDataReturnOne.setData(null);
        }
        return userDataReturnOne;
    }

    @PostMapping("/doimatkhau/{email}/{oldPassword}/{newPassword}")
    public DataReturnOne<UserDto> doiMatKhau(@PathVariable String email,
                                                @PathVariable String oldPassword, @PathVariable String newPassword){
        DataReturnOne<UserDto> dataReturnOne = new DataReturnOne<>();
        User user;
        try {
            user = userService.findByEmailAndPassWord(email, oldPassword);
            dataReturnOne.setMessage("Đổi mật khẩu thành công !!!");
            user.setPassword(newPassword);
            user.setUserUpdated(email);
            user.setDateUpdated(new Date());
            dataReturnOne.setData(userMapper.userToUserDto(userService.getRepo().save(user)));
        }
        catch (NotFoundException ex) {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Nhập sai Mật khẩu");

        }

        return dataReturnOne;
    }

    @PostMapping("/doiavatar")
    public DataReturnOne<UserDto> changeAvatar(@RequestBody User user){
        DataReturnOne<UserDto> dataReturnOne = new DataReturnOne<>();
        User userTemp = userService.findByEmail(user.getEmail());
        dataReturnOne.setMessage("Đổi avatar thành công !!!");
        userTemp.setAvatar(user.getAvatar());
        dataReturnOne.setSuccess("true");
        userTemp.setUserUpdated(userTemp.getEmail());
        userTemp.setDateUpdated(new Date());
        dataReturnOne.setData(userMapper.userToUserDto(userService.getRepo().save(userTemp)));

        return dataReturnOne;
    }
}
