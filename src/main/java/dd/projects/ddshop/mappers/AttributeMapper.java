package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.models.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AttributeMapper {

  //  ProductAttribute toModel(AttributeCreateDTO attributeCreateDTO);
    @Mappings({
            @Mapping(target = "values", expression = "java(toDTOStr(attribute.getAttributeValues()))")
    })
     AttributeDTO toDTO(ProductAttribute attribute);

     List<AttributeValueDTO> toDTOStr(List<AttributeValue> attributeValue);

    AttributeValueDTO toDTO(AttributeValue attributeValue);


    default SubcategoryDTO toDTO(Subcategory subcategory){
        return new SubcategoryDTO(subcategory.getId(),subcategory.getName(),subcategory.getCategory().getName());
    }

}
