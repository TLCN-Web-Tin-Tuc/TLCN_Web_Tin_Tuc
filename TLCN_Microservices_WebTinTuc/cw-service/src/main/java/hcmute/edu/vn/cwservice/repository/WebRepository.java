package hcmute.edu.vn.cwservice.repository;

import hcmute.edu.vn.cwservice.entity.Web;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebRepository extends JpaRepository<Web, Long> {
    Optional<Web> findWebByTitle(String webtitle);
}
