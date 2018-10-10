package hcmute.edu.vn.dbservice.model;

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

    private String checkCat;

    private String fileName;

    private String fileExtension;

    private byte[] image;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @ManyToMany
    @JoinTable(
            name = "ne_cat_item",
            joinColumns = @JoinColumn(name = "catId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "id")
    )
    private Set<Items> items;
}