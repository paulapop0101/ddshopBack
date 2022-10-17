package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category getCategoryByName(String name);
}
