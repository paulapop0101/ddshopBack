package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttributeValueRepository extends JpaRepository<AttributeValue,Integer> {
    @Query(
            "select distinct atv from AssignedValue av inner join av.productAttribute pa inner join av.attributeValue atv inner join av.variants v where pa.name='color' and v.product.id=:id"
    )
    List<AttributeValue> findColorsByProductId(int id);

//    @Query
//    List<AttributeValue> findSizedByProductIdAndColor(int id, String color);
}
