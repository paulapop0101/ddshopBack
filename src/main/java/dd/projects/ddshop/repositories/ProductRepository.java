package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
    List<Product> findBySubcategoryId(int id);

}
