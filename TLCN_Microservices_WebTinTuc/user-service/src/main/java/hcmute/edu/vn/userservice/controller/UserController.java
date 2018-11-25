package hcmute.edu.vn.userservice.controller;

import hcmute.edu.vn.userservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.userservice.model.Item_Access;
import hcmute.edu.vn.userservice.model.Items;
import hcmute.edu.vn.userservice.service.ItemAccessService;
import hcmute.edu.vn.userservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {

    @Autowired
    ItemAccessService itemAccessService;

    @Autowired
    ItemService itemService;

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

    @PostMapping("/items/dislike/{itemid}/{email}")
    public DataReturnOne<Items> DisLikeItem(@PathVariable("itemid") long itemid,@PathVariable("email") String email){

        Items items1 = itemService.unlikeItem(itemid);
        itemAccessService.userDisLike(itemid,email);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(items1!=null ){
            dataReturnOne.setData(items1);
            dataReturnOne.setMessage("DisLike Item success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("DisLike item fail");
        }
        return dataReturnOne;
    }

}
