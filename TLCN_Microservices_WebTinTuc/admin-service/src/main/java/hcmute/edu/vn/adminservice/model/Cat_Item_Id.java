package hcmute.edu.vn.adminservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cat_Item_Id implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private Cat cat;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
}
