package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    @Query(
            value="select count(o.id) from User o\n",
            nativeQuery = true
    )
    int countUsers();

    @Query(
            value="select * from User u\n" +
                    "where u.role='normal_user'",
            nativeQuery = true
    )
    List<User> findAllByRoleNormal_user();
}
