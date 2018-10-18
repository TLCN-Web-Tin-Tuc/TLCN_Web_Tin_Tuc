package vn.hcmute.demo.entity;

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
    private String email;
    private String password;
    private int status;

    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    @OneToMany(mappedBy = "usercm")
    private Set<Comments> comments;

    @OneToMany(mappedBy = "userrcm")
    private Set<Response_Comments> response_comments;

    @OneToMany(mappedBy = "useria")
    private Set<Item_Access> item_accesses;

    @OneToMany(mappedBy = "userap")
    private Set<Assign_Permission> assign_permissions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles;
}


