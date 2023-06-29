package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Feature;
import dd.projects.ddshop.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Integer> {
}
