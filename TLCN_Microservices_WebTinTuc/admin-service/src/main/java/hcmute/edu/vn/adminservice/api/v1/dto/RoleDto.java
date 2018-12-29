package hcmute.edu.vn.adminservice.api.v1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDto {
    private long id;

    private String rname;

    private Boolean p_create;

    private Boolean p_update;

    private Boolean p_delete;

    private Boolean p_approve;

    private Boolean p_admin;

    private int status;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

}
