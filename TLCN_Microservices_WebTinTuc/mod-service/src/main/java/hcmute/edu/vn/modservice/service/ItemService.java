package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.modservice.model.Cat_Item;
import hcmute.edu.vn.modservice.model.Items;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemService {
    CrudRepository<Items,Long> getRepo();
    Items getItemById(long id);
    List<Items> retrieveAllItems();
    Items retrieveItemsById(long id);
    Items InsertItem(Items items,long catid);
    Cat_Item addCatOnItem(long itemid, long catid);
    boolean removeCatOnItem(long itemid,long catid);
    void DeleteItem(long id);
}
