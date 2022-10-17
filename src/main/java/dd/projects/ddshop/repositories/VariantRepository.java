package dd.projects.ddshop.repositories;

import dd.projects.ddshop.dtos.ColorsDTO;
import dd.projects.ddshop.dtos.DistinctVariantIdsDTO;
import dd.projects.ddshop.dtos.SizesDTO;
import dd.projects.ddshop.models.Variant;
import org.hibernate.criterion.Distinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface VariantRepository extends JpaRepository<Variant,Integer> {


    @Query(
        "select v from Variant v where v.product.subcategory.id=:id and v.id in :distinctIds"
    )
    List<Variant> getVariantsByProductSubcategoryIdAndIdInDistinctIds(int id,List<Integer> distinctIds);

    @Query(
        "select new dd.projects.ddshop.dtos.DistinctVariantIdsDTO(v.id,v.product.id,atv.value) from Variant v inner join v.assignedValues av inner join av.productAttribute pa inner join av.attributeValue atv where pa.name='color'"
    )
    Set<DistinctVariantIdsDTO> getDistinctVariantsByColor();


    @Query(
            "select new dd.projects.ddshop.dtos.SizesDTO(v.id,atv.value) from  Variant v " +
                    "inner join v.assignedValues av " +
                    "inner join av.productAttribute pa " +
                    "inner join av.attributeValue atv where v.product.id=:id and pa.name='size' " +
                    "and v.id in (select v.id from Variant v inner join v.assignedValues av " +
                    "               inner join av.productAttribute pa inner join av.attributeValue atv " +
                    "               where v.product.id=:id and atv.value=:color)" +
                    "order by atv.value"
    )
    List<SizesDTO> findSizesByProductID(int id,String color);







}
