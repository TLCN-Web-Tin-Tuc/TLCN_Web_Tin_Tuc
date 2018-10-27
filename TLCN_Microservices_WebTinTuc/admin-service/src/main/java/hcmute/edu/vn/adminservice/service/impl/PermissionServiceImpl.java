package hcmute.edu.vn.adminservice.service.impl;

import hcmute.edu.vn.adminservice.model.Permission;
import hcmute.edu.vn.adminservice.repository.PermissionRepository;
import hcmute.edu.vn.adminservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public CrudRepository<Permission, Long> getRepo() {
        return permissionRepository;
    }
}
