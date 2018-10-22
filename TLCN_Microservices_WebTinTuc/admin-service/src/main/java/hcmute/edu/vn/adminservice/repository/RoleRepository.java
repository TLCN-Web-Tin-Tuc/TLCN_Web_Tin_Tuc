package hcmute.edu.vn.adminservice.repository;

import hcmute.edu.vn.adminservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRname(String Rname);
    Optional<Role> findById(long rid);
}
