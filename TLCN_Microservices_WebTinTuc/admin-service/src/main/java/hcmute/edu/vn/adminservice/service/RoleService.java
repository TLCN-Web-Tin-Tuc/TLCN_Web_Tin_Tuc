package hcmute.edu.vn.adminservice.service;

import hcmute.edu.vn.adminservice.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleService {

    CrudRepository<Role,Long> getRepo();
    Role retrieveRoleByRName(String rname);
    List<Role> retrieveAllRole();
}
