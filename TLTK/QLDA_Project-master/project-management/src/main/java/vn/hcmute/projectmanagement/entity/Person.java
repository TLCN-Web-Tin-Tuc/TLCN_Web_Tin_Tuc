package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private Date dateOfBirth;

    private int sex;

    private String avatar;

    private String address;

    private String Phone;

    private String email;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String UserUpdated;

    @OneToMany(mappedBy = "person")
    private Set<Invoice> invoices;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
