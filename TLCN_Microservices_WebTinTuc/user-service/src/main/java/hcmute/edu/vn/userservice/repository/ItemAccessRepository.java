package hcmute.edu.vn.userservice.repository;

import hcmute.edu.vn.userservice.model.Item_Access;
import hcmute.edu.vn.userservice.model.Items;
import hcmute.edu.vn.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemAccessRepository extends JpaRepository<Item_Access, Long> {
    Optional<Item_Access> findByItem_acAndUser_ia(Items items, User user);
}
