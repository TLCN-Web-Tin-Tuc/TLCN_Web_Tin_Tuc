package hcmute.edu.vn.modservice.repository;

import hcmute.edu.vn.modservice.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Items,Long> {
    Optional<Items> findById(long id);
    List<Items> findByCats(long id);
}
