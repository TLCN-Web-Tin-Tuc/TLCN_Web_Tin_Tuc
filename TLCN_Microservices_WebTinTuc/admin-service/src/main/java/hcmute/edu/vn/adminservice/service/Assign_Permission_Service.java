package hcmute.edu.vn.adminservice.service;

import hcmute.edu.vn.adminservice.model.Assign_Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface Assign_Permission_Service {
    CrudRepository<Assign_Permission,Long> getRepo();
}
