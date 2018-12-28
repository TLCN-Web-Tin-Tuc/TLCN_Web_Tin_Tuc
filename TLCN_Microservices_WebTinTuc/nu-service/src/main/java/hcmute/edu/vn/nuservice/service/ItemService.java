package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemService {

    CrudRepository<Item,Long> getRepo();
    Item retrieveItemsById(long id);
    List<Item> retrieveItemsDescDay();
    List<Item> retrieveItemsDescLike();
}
