package hcmute.edu.vn.dbservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "ne_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long cat;

    private Long parent_id;

    private String fileName;

    private String fileExtension;

    private byte[] image;

    private String title;

    private String ShortDesc;

    private String FullDesc;

    private String Author;

    private Long Views;

    private Long Likes;

    private Long Download;

    private Long Comment;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @ManyToMany(mappedBy = "items")
    private Set<Cat> cats;

    @OneToMany(mappedBy = "item_ac")
    private Set<Item_Access> item_accesses;

    @OneToMany(mappedBy = "item")
    private Set<Attach_File> attach_files;
}