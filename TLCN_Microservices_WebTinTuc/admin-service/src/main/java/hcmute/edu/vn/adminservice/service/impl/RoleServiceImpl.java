package hcmute.edu.vn.adminservice.service.impl;

import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.Role;
import hcmute.edu.vn.adminservice.repository.RoleRepository;
import hcmute.edu.vn.adminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public CrudRepository<Role, Long> getRepo() {
        return roleRepository;
    }

    @Override
    public Role retrieveRoleByRName(String rname) {
        Optional<Role> role = roleRepository.findByRname(rname);
        if(!role.isPresent()){
            throw new NotFoundException("Không tìm thấy quyền");
        }
        return role.get();
    }

    @Override
    public List<Role> retrieveAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role retrieveRoleByRId(long rid) {
        Optional<Role> role = roleRepository.findById(rid);
        return role.get();
    }
}
