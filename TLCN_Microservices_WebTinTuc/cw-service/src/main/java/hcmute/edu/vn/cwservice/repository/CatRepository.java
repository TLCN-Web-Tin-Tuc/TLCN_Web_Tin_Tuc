package hcmute.edu.vn.cwservice.repository;

import hcmute.edu.vn.cwservice.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository extends JpaRepository<Cat,Long> {
    Optional<Cat> findById(long id);
    Optional<Cat> findByName(String name);
//    List<Cat> findCatByParent_id(long id);
    //List<Cat> findAllByParent_idIsNull();

}
