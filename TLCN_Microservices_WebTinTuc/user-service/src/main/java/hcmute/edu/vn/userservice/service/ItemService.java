package hcmute.edu.vn.userservice.service;

import hcmute.edu.vn.userservice.model.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemService {

    CrudRepository<Items,Long> getRepo();
}
