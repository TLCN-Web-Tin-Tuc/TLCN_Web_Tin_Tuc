package hcmute.edu.vn.userservice.service;

import hcmute.edu.vn.nuservice.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleService {

    CrudRepository<Role,Long> getRepo();
}
