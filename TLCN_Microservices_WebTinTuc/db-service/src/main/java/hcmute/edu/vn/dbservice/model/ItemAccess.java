package hcmute.edu.vn.dbservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ne_item_access")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Long action;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;
}
