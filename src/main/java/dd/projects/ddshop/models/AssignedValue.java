package dd.projects.ddshop.models;

import dd.projects.ddshop.dtos.AssignedValueDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="assigned_value")
public class AssignedValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="product_attribute_id", referencedColumnName = "id")
    ProductAttribute productAttribute;

    @OneToOne
    @JoinColumn(name="attribute_value_id", referencedColumnName = "id")
    AttributeValue attributeValue;

    @ManyToMany(mappedBy = "assignedValues",cascade = CascadeType.ALL)
    private List<Variant> variants;

    public AssignedValue(final ProductAttribute attribute, final AttributeValue attributeValue) {
        this.productAttribute = attribute;
        this.attributeValue = attributeValue;
        this.variants = new ArrayList<>();
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AssignedValue that = (AssignedValue) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}