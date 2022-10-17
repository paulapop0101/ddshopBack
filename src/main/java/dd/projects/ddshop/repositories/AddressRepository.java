package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address,Integer> {
}
