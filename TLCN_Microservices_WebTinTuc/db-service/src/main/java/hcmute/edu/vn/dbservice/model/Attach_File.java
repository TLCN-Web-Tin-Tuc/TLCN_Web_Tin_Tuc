package hcmute.edu.vn.dbservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ne_attach_file")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attach_File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Items item;

    private String fileName;

    private String fileExtension;

    private byte[] fileContent;

    private String link;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;
}
