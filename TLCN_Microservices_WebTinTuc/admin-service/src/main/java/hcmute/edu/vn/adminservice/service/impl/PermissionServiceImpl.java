package hcmute.edu.vn.adminservice.service.impl;

import hcmute.edu.vn.adminservice.model.Permission;
import hcmute.edu.vn.adminservice.repository.PermissionRepository;
import hcmute.edu.vn.adminservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public CrudRepository<Permission, Long> getRepo() {
        return permissionRepository;
    }

    @Override
    public List<Permission> retrieveAllPermission() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission updatePermission(Permission permission) {
        Optional<Permission> permission1 = permissionRepository.findById(permission.getId());
        Permission permissionUpdate = permission1.get();
        permissionUpdate.setName(permission.getName());
        permissionUpdate.setAssign_permissions(permission.getAssign_permissions());
        permissionUpdate.setCat(permission.getCat());
        permissionUpdate.setCd(permission.getCd());
        permissionUpdate.setP_approve(permission.getP_approve());
        permissionUpdate.setP_create(permission.getP_create());
        permissionUpdate.setP_delete(permission.getP_delete());
        permissionUpdate.setP_update(permission.getP_update());
        return permissionRepository.save(permissionUpdate);
    }

    @Override
    public Permission createPermission(Permission permission) {
        permission.setStatus(true);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission deletePermission(Permission permission) {
        Optional<Permission> permissionOptional  = permissionRepository.findById(permission.getId());
        Permission permissionUpdate = permissionOptional.get();
        permissionUpdate.setStatus(false);
        return permissionUpdate;
    }
}
