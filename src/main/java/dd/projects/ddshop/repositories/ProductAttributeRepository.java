package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute,Integer> {
    ProductAttribute getProductAttributeByName(String name);
}
