package hcmute.edu.vn.userservice.repository;

import hcmute.edu.vn.userservice.model.Item_Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ItemAccessRepository extends JpaRepository<Item_Access, Long> {
    @Query(value = "SELECT i FROM ne_item_access i WHERE i.item_ac_id = item_ac_id and i.user_ia_id = user_ia_id")
    Optional<Item_Access> findItem_AccessByUserandItem(@Param("item_ac_id") long item_ac_id, @Param("user_ia_id") long user_ia_id);

    //    Optional<Item_Access> findByItem_acAndUser_ia(Items items, User user);
}
