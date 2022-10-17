package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignedValueRepository extends JpaRepository<AssignedValue,Integer> {
    List<AssignedValue> findByProductAttribute(ProductAttribute productAttribute);

    List<AssignedValue> findByIdAndProductAttribute(int id, ProductAttribute productAttribute);

    AssignedValue findByProductAttributeAndAttributeValue(ProductAttribute attribute, AttributeValue value);
}
