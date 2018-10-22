package hcmute.edu.vn.adminservice.service;

import hcmute.edu.vn.adminservice.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleService {

    CrudRepository<Role,Long> getRepo();
}
