package hcmute.edu.vn.userservice.service;

import hcmute.edu.vn.userservice.model.Item_Access;
import org.springframework.data.repository.CrudRepository;

public interface ItemAccessService {

    CrudRepository<Item_Access,Long> getRepo();
    Item_Access userLike(long itemid, String email);
    void userDisLike(long itemid, String email);
}
