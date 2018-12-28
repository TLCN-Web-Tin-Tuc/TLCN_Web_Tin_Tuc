package hcmute.edu.vn.modservice.repository;

import hcmute.edu.vn.modservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findById(long id);
    //List<Items> findByCats(Cat cat);
}
