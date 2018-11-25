package hcmute.edu.vn.modservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="ne_cat_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat_Item {
    @EmbeddedId
    private Cat_Item_Id id;
}
