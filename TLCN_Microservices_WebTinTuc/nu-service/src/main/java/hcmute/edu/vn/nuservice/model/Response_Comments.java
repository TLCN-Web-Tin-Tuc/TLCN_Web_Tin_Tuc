package hcmute.edu.vn.nuservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ne_response_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response_Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comments comments;

    private String Content;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;
}
