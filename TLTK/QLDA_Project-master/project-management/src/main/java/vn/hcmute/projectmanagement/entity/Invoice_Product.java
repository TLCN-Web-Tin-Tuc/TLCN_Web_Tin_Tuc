package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "invoice_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice_Product {

    @EmbeddedId
    private Invoice_Product_Id id;

    private Long quantity;

    @NotNull
    private Long price;
}
