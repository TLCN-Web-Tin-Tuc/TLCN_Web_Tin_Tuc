package hcmute.edu.vn.nuservice.service.impl;

import hcmute.edu.vn.nuservice.repository.RoleRepository;
import hcmute.edu.vn.nuservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleRepository getRepo() {
        return roleRepository;
    }
}
