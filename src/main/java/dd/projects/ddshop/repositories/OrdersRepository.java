package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    @Query(
            value="select * from Orders o\n" +
                    "inner join User u on u.id = o.user_id\n " +
                    "where u.id = :id",
            nativeQuery = true
    )
    List<Orders> getByUser_idId(int id);

    @Query(
            value="select sum(o.total_price) from Orders o\n",
            nativeQuery = true
    )
    int getTotalIncome();
    @Query(
            value="select count(o.id) from Orders o\n",
            nativeQuery = true
    )
    int getTotalOrders();
    @Query(
            value="select count(o.id) from Orders o\n" +
                    "where o.user_id = :id\n",
            nativeQuery = true
    )
    int getTotalOrdersByUser(int id);

    @Query(
            value="select count(o.id) from Orders o\n" +
                    "where o.order_date like :month%",
            nativeQuery = true
    )
    Integer getTotalOrdersByMonth(String month);

    @Query(
            value="select sum(o.total_price) from Orders o\n" +
                    "where o.order_date like :month%",
            nativeQuery = true
    )
    Integer getTotalIncomeByMonth(String month);

}
