package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Integer> {
    Subcategory getSubcategoryByNameAndCategory_Name(String name,String category_name);


}
