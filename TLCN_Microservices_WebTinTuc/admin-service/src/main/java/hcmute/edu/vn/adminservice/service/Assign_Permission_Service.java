package hcmute.edu.vn.adminservice.service;

import hcmute.edu.vn.adminservice.model.Assign_Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Assign_Permission_Service {
    CrudRepository<Assign_Permission,Long> getRepo();
    List<Assign_Permission> retrieveAllAssignPermission();
    Assign_Permission updateAssignPermission(Assign_Permission assign_permission);
    Assign_Permission createAssignPermission(Assign_Permission assign_permission);
    void deleteAssignPermission(Assign_Permission assign_permission);
}
