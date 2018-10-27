package hcmute.edu.vn.adminservice.service;


import hcmute.edu.vn.adminservice.model.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionService {
    CrudRepository<Permission,Long> getRepo();
}
