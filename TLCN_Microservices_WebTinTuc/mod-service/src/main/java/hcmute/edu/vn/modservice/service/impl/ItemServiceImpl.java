package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.modservice.exception.Error404;
import hcmute.edu.vn.modservice.exception.NotFoundException;
import hcmute.edu.vn.modservice.model.Cat;
import hcmute.edu.vn.modservice.model.Cat_Item;
import hcmute.edu.vn.modservice.model.Cat_Item_Id;
import hcmute.edu.vn.modservice.model.Item;
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
    public CrudRepository<Item, Long> getRepo() {
        return itemRepository;
    }

    @Override
    public Item getItemById(long id) {
        Optional<Item> items = itemRepository.findById(id);
        if(!items.isPresent()){
            throw new Error404("Product Not Found");
        }
        return items.get();
    }

    @Override
    public List<Item> retrieveAllItems() {
        List<Item> items = itemRepository.findAll();
        if(items.isEmpty())
            throw new NotFoundException("item not found");
        return items;
    }

    @Override
    public Item retrieveItemsById(long id){
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found.");
        return itemOptional.get();
    }

    @Override
    public Item updateItemStatusApprove(long id, String userUpdate){
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found. Could not update status for this item");
        Item item = itemOptional.get();
        if(item.getStatus() == 1)
            item.setStatus(2);
        else item.setStatus(1);
        item.setUserUpdated(userUpdate);
        item.setDateUpdated(new Date());
        return itemRepository.save(item);
    }

    @Override
    public Item updateItemStatusCat(long id, String userUpdate) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found. Could not update status for this item");
        Item item = itemOptional.get();
        if(item.getStatus() == 0)
            item.setStatus(1);
        item.setUserUpdated(userUpdate);
        item.setDateUpdated(new Date());
        return itemRepository.save(item);
    }

    @Override
    public Item deleteItemStatus(long id, String userUpdate){
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            throw new NotFoundException("Item not found. Could not delete this item");
        Item item = itemOptional.get();
            item.setStatus(3);
        item.setUserUpdated(userUpdate);
        item.setDateUpdated(new Date());
        return itemRepository.save(item);
    }

    @Override
    public Item InsertItem(Item items, long catid) {
        Item itemsCreate = itemRepository.save(items);
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
       Item items = itemRepository.findById(itemid).get();
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
        Optional<Item> itemsOptional = itemRepository.findById(id);
        if(itemsOptional.isPresent()){
            Item items = itemsOptional.get();
            items.setStatus(3);
            itemRepository.save(items);
        }
    }
}
