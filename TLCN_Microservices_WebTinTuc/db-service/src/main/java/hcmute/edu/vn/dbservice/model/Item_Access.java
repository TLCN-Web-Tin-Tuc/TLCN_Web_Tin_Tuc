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
public class Item_Access {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Items item_ac;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Long action;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;


}
