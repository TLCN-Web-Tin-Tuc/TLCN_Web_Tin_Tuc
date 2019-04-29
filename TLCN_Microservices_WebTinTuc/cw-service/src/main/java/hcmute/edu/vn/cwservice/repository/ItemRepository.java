package hcmute.edu.vn.cwservice.repository;

import hcmute.edu.vn.cwservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> findById(long id);
    Optional<Item> findByTitle(String title);
    //List<Items> findByCats(Cat cat);
}
