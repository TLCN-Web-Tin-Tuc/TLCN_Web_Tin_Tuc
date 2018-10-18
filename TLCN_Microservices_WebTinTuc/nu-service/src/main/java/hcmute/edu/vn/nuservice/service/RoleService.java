package hcmute.edu.vn.nuservice.service;

import hcmute.edu.vn.nuservice.model.Role;
import hcmute.edu.vn.nuservice.repository.RoleRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleService {

    CrudRepository<Role,Long> getRepo();
}
