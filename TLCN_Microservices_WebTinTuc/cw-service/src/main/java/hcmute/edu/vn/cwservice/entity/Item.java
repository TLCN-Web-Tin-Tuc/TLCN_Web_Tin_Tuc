package hcmute.edu.vn.cwservice.entity;

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

    @Size(max=2000000)
    private String decription;

    private String author;

    private Long views;

    private Long likes;

    private Long download;

    private Long comment;

    private String linkOrigin;

    private String originName;

    private Date dateUpdated;

    @OneToMany(mappedBy = "cIId.item")
    private Set<CatItem> catItems;









}