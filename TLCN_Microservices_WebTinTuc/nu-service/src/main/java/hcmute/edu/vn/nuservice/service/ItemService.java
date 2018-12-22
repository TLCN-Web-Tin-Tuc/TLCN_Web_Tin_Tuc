package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Items;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemService {

    CrudRepository<Items,Long> getRepo();
    Items retrieveItemsById(long id);
    List<Items> retrieveItemsDescDay();
    List<Items> retrieveItemsDescLike();
}
