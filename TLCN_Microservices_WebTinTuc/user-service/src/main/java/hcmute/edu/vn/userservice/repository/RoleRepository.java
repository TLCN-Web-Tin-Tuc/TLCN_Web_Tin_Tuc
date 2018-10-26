package hcmute.edu.vn.userservice.repository;

import hcmute.edu.vn.nuservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRname(String Rname);
}
