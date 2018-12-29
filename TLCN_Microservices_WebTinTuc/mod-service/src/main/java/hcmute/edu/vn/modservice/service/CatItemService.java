package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.modservice.model.Cat_Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatItemService {
    CrudRepository<Cat_Item,Long> getRepo();
    List<Cat_Item> retrieveAllCatItem(Long id);
    List<Cat_Item> retrieveAllByCatId(Long id);
}
