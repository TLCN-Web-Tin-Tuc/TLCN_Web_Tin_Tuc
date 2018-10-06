package vn.hcmute.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hcmute.demo.entity.Permission;

public interface PermissionRepository  extends JpaRepository<Permission, Long> {
}
