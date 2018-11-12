package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.modservice.model.Items;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemService {
    CrudRepository<Items,Long> getRepo();
    Items getItemById(long id);
    List<Items> getAllItem();
    List<Items> getAllItemByCategory(long id);
    Items InsertItem(Items items);
    void DeleteItem(long id);
}
