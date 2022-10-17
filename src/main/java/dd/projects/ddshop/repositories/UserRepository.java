package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
