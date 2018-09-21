package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String briefInfo;

    private String images;

    private String introduction;

    private Long quantity;

    private String status;

    private Long price;

    private Date dateCreated;

    private String userCreated;

    private Date dateUpdated;

    private String userUpdated;

    @OneToMany(mappedBy = "id.product")
    private Set<Invoice_Product> invoice_products;

    @ManyToMany(mappedBy = "products")
    private Set<Category> categories;

    @ManyToMany(mappedBy = "products")
    private Set<SaleOff> saleOffs;
}
