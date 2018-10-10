package hcmute.edu.vn.dbservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

@Entity(name = "ne_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String rname;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

