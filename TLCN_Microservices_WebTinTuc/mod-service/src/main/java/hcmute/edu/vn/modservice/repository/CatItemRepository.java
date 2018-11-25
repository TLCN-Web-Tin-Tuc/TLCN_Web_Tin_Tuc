package hcmute.edu.vn.modservice.repository;

import hcmute.edu.vn.modservice.model.Cat_Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatItemRepository extends JpaRepository<Cat_Item, Long> {
    List<Cat_Item> findById_Cat_Id(Long id);
    Optional<Cat_Item> findById_Cat_IdAndId_Item_Id(long catid, long itemid);
}
