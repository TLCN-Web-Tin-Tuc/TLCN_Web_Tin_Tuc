package hcmute.edu.vn.adminservice.repository;


import hcmute.edu.vn.adminservice.model.Assign_Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Assign_Permission_Repository extends JpaRepository<Assign_Permission,Long> {
    Optional<Assign_Permission> findById(long id);
}
