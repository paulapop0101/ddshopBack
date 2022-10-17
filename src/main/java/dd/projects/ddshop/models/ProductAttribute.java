package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product_attribute")
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany
    @JoinTable(name = "subcategory_product_attribute",
            joinColumns = @JoinColumn(name = "product_attribute_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id",
                    referencedColumnName = "id"))
    private List<Subcategory> subcategories;

    @OneToMany(mappedBy = "product_attributes",cascade = CascadeType.ALL)
    private List<AttributeValue> attributeValues;

    public ProductAttribute(String name) {
        this.name = name;
        this.subcategories=new ArrayList<>();
        this.attributeValues = new ArrayList<>();
    }
}