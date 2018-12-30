package hcmute.edu.vn.nuservice.controller;


import hcmute.edu.vn.nuservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.nuservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.nuservice.api.v1.dto.CatDto;
import hcmute.edu.vn.nuservice.api.v1.dto.CatOfItemDto;
import hcmute.edu.vn.nuservice.api.v1.dto.ItemDto;
import hcmute.edu.vn.nuservice.api.v1.dto.UserDto;
import hcmute.edu.vn.nuservice.api.v1.mapper.CatMapper;
import hcmute.edu.vn.nuservice.api.v1.mapper.CatOfItemMapper;
import hcmute.edu.vn.nuservice.api.v1.mapper.ItemMapper;
import hcmute.edu.vn.nuservice.api.v1.mapper.UserMapper;
import hcmute.edu.vn.nuservice.exception.NotFoundException;
import hcmute.edu.vn.nuservice.model.*;
import hcmute.edu.vn.nuservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/nuser/")
@CrossOrigin(origins = "http://localhost:4200")
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
    private CatItemService catItemService;

    @Autowired
    private CatOfItemMapper catOfItemMapper;

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
            user.setUserCreated(user.getEmail());
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
            if(user.getStatus() == 0)
            {
                dataReturnOne.setSuccess("false");
                dataReturnOne.setMessage("Tài khoản đã hủy kích hoạt");
            } else {
                dataReturnOne.setMessage("Đăng nhập thành công");
                dataReturnOne.setData(userMapper.userToUserDto(userServie.findByEmailAndPassWord(email, passWord)));
            }

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
            user.setItemAccesses(null);
            dataReturnOne.setMessage("Đã tìm thấy thành công");
            dataReturnOne.setData(user);
        }
        catch (NotFoundException ex) {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Không tồn tại");

        }
        return dataReturnOne;
    }
    @GetMapping("/getcatofitem/{itemid}")
    public DataReturnList<CatOfItemDto> getAllCatOfItem(@PathVariable Long itemid)
    {
        DataReturnList<CatOfItemDto> dataReturnList = new DataReturnList<>();
        List<Cat_Item> cat_items = new ArrayList<Cat_Item>();
        try {

            dataReturnList.setMessage("Đã tìm thấy thành công");
            dataReturnList.setData(catItemService.retrieveAllCatItem(itemid)
                    .stream()
                    .map(catOfItemMapper::listcatitemTolistCatItemDto)
                    .collect(Collectors.toList()));
        }
        catch (NotFoundException ex) {
            dataReturnList.setSuccess("false");
            dataReturnList.setMessage("Không tồn tại");

        }
        return dataReturnList;
    }

    @GetMapping("/item/updateview/{itemid}")
    public DataReturnOne<Item> updateViewItem(@PathVariable Long itemid)
    {
        DataReturnOne<Item> dataReturnOne = new DataReturnOne<>();
        Item item = itemService.updateView(itemid);
        if(item!=null){
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update view success");
            dataReturnOne.setSuccess("true");
        } else{
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update view fail");
            dataReturnOne.setSuccess("false");
        }
        return dataReturnOne;
    }

    @GetMapping("/get-all-cat")
    public List<CatDto> getAllCat()
    {
        return catMapper.listcatTolistCatDto(catService.getRepo().findAll());
    }

    @GetMapping("/get-item-desc-day")
    public DataReturnList<ItemDto> getAllItemDescDay()
    {
        DataReturnList<ItemDto> catOfItemDtoDataReturnList = new DataReturnList<>();
        catOfItemDtoDataReturnList.setMessage("Lấy dữ liệu thành công");
        catOfItemDtoDataReturnList.setData(itemService.retrieveItemsDescDay().stream().map(itemMapper::listitemTolistItemDto)
                .collect(Collectors.toList()));
        return catOfItemDtoDataReturnList;
    }

    @GetMapping("/get-item-desc")
    public DataReturnList<ItemDto> getAllItemDesc()
    {
        DataReturnList<ItemDto> catOfItemDtoDataReturnList = new DataReturnList<>();
        catOfItemDtoDataReturnList.setMessage("Lấy dữ liệu thành công");
        catOfItemDtoDataReturnList.setData(itemService.retrieveItemsDesc().stream().map(itemMapper::listitemTolistItemDto)
                .collect(Collectors.toList()));
        return catOfItemDtoDataReturnList;
    }

    @GetMapping("/get-item-desc-like")
    public DataReturnList<ItemDto> getAllItemDescLike()
    {
        DataReturnList<ItemDto> itemDtoDataReturnList = new DataReturnList<>();
        itemDtoDataReturnList.setMessage("Lấy dữ liệu thành công");
        itemDtoDataReturnList.setData(itemService.retrieveItemsDescLike().stream().map(itemMapper::listitemTolistItemDto)
                .collect(Collectors.toList()));
        return itemDtoDataReturnList;
    }

    @GetMapping("/get-item-desc-day-by-cat/{catId}")
    public DataReturnList<CatOfItemDto> getAllItemDescDay(@PathVariable Long catId)
    {
        DataReturnList<CatOfItemDto> itemDtoDataReturnList = new DataReturnList<>();
        itemDtoDataReturnList.setMessage("Lấy dữ liệu thành công");
        itemDtoDataReturnList.setData(catItemService.retrieveAllByCatId(catId).stream().map(catOfItemMapper::listcatitemTolistCatItemDto)
                                                                                        .collect(Collectors.toList()));

        return itemDtoDataReturnList;
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


    @GetMapping("/cat/checked")
    public DataReturnList<CatDto> CategoryChecked(){
        List<Cat> categoriesChecked = catService.retrieveAllCatChecked();
        DataReturnList<CatDto> dtrList = new DataReturnList<>();
        dtrList.setData(catService.retrieveAllCatChecked().stream().map(catMapper::catToCatDto)
                .collect(Collectors.toList()));
        dtrList.setSuccess("true");
        dtrList.setMessage("success");
        return dtrList;
    }

    @GetMapping("/cat/parentcatchecked")
    public DataReturnList<CatDto> ParentCategoryChecked(){
        DataReturnList<CatDto> dtrList = new DataReturnList<>();
        dtrList.setData(catService.retrieveAllParentCatChecked().stream().map(catMapper::catToCatDto)
                .collect(Collectors.toList()));
        dtrList.setSuccess("true");
        dtrList.setMessage("success");
        return dtrList;
    }

    @GetMapping("/cat/childcat/{id}")
    public DataReturnList<CatDto> ParentCategoryChecked(@PathVariable int id){
        DataReturnList<CatDto> dtrList = new DataReturnList<>();
        dtrList.setData(catService.retrieveAllChildCatChecked(id).stream().map(catMapper::catToCatDto)
                .collect(Collectors.toList()));
        dtrList.setSuccess("true");
        dtrList.setMessage("success");
        return dtrList;
    }

    @GetMapping("/itemsbycat")
    public DataReturnList<CatOfItemDto> getAllItemPaging(@RequestParam Optional<Long> id, @RequestParam Optional<Integer> page
            , @RequestParam Optional<Integer> size){
        DataReturnList<CatOfItemDto> dataReturnList = new DataReturnList<>();
        Page<Cat_Item> itemPage = null;

        try {
            itemPage = catItemService.findItemByCatIddddd(id , page, size);
            dataReturnList.setPageNumber(itemPage.getTotalPages());
            dataReturnList.setData(itemPage.getContent().stream().map(catOfItemMapper::listcatitemTolistCatItemDto)
                                                                                        .collect(Collectors.toList()));
            dataReturnList.setMessage("Co " + dataReturnList.getPageNumber() + " trang item.");
        }catch (Exception e){
            dataReturnList.setSuccess("false");
            dataReturnList.setMessage("Khong the lay item !!!");
        }

        return dataReturnList;
    }


}
