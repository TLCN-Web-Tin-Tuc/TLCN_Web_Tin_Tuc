package hcmute.edu.vn.adminservice.api.v1.dto;

import hcmute.edu.vn.adminservice.model.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDto {
    private String email;
    private String password;
    private Long id;
    private int status;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private int sex;
    private String avatar;
    private String address;
    private String phone;
    private Date dateCreated;
    private String userCreated;
    private Date dateUpdated;
    private String userUpdated;
    private Set<Role> roles;
}
