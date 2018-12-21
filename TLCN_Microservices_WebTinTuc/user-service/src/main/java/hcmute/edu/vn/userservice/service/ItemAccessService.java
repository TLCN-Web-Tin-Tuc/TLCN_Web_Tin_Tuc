package hcmute.edu.vn.userservice.service;

import hcmute.edu.vn.userservice.model.ItemAccess;
import org.springframework.data.repository.CrudRepository;

public interface ItemAccessService {

    CrudRepository<ItemAccess,Long> getRepo();
    ItemAccess userLike(long itemid, String email);
    void userDisLike(long itemid, String email);
}
