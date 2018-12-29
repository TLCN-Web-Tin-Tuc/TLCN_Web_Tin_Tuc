package hcmute.edu.vn.adminservice.repository;

import hcmute.edu.vn.adminservice.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat,Long> {
    List<Cat> findCatByCheckCat(int id);
    Optional<Cat> findById(long catId);
}
