package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Cat;
import hcmute.edu.vn.nuservice.model.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemService {

    CrudRepository<Items,Long> getRepo();
}
