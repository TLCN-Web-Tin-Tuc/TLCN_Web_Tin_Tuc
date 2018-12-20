package hcmute.edu.vn.userservice.repository;

import hcmute.edu.vn.userservice.model.Item_Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ItemAccessRepository extends JpaRepository<Item_Access, Long> {
    @Query("SELECT ")

    //    Optional<Item_Access> findByItem_acAndUser_ia(Items items, User user);
}
