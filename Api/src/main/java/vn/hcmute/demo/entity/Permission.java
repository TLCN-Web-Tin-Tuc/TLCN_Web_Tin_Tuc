package vn.hcmute.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "ne_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cd;

    private String name;

    private Long cat;

    private Boolean p_create;

    private Boolean p_update;

    private Boolean p_delete;

    private Boolean p_approve;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @OneToMany(mappedBy = "permission")
    private Set<Assign_Permission> assign_permissions;
}
