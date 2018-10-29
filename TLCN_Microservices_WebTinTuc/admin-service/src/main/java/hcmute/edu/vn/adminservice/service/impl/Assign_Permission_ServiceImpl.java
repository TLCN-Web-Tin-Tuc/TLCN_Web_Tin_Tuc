package hcmute.edu.vn.adminservice.service.impl;

import hcmute.edu.vn.adminservice.model.Assign_Permission;
import hcmute.edu.vn.adminservice.repository.Assign_Permission_Repository;
import hcmute.edu.vn.adminservice.service.Assign_Permission_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Assign_Permission_ServiceImpl implements Assign_Permission_Service {

    @Autowired
    Assign_Permission_Repository assign_permission_repository;

    @Override
    public CrudRepository<Assign_Permission, Long> getRepo() {
        return assign_permission_repository;
    }

    @Override
    public List<Assign_Permission> retrieveAllAssignPermission() {
        return assign_permission_repository.findAll();
    }

    @Override
    public Assign_Permission updateAssignPermission(Assign_Permission assign_permission) {
        Optional<Assign_Permission> assign_permissionOptional = assign_permission_repository.findById(assign_permission.getId());
        Assign_Permission assignPermissionUpdate = assign_permissionOptional.get();
        assignPermissionUpdate.setPermission(assign_permission.getPermission());
        assignPermissionUpdate.setUser_ap(assign_permission.getUser_ap());
        return assign_permission_repository.save(assignPermissionUpdate);
    }

    @Override
    public Assign_Permission createAssignPermission(Assign_Permission assign_permission) {
        return assign_permission_repository.save(assign_permission);
    }

    @Override
    public void deleteAssignPermission(Assign_Permission assign_permission) {
        assign_permission_repository.delete(assign_permission);
    }


}
