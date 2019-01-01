package hcmute.edu.vn.adminservice.bootstrap;

import hcmute.edu.vn.adminservice.exception.NotFoundException;
import hcmute.edu.vn.adminservice.model.Role;
import hcmute.edu.vn.adminservice.model.User;
import hcmute.edu.vn.adminservice.service.RoleService;
import hcmute.edu.vn.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
        Role roleUser = createRoleIfNotFound("ROLE_USER");
        User userAdmin = createUserIfNotFound("admin");
    }

    @Transactional
    Role createRoleIfNotFound(String roleName) {
        Role role;
        try{
            role = roleService.retrieveRoleByRName(roleName);
        } catch (NotFoundException ex) {
            Role newRole = new Role();
            if(roleName == "ROLE_ADMIN"){
                newRole.setP_admin(true);
                newRole.setP_update(false);
                newRole.setP_delete(false);
                newRole.setP_approve(false);
                newRole.setCatId(0);
                newRole.setStatus(1);
                newRole.setUserCreated("Admin");
                newRole.setDateCreated(new Date());
                newRole.setP_create(false);
                newRole.setRname("ROLE_ADMIN");
            }

            if(roleName == "ROLE_USER"){
                newRole.setP_admin(false);
                newRole.setP_update(false);
                newRole.setP_delete(false);
                newRole.setP_approve(false);
                newRole.setStatus(1);
                newRole.setCatId(0);
                newRole.setUserCreated("Admin");
                newRole.setDateCreated(new Date());
                newRole.setP_create(false);
                newRole.setRname("ROLE_USER");
            }
            return roleService.getRepo().save(newRole);
        }
        return role;

    }

    @Transactional
    User createUserIfNotFound(String email) {
        User user = new User() ;

        try {
            user = userService.retrieveUserByEmail(email);
        } catch (NotFoundException ex) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword("admin");
            newUser.setDateCreated(new Date());
            newUser.setUserCreated("Admin");
            newUser.setStatus(1);
            newUser.setLastName("Admin");
            newUser.setFirstName("Admin");
            newUser.setSex(1);

            User userCreate = userService.getRepo().save(newUser);
            Role role = roleService.retrieveRoleByRName("ROLE_ADMIN");
            Set<Role> rolesUser = new HashSet<>() ;
            rolesUser.add(role);
            userCreate.setRoles(rolesUser);
            return userService.getRepo().save(userCreate);
        }
        return user;
    }

}
