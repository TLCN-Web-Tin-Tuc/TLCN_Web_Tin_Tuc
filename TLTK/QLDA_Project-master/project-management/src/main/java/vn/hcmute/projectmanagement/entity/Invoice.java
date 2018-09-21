package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long total;

    private int status;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @OneToMany(mappedBy = "id.invoice")
    private Set<Invoice_Product> invoice_products;
}
