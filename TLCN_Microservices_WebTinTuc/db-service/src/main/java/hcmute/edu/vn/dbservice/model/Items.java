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

    private String fileName;

    private String fileExtension;

    private byte[] image;

    private String title;

    private int status;

    private String shortDesc;

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

    @OneToMany(mappedBy = "item_ac")
    private Set<Item_Access> item_accesses;

    @OneToMany(mappedBy = "item")
    private Set<Attach_File> attach_files;
}