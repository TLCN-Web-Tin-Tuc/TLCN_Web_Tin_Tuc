package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Cat_Item;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CatItemService {
    CrudRepository<Cat_Item,Long> getRepo();
    List<Cat_Item> retrieveAllCatItem(Long id);
    List<Cat_Item> retrieveAllByCatId(Long id);
    List<Cat_Item> retrieveAllItemDescDay();
    Page<Cat_Item> findItemByCatIddddd(Optional<Long> catId, Optional<Integer> page, Optional<Integer> size);
}
