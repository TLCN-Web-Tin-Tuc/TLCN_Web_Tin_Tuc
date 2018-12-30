package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.modservice.model.Cat_Item;
import hcmute.edu.vn.modservice.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemService {
    CrudRepository<Item,Long> getRepo();
    Item getItemById(long id);
    List<Item> retrieveAllItems();
    Item retrieveItemsById(long id);
    Item updateItemStatusApprove(long id, String userUpdate);
    Item updateItemStatusCat(long id, String userUpdate);
    Item deleteItemStatus(long id, String userUpdate);
    Item InsertItem(Item items, long catid);
    Cat_Item addCatOnItem(long itemid, long catid);
    boolean removeCatOnItem(long itemid,long catid);
    void DeleteItem(long id);
}
