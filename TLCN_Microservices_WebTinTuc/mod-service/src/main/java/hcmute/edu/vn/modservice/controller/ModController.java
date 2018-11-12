package hcmute.edu.vn.modservice.controller;

import hcmute.edu.vn.modservice.api.v1.data.DataReturnList;
import hcmute.edu.vn.modservice.api.v1.data.DataReturnOne;
import hcmute.edu.vn.modservice.model.Cat;
import hcmute.edu.vn.modservice.model.Items;
import hcmute.edu.vn.modservice.service.CatService;
import hcmute.edu.vn.modservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
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
        dataReturnList.setMessage("Get list categry success");
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

    @PostMapping("/cat/create")
    public DataReturnOne<Cat> CreateCategory(@RequestBody Cat cat){
        DataReturnOne<Cat> dataReturnOne = new DataReturnOne<>();
        Cat cat1 = catService.InsertCategory( cat);
        if(cat != null){
            dataReturnOne.setMessage("Insert Category Success");
            dataReturnOne.setData(cat1);
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setMessage("Insert Category Fail");
            dataReturnOne.setData(null);
        }
        return dataReturnOne;
    }

    @PutMapping("/cat/update")
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
        List<Items> items = itemService.getAllItemByCategory(id);

        DataReturnList<Items> dataReturnList = new DataReturnList<>();
        dataReturnList.setData(items);
        dataReturnList.setMessage("Get list item success");
        return dataReturnList;
    }

    @PostMapping("/items/create/{categoryid}")
    public DataReturnOne<Items> Create(@RequestBody Items items, @PathVariable("categoryid") long id){
        Cat categories = catService.retrieveCatById(id);
        Items items1 = itemService.InsertItem(items);
        Set<Cat> cats = new HashSet<>();
        cats.add(categories);
        items1.setCats(cats);
        itemService.getRepo().save(items1);
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        if(items1!=null){
            dataReturnOne.setData(items1);
            dataReturnOne.setMessage("Create item success");
        }else{
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Create item fail");
        }
        return dataReturnOne;
    }

//    @PutMapping("/items/update")
//    public DataReturnOne<Items> UpdateItems(@RequestBody Items items){
//        Items items1 = itemService.InsertItem(items);
//        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
//        if(items1!=null){
//            dataReturnOne.setData(items1);
//            dataReturnOne.setMessage("Update item success");
//        }else{
//            dataReturnOne.setSuccess("false");
//            dataReturnOne.setData(null);
//            dataReturnOne.setMessage("Update item fail");
//        }
//        return dataReturnOne;
//    }

    @DeleteMapping("/items/delete")
    public DataReturnOne<Items> DeleteItems(@RequestBody Items items){
        DataReturnOne<Items> dataReturnOne = new DataReturnOne<>();
        try {
            itemService.DeleteItem(items.getId());
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete item success");
        }catch (Exception e){
            dataReturnOne.setSuccess("false");
            dataReturnOne.setData(null);
            dataReturnOne.setMessage("Delete item fail" + e.getMessage());
        }
        return dataReturnOne;
    }
}
