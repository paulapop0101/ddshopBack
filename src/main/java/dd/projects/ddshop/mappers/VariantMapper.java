package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.AttributeValue;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.utils.ImageStorageUtil;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface VariantMapper {

    @Mappings({
            @Mapping(target = "name", expression = "java(variant.getProduct().getName())"),
            @Mapping(target = "added_date",dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(target = "productId",expression = "java(variant.getProduct().getId())")

    })
    VariantDTO toDTO(Variant variant);

    @Mappings({
            @Mapping(target = "attribute", expression = "java(toDTO(assignedValue.getProductAttribute()))"),
            @Mapping(target = "value",expression = "java(toDTO(assignedValue.getAttributeValue()))")


    })
    AssignedValueDTO toDTO(AssignedValue assignedValue);

    VarAttributeDTO toDTO(ProductAttribute attribute);

    AttributeValueDTO toDTO(AttributeValue value);

    List<VariantDTO> toDTO(List<Variant> variants);
//    @Mappings({
//            @Mapping(target = "values", expression = "java(toDTOStr(attribute.getAttributeValues()))")
//    })
//    VarAssignedValueDTO toDTO(ProductAttribute attribute);
//
//    List<AttributeValueDTO> toDTOStr(List<AttributeValue> attributeValue);
//
//    AttributeValueDTO toDTO(AttributeValue attributeValue);
//
//    List<VarAssignedValueDTO> toDto(List<ProductAttribute> productAttribute);

    @Mappings({
            @Mapping(target = "assignedValues", expression = "java(toModel(variantCreateDTO.getAttributes()))"),
            @Mapping(target = "url", expression = "java(getURLs(variantCreateDTO))"),
    })
    Variant toModel(VariantCreateDTO variantCreateDTO);


    default String getURLs(VariantCreateDTO variantCreateDTO) {

        String pictureUri = ImageStorageUtil.hostImage(String.valueOf(variantCreateDTO.hashCode()), variantCreateDTO.getPicture());
        return pictureUri;
    }
    default AssignedValue toModel(final int attribute){
        final AssignedValue a = new AssignedValue();
        a.setId(attribute);
        return a;
    }

   List<AssignedValue> toModel(List<Integer> attributes);
}
