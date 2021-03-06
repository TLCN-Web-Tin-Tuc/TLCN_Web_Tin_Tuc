package hcmute.edu.vn.nuservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity(name = "ne_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileExtension;

    @Size(max=2000000)
    private String image;

    private String title;

    private int status;

    @Size(max=2000000)
    private String shortDesc;

    @Size(max=2000000)
    private String fullDesc;

    private String author;

    private Long views;

    private Long likes;

    private Long download;

    private Long comment;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @OneToMany(mappedBy = "id.item")
    private Set<Cat_Item> cat_items;

    @OneToMany(mappedBy = "item")
    private Set<ItemAccess> itemAccesses;

    @OneToMany(mappedBy = "itemCm")
    private Set<Comment> comments;

}