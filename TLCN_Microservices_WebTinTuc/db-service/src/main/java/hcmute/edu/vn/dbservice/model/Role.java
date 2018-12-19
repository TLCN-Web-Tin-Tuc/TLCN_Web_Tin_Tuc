package hcmute.edu.vn.dbservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "ne_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String rname;

    private Boolean p_create;

    private Boolean p_update;

    private Boolean p_delete;

    private Boolean p_approve;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

}

