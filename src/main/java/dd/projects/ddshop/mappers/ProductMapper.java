package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.SubcategoryDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.models.Product;
import dd.projects.ddshop.models.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ProductMapper {


    Product toModel(ProductDTO productDTO);

    seeProductDTO toDTO(Product product);

    default SubcategoryDTO toDTO(Subcategory subcategory){
        return new SubcategoryDTO(subcategory.getId(),subcategory.getName(),subcategory.getCategory().getName());
    }


}
