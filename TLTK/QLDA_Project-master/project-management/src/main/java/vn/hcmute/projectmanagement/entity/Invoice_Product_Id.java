package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Invoice_Product_Id implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}