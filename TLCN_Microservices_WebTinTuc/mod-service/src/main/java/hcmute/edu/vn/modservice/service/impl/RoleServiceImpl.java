package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.nuservice.model.Role;
import hcmute.edu.vn.nuservice.repository.RoleRepository;
import hcmute.edu.vn.nuservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public CrudRepository<Role, Long> getRepo() {
        return roleRepository;
    }
}
