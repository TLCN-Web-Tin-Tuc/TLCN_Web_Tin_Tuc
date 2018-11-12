package hcmute.edu.vn.nuservice.controller;


import hcmute.edu.vn.nuservice.api.v1.dto.CatDto;
import hcmute.edu.vn.nuservice.api.v1.dto.ItemDto;
import hcmute.edu.vn.nuservice.api.v1.dto.UserDto;
import hcmute.edu.vn.nuservice.api.v1.mapper.CatMapper;
import hcmute.edu.vn.nuservice.api.v1.mapper.ItemMapper;
import hcmute.edu.vn.nuservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.nuservice.model.Cat;
import hcmute.edu.vn.nuservice.model.User;
import hcmute.edu.vn.nuservice.service.CatService;
import hcmute.edu.vn.nuservice.service.ItemService;
import hcmute.edu.vn.nuservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/nuser/")
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

    @PostMapping("/register")
    public UserDto register(@RequestBody User user){
        user.setStatus(1);
        return userMapper.userToUserDto(userServie.registerUser(user));
    }

    @PostMapping("/login/{email}/{passWord}")
    public UserDto login(@PathVariable String email, @PathVariable String passWord){
        return userMapper.userToUserDto(userServie.findByEmailAndPassWord(email, passWord));
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

}
