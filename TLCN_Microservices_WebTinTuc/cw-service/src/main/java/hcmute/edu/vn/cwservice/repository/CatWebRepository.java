package hcmute.edu.vn.cwservice.repository;

import hcmute.edu.vn.cwservice.entity.CatWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatWebRepository extends JpaRepository<CatWeb,Long> {
}
