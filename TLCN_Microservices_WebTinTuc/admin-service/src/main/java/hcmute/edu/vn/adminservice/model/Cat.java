package hcmute.edu.vn.adminservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "ne_cats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long parent_id;

    private int checkCat;

    private String fileName;

    private String fileExtension;

    private byte[] image;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @OneToMany(mappedBy = "id.cat")
    private Set<Cat_Item> cat_items;

    @OneToMany(mappedBy = "cat")
    private Set<Permission> permissions;
}