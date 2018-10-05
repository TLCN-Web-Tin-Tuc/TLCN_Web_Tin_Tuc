package vn.hcmute.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "ne_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;


    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "account")
    private Set<Comments> comments;

    @OneToMany(mappedBy = "account")
    private Set<Response_Comments> response_comments;

    @OneToMany(mappedBy = "account")
    private Set<Item_Access> item_accesses;

    @OneToMany(mappedBy = "account")
    private Set<Assign_Permission> assign_permissions;

}