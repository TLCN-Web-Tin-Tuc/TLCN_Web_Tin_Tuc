package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
