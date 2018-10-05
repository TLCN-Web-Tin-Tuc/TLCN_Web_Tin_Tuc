package vn.hcmute.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ne_assign_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assign_Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Permission permission;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

}
