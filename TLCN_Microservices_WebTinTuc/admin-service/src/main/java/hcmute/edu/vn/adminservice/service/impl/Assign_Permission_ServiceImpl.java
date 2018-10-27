package hcmute.edu.vn.adminservice.service.impl;

import hcmute.edu.vn.adminservice.model.Assign_Permission;
import hcmute.edu.vn.adminservice.repository.Assign_Permission_Repository;
import hcmute.edu.vn.adminservice.service.Assign_Permission_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class Assign_Permission_ServiceImpl implements Assign_Permission_Service {

    @Autowired
    Assign_Permission_Repository assign_permission_repository;

    @Override
    public CrudRepository<Assign_Permission, Long> getRepo() {
        return assign_permission_repository;
    }
}
