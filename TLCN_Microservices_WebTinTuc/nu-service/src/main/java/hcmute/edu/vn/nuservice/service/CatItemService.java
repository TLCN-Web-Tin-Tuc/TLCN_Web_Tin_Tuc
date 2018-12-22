package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Cat_Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatItemService {
    CrudRepository<Cat_Item,Long> getRepo();
    List<Cat_Item> retrieveAllCatItem(Long id);
}
