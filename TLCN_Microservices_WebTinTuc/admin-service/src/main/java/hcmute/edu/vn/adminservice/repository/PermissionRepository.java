package hcmute.edu.vn.adminservice.repository;

import hcmute.edu.vn.adminservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Optional<Permission> findById(long id);
}
