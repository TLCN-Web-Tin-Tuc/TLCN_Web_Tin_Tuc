package hcmute.edu.vn.cwservice.repository;

import hcmute.edu.vn.cwservice.entity.CatItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatItemRepository extends JpaRepository<CatItem, Long> {
    List<CatItem> findByCIId_Cat_Id(Long id);

    Optional<CatItem> findByCIId_Cat_IdAndCIId_Item_Id(long catid, long itemid);
}
