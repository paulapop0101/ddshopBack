package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDTO toDTO(User user);

    User dtoToModel(final UserCreationDTO userCreationDTO);


    Address dtoToModel(AddressDTO addressDTO);
}
