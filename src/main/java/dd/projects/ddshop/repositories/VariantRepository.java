package dd.projects.ddshop.repositories;

import dd.projects.ddshop.dtos.ColorsDTO;
import dd.projects.ddshop.dtos.DistinctVariantIdsDTO;
import dd.projects.ddshop.dtos.SizesDTO;
import dd.projects.ddshop.models.Variant;
import org.hibernate.criterion.Distinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface VariantRepository extends JpaRepository<Variant,Integer> {


    @Query(
        "select v from Variant v where v.product.subcategory.id=:id and v.id in :distinctIds"
    )
    List<Variant> getVariantsByProductSubcategoryIdAndIdInDistinctIds(int id,List<Integer> distinctIds);

    @Query(
        "select new dd.projects.ddshop.dtos.DistinctVariantIdsDTO(v.id,v.product.id,atv.value) from Variant v " +
                "inner join v.assignedValues av " +
                "inner join av.productAttribute pa " +
                "inner join av.attributeValue atv where pa.name='color'"
    )
    Set<DistinctVariantIdsDTO> getDistinctVariantsByColor();

    @Query(
            "select new dd.projects.ddshop.dtos.SizesDTO(v.id,atv.value) from Variant v \n" +
                    "inner join v.assignedValues av \n" +
                    "inner join av.productAttribute pa \n" +
                    "inner join av.attributeValue atv \n" +
                    "where v.product.id=:prodid and pa.id = :attid"
    )
    List<SizesDTO> getVariantsFirstAttribute(int prodid,int attid);

    @Query(
            "select new dd.projects.ddshop.dtos.SizesDTO(v.id,av.attributeValue.value) from Variant v \n" +
                    "inner join v.assignedValues av \n" +
                    "where v.product.id=:prodid and av.productAttribute.id = :attid2  \n" +
                    "and v.id in (select v.id from Variant v\n" +
                                  "inner join v.assignedValues av inner join av.productAttribute pa \n" +
                                    "inner join av.attributeValue atv \n" +
                                    "where v.product.id=:prodid and av.productAttribute.id = :attid1 and atv.value= :valId \n)"
    )
    List<SizesDTO> getVariantsSecondAttribute(int prodid,int attid1, int attid2, String valId);

    @Query(
            "select new dd.projects.ddshop.dtos.SizesDTO(v.id,atv.value) from  Variant v " +
                    "inner join v.assignedValues av " +
                    "inner join av.productAttribute pa " +
                    "inner join av.attributeValue atv where v.product.id=:id and pa.name='size' " +
                    "and v.id in (select v.id from Variant v inner join v.assignedValues av " +
                    "               inner join av.productAttribute pa inner join av.attributeValue atv " +
                    "               where v.product.id=:id and atv.id=:color)" +
                    "order by atv.value"
    )
    List<SizesDTO> findSizesByProductID(int id,int color);

    @Query( value=
            "select * from Variant v order by v.price asc limit 1", nativeQuery = true
    )
    Variant getFirstVariantByPriceASC();

    List<Variant> getByProduct_SubcategoryId(int id);

    @Query(
            value = "select v from Variant v\n" +
                    "join v.assignedValues vav \n" +
                    "join v.product p \n" +
                    "where p.subcategory.id = :sub\n" +
                    "and v.price > :lp \n" +
                    "and v.price < :hp \n" +
                    "and vav.productAttribute.id = :att and vav.attributeValue.id in :values"
    )
    List<Variant> getFilteredByAttributes(int sub,float lp,float hp,int att, List<Integer> values);

    @Query(
            value = "select distinct v from Variant v\n" +
                    "join v.assignedValues vav \n" +
                    "join v.product p \n" +
                    "where p.subcategory.id = :sub\n" +
                    "and v.price > :lp \n" +
                    "and v.price < :hp \n" +
                    "group by (v.url)"
    )
    List<Variant> getFilteredByPrice(int sub,float lp,float hp);


    @Query(
            value = "select distinct v from Variant v\n" +
                    "join v.assignedValues vav \n" +
                    "join v.product p \n" +
                    "where p.subcategory.id = :sub\n" +
                    "and v.price > :lp \n" +
                    "and v.price < :hp \n" +
                    "and p.name like %:word%\n" +
                    "group by (v.url)"
    )
    List<Variant> getFilteredByPriceWord(int sub, float lp, float hp,@Param("word") String word);

    @Query(
            value = "select v from Variant v\n" +
                    "join v.assignedValues vav \n" +
                    "join v.product p \n" +
                    "where p.subcategory.id = :sub\n" +
                    "and v.price > :lp \n" +
                    "and v.price < :hp \n" +
                    "and p.name like %:word%\n" +
                    "and vav.productAttribute.id = :att and vav.attributeValue.id in :values"
    )
    List<Variant> getFilteredByAttributesWord(int sub, float lp, float hp, int att, List<Integer> values,@Param("word") String word);
    Variant getFirstByProductSubcategoryId(int subcategoryID);
}
