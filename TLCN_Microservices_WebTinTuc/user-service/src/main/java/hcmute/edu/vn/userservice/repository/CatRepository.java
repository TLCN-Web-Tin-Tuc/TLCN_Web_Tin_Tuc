package hcmute.edu.vn.userservice.repository;

import hcmute.edu.vn.nuservice.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat,Long> {
}
