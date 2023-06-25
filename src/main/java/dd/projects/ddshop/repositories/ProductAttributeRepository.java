package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute,Integer> {
    ProductAttribute getProductAttributeByName(String name);
    List<ProductAttribute> getProductAttributesBySubcategories(Subcategory subcategory);

    List<ProductAttribute> getProductAttributesBySubcategoriesAndIsOnPDPOrderById(Subcategory subcategory, boolean IsOnPDP);
}
