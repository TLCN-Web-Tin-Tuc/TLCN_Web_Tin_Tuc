package hcmute.edu.vn.dbservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "ne_users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

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

    @OneToMany(mappedBy = "user_cm")
    private Set<Comments> comments;

    @OneToMany(mappedBy = "user_rcm")
    private Set<Response_Comments> response_comments;

    @OneToMany(mappedBy = "user_ia")
    private Set<Item_Access> item_accesses;

    @OneToMany(mappedBy = "user_ap")
    private Set<Assign_Permission> assign_permissions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ne_user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles;



}




