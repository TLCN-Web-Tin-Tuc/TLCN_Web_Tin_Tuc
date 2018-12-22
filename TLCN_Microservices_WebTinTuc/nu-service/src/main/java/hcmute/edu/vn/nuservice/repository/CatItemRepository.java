package hcmute.edu.vn.nuservice.repository;

import hcmute.edu.vn.nuservice.model.Cat_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatItemRepository extends JpaRepository<Cat_Item,Long> {
    List<Cat_Item> findCat_ItemById_Item_Id(Long id);

    @Query("SELECT p FROM ne_cat_item p  " +
            "WHERE p.id.cat.id = :catid AND p.id.item.status = 1 " +
            "ORDER BY p.id.item.dateUpdated DESC")
    List<Cat_Item> findItemByCatId(@Param("catid") Long catid);

    @Query("SELECT p FROM ne_cat_item p  " +
            "WHERE  p.id.item.status = 1 " +
            "ORDER BY p.id.item.dateUpdated DESC")
    List<Cat_Item> findItemDescDay();
}
