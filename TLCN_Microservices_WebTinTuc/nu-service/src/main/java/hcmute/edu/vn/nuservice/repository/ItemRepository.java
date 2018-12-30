package hcmute.edu.vn.nuservice.repository;

import hcmute.edu.vn.nuservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findById(long id);

    @Query(value = "SELECT p FROM ne_items p\n" +
            "WHERE p.status = 1 \n" +
            "ORDER BY p.dateUpdated DESC")
    List<Item> findAllItemsNew();

    @Query(value = "SELECT p FROM ne_items p\n" +
            "ORDER BY p.dateUpdated DESC")
    List<Item> findAllItemsNewDESC();

    @Query(value = "SELECT p FROM ne_items p\n" +
            "WHERE p.status = 1 \n" +
            "ORDER BY p.likes DESC")
    List<Item> findAllItemsNewDescLike();

//    @Query(value = "SELECT p FROM ne_items p, ne_cat_item q \n" +
//            "WHERE p.status = 1 AND p.id = q.id.item.id \n" +
//            "AND q.id.cat.id = :catId \n" +
//            "ORDER BY p.date_updated DESC")
//    List<Items> findAllItemByCatId(@Param("catId") long catId);
//

}
