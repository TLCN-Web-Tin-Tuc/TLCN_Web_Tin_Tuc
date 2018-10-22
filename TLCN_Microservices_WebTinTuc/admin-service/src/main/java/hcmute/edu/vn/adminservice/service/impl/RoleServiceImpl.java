package hcmute.edu.vn.adminservice.service.impl;

import hcmute.edu.vn.adminservice.model.Role;
import hcmute.edu.vn.adminservice.repository.RoleRepository;
import hcmute.edu.vn.adminservice.service.RoleService;
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
