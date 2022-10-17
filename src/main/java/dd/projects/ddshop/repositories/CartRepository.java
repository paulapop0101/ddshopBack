package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}
