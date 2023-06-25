package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Cart_entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;

public interface CartEntryRepository extends JpaRepository<Cart_entry,Integer> {

    @Query(
            "select sum(e.quantity) from Cart_entry e inner join e.cart_id c " +
                    "where c.status=0 and c.user.email=:email"
    )
    Integer countCartItems(String email);

    @Query(
            "select sum(e.quantity) from Cart_entry e "
    )
    int countSales();
}
