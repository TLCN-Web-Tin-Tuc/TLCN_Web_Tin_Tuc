package hcmute.edu.vn.modservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ne_response_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userRcm;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comments;

    private String Content;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;
}
