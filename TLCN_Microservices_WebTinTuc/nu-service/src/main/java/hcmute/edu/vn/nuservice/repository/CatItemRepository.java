package hcmute.edu.vn.nuservice.repository;

import hcmute.edu.vn.nuservice.model.Cat_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatItemRepository extends JpaRepository<Cat_Item,Long> {
    List<Cat_Item> findCat_ItemById_Item_Id(Long id);
}
