package hcmute.edu.vn.userservice.service;

import hcmute.edu.vn.userservice.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemService {

    CrudRepository<Item,Long> getRepo();
    Item itemDetail(long id);
    Item likeItem(long id);
    Item unlikeItem(long id);

}
