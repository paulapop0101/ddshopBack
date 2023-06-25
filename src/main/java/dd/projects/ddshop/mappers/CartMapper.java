package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.*;
import dd.projects.ddshop.models.AssignedValue;
import dd.projects.ddshop.models.Cart;
import dd.projects.ddshop.models.Cart_entry;
import dd.projects.ddshop.models.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface CartMapper {

    @Mappings({
            @Mapping(target = "entries", expression = "java(toDTO(cart.getCart_entries()))")
    })
    CartDTO toDTO(Cart cart);

    @Mappings({
            @Mapping(target = "variant", expression = "java(toDTO(entry.getVariant_id()))"),
            @Mapping(target = "name", expression = "java(entry.getVariant_id().getProduct().getName())"),
            @Mapping(target = "price", expression = "java(entry.getPrice_per_piece())"),
            @Mapping(target = "totalPrice", expression = "java(entry.getTotal_price_per_entity())"),
            @Mapping(target = "url", expression = "java(entry.getVariant_id().getUrl())"),
            @Mapping(target = "assignedValueDTOList", expression = "java(toDTOAV(entry.getVariant_id().getAssignedValues()))")
    })
    EntryDTO toDTO(Cart_entry entry);

    List<EntryDTO> toDTO(List<Cart_entry> cart_entryList);

    default AssignedValueDTO toDTOAV(final AssignedValue assignedValue){
        final VarAttributeDTO varAttributeDTO = new VarAttributeDTO(assignedValue.getProductAttribute().getId(),assignedValue.getProductAttribute().getName());
        final AttributeValueDTO attributeValueDTO = new AttributeValueDTO(assignedValue.getAttributeValue().getId(),assignedValue.getAttributeValue().getValue());
        return new AssignedValueDTO(assignedValue.getId(),varAttributeDTO,attributeValueDTO);
    }

    List<AssignedValueDTO> toDTOAV(List<AssignedValue> assignedValues);

    @Mappings({
            @Mapping(target = "name", expression = "java(variant.getProduct().getName())"),
            @Mapping(target = "added_date",dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(target = "productId",expression = "java(variant.getProduct().getId())")

    })
    VariantDTO toDTO(Variant variant);
}
