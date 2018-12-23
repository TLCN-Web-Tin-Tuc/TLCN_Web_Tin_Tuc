package hcmute.edu.vn.nuservice.repository;

import hcmute.edu.vn.nuservice.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat,Long> {
    List<Cat> findCatByCheckCat(int id);
}
