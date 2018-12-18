package hcmute.edu.vn.userservice.api.v1.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String lastName;
    private String avatar;
}
