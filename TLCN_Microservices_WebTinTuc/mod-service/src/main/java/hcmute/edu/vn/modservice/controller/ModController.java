package hcmute.edu.vn.modservice.controller;

import hcmute.edu.vn.modservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.modservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.modservice.model.Cat;
import hcmute.edu.vn.modservice.model.Cat_Item;
import hcmute.edu.vn.modservice.model.Items;
import hcmute.edu.vn.modservice.service.CatService;
import hcmute.edu.vn.modservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/mod/")
public class ModController {

    @Autowired
    CatService catService;

    @Autowired
    ItemService itemService;

    @GetMapping("/cat")
    public DataReturnList<Cat> Category(){

        List<Cat> categories = catService.retrieveAllCat();
        DataReturnList<Cat> dataReturnList = new DataReturnList<>();
        dataReturnList.setData(categories);
        dataReturnList.setSuccess("true");
        dataReturnList.setMessage("success");
        return dataReturnList;
    }

    @GetMapping("/cat/{categoryid}")
    public DataReturnOne<Cat> findCategory(@PathVariable("categoryid") long id){
        Cat categories = catService.retrieveCatById(id);
        DataReturnOne<Cat> dataReturnOne = new DataReturnOne<>();
        dataReturnOne.setData(categories);
        dataReturnOne.setMessage("Get list categry success");
        return dataReturnOne;
    }

    @PostMapping("/cat/createcat")
    public DataReturnOne<Cat> CreateCategory(@RequestBody Cat cat){
        DataReturnOne<Cat> dataReturnOne = new DataReturnOne<>();
        Cat cat1 = catService.getRepo().save(cat);
        if(cat != null){
            dataReturnOne.setMessage("Insert Category Success");
            dataReturnOne.setSuccess("true");
            dataReturnOne.setData(cat1);
        }else {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Insert Category Fail");
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @PutMapping("/cat/updatestatus/{id}/{userUpdate}")
    public DataReturnOne<Cat> UpdateStatusRole(@PathVariable long id, @PathVariable String userUpdate)
    {
        Cat cat1 = catService.retrieveCatById(id);
        DataReturnOne<Cat> dataReturnOne = new DataReturnOne<>();
        if(cat1 != null){
            if(cat1.getCheckCat() == 1)
            {
                cat1.setCheckCat(0);
            }
            else
            {
                cat1.setCheckCat(1);
            }
            cat1.setDateUpdated(new Date());
            cat1.setUserUpdated(userUpdate);
            dataReturnOne.setData(catService.getRepo().save(cat1));
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Set status Role Success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Set status Role Fail");
        }
        return dataReturnOne;
    }

    @PostMapping("/cat/update")
    public DataReturnOne<Cat> UpdateCategory(@RequestBody Cat cat){
        DataReturnOne<Cat> dataReturnOne = new DataReturnOne<>();
        Cat category1 = catService.UpdateCategory(cat);
        if(category1 != null){
            dataReturnOne.setMessage("Update Category Success");
            dataReturnOne.setData(category1);
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Update Category Fail");
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }
    @DeleteMapping("/cat/delete")
    public  DataReturnOne<Cat> DeleteCategory(@RequestBody Cat cat){
        DataReturnOne<Cat> dataReturnOne = new DataReturnOne<>();
        try{
            catService.DeleteCategory(cat.getId());
            dataReturnOne.setMessage("Delete Category Success");
            dataReturnOne.setData(null);
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage(e.getMessage());
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @GetMapping("/items/{categoryid}")
    public DataReturnList<Items> Items(@PathVariable("categoryid") long id){
        Cat cat  = catService.retrieveCatById(id);
        List<Items> items = new ArrayList<>();
       // items.addAll(cat.getItems());
        DataReturnList<Items> dataReturnList = new DataReturnList<>();
        dataReturnList.setData(items);
        dataReturnList.setMessage("Get list item success");
        return dataReturnList;
    }

    @GetMapping("/items/search")
    public DataReturnOne<Items> retrieveItemsById(@RequestParam(required = false) long id){
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        dataReturnOne.setSuccess("true");
        dataReturnOne.setMessage("success");
        dataReturnOne.setData(itemService.retrieveItemsById(id));
        return dataReturnOne;
    }

    @GetMapping("/items")
    public DataReturnList<Items> retrieveAllItems(){
        DataReturnList<Items> dataReturnList = new DataReturnList<>();
        List<Items> items = new ArrayList<Items>();
        items = itemService.retrieveAllItems();
        dataReturnList.setData(items);
        dataReturnList.setMessage("Get list item success");
        return dataReturnList;
    }

    @PostMapping("/items/create")
    public DataReturnOne<Items> CreateItem(@RequestBody Items items ){
        items.setDownload((long) 0);
        items.setComment((long) 0);
        items.setLikes((long) 0);
        items.setViews((long) 0);
        Items items1 = itemService.getRepo().save(items);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(items1 != null){
            dataReturnOne.setData(items1);
            dataReturnOne.setSuccess("true");
            dataReturnOne.setMessage("Create item success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Create item fail");
        }
        return dataReturnOne;
    }

    @PostMapping("/items/addCatOnItem/{itemid}/{categoryid}")
    public DataReturnOne<Cat_Item> addCatOnItem(@PathVariable("itemid") long itemid, @PathVariable("categoryid") long categoryid){
        Cat_Item cat_item = itemService.addCatOnItem(itemid, categoryid);
        DataReturnOne<Cat_Item> dataReturnOne = new DataReturnOne<>();
        if(cat_item!=null){
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update cat on item success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update cat on item fail");
        }
        return dataReturnOne;
    }

    @PostMapping("/items/deleteCatOnItem/{itemid}/{categoryid}")
    public DataReturnOne<Items> deleteCatOnItem(@PathVariable("itemid") long itemid, @PathVariable("categoryid") long categoryid){
        boolean flag = itemService.removeCatOnItem(itemid, categoryid);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(flag==true){
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete cat on item success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete cat on item fail");
        }
        return dataReturnOne;
    }

    @GetMapping("/items/update/{id}/{userUpdate}")
    public DataReturnOne<Items> UpdateItems(@PathVariable long id, @PathVariable String userUpdate){
        Items items1 = itemService.updateItemStatus(id, userUpdate);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(items1!=null){
            dataReturnOne.setData(items1);
            dataReturnOne.setMessage("Update item success");
            dataReturnOne.setSuccess("true");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update item fail");
        }
        return dataReturnOne;
    }

    @GetMapping("/items/delete/{id}/{userUpdate}")
    public DataReturnOne<Items> DeleteItems(@RequestBody Items items){
        Items items1 = itemService.updateItemStatus(id, userUpdate);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(items1 != null){
            dataReturnOne.setData(items1);
            dataReturnOne.setMessage("Update item success");
            dataReturnOne.setSuccess("true");
        }else
        {
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Update item fail");
        }
        return dataReturnOne;
    }
}
