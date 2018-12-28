package hcmute.edu.vn.adminservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ne_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Cat catRole;

}

