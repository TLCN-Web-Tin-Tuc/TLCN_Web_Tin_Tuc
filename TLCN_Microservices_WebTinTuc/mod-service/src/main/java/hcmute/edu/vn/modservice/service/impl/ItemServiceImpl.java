package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.modservice.exception.Error404;
import hcmute.edu.vn.modservice.exception.NotFoundException;
import hcmute.edu.vn.modservice.model.Cat;
import hcmute.edu.vn.modservice.model.Cat_Item;
import hcmute.edu.vn.modservice.model.Cat_Item_Id;
import hcmute.edu.vn.modservice.model.Items;
import hcmute.edu.vn.modservice.repository.CatItemRepository;
import hcmute.edu.vn.modservice.repository.CatRepository;
import hcmute.edu.vn.modservice.repository.ItemRepository;
import hcmute.edu.vn.modservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CatRepository catRepository;

    @Autowired
    CatItemRepository catItemRepository;

    @Override
    public CrudRepository<Items, Long> getRepo() {
        return itemRepository;
    }

    @Override
    public Items getItemById(long id) {
        Optional<Items> items = itemRepository.findById(id);
        if(!items.isPresent()){
            throw new Error404("Product Not Found");
        }
        return items.get();
    }

    @Override
    public List<Items> retrieveAllItems() {
        List<Items> items = itemRepository.findAll();
        if(items.isEmpty())
            throw new NotFoundException("item not found");
        return items;
    }

    @Override
    public Items retrieveItemsById(long id){
        Optional<Items> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found.");
        return itemOptional.get();
    }

    @Override
    public Items updateItemStatus(long id, String userUpdate){
        Optional<Items> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found. Could not update status for this item");
        Items item = itemOptional.get();
        if(item.getStatus() == 0)
            item.setStatus(1);
        else item.setStatus(0);
        item.setUserUpdated(userUpdate);
        item.setDateUpdated(new Date());
        return itemRepository.save(item);
    }

    @Override
    public Items InsertItem(Items items, long catid) {
        Items itemsCreate = itemRepository.save(items);
        Cat cat = catRepository.findById(catid).get();
        Cat_Item cat_item = new Cat_Item();
        Cat_Item_Id cat_item_id = new Cat_Item_Id();
        cat_item_id.setCat(cat);
        cat_item_id.setItem(itemsCreate);
        cat_item.setId(cat_item_id);
        catItemRepository.save(cat_item);
        return itemsCreate;
    }

    @Override
    public Cat_Item addCatOnItem(long itemid, long catid) {
       Items items = itemRepository.findById(itemid).get();
       Cat cat = catRepository.findById(catid).get();
        Cat_Item cat_item = new Cat_Item();
        Cat_Item_Id cat_item_id = new Cat_Item_Id();
        cat_item_id.setCat(cat);
        cat_item_id.setItem(items);
        cat_item.setId(cat_item_id);
        return  catItemRepository.save(cat_item);
    }

    @Override
    public boolean removeCatOnItem(long itemid, long catid) {
        Cat_Item cat_item = catItemRepository.findById_Cat_IdAndId_Item_Id(catid,itemid).get();
        catItemRepository.delete(cat_item);
        return true;
    }

    @Override
    public void DeleteItem(long id) {
        Optional<Items> itemsOptional = itemRepository.findById(id);
        if(itemsOptional.isPresent()){
            Items items = itemsOptional.get();
            items.setStatus(2);
            itemRepository.save(items);
        }
    }
}
