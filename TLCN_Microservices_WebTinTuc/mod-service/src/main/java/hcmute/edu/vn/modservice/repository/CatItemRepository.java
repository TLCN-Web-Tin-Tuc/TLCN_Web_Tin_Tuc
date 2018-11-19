package hcmute.edu.vn.modservice.repository;

import hcmute.edu.vn.modservice.model.Cat_Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatItemRepository extends JpaRepository<Cat_Item, Long> {
    List<Cat_Item> findById_Cat_Id(Long id);
}
