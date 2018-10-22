package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.nuservice.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleService {

    CrudRepository<Role,Long> getRepo();
}
