package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "saleoffs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int discount;

    private Date dateStart;

    private Date dateEnd;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @ManyToMany
    @JoinTable(
            name = "saleoff_product",
            joinColumns = @JoinColumn(name = "saleoffId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "id")
    )
    private Set<Product> products;
}
