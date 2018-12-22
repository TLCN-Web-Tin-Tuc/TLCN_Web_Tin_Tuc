package hcmute.edu.vn.nuservice.repository;

import hcmute.edu.vn.nuservice.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Items,Long> {
    Optional<Items> findById(long id);
}
