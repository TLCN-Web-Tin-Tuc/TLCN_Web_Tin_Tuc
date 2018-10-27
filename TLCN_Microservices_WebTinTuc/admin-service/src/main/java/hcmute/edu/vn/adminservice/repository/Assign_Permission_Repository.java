package hcmute.edu.vn.adminservice.repository;


import hcmute.edu.vn.adminservice.model.Assign_Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Assign_Permission_Repository extends JpaRepository<Assign_Permission,Long> {
}
