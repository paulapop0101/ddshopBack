package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.GetAddressDTO;
import dd.projects.ddshop.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface AddressMapper {

    GetAddressDTO toDTO(Address address);

    Address toModel(AddressDTO addressDTO);
}
