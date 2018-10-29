package hcmute.edu.vn.adminservice.service;


import hcmute.edu.vn.adminservice.model.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionService {
    CrudRepository<Permission,Long> getRepo();
    List<Permission> retrieveAllPermission();
    Permission updatePermission(Permission permission);
    Permission createPermission(Permission permission);
    Permission deletePermission(Permission permission);
}
