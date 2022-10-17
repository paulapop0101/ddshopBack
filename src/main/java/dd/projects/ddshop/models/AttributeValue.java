package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="attribute_value")
public class AttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "product_attribute_id", referencedColumnName = "id")
    ProductAttribute product_attributes;


    public AttributeValue(String value, ProductAttribute attribute) {
        this.value=value;
        this.product_attributes = attribute;
    }
}