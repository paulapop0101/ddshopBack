package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.dtos.UserDTO;
import dd.projects.ddshop.dtos.UserLoginDTO;
import dd.projects.ddshop.dtos.UserLoginRoleDTO;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.models.Role;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.repositories.UserRepository;
import dd.projects.ddshop.utils.PasswordUtil;
import dd.projects.ddshop.validations.UserValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserValidation userValidation;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    public UserService(final UserRepository userRepository){
        this.userRepository = userRepository;
        this.userValidation = new UserValidation(userRepository);
    }

    public UserCreationDTO addUser(final UserCreationDTO user){
        userValidation.userValidation(user);
        final User u= userMapper.dtoToModel(user);
        u.setPassword(PasswordUtil.getMd5(u.getPassword()));
        u.setRole(Role.normal_user);
        userRepository.save(u);
        return user;
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(toList());
    }
    public UserDTO updateUser(final UserDTO user, final int id){
        userExists(id);
        final User u = userRepository.getReferenceById(id);
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setPhone(user.getPhone());
        userRepository.save(u);
        return user;
    }
    public boolean deleteUser(final int id) {
        userExists(id);
        userRepository.deleteById(id);
        return true;
    }

    public void userExists(final int id)  {
        if(!userRepository.existsById(id)){
            throw new EntityDoesNotExist("Exception: User was not found!");
        }
    }

    public UserLoginRoleDTO logUser(final UserLoginDTO userDTO) {
        final User user = userRepository.findByEmail(userDTO.getEmail());
        if(user==null){
            throw new IncorrectInput("An account with this email does not exist");
        }
        if(!user.getPassword().equals(PasswordUtil.getMd5(userDTO.getPassword())))
            throw new IncorrectInput("Incorrect password");
        if(user.getDefault_delivery_address() == null)
            return new UserLoginRoleDTO(user.getId(),user.getFirstname(),user.getLastname(),
                    user.getPhone(),user.getEmail(),"",
                    "","","","","");
        return new UserLoginRoleDTO(user.getId(),user.getFirstname(),user.getLastname(),user.getPhone(),user.getEmail(),user.getDefault_delivery_address().getStreetLine(),user.getDefault_delivery_address().getPostalCode(),user.getDefault_delivery_address().getCity(),user.getDefault_delivery_address().getCounty(),user.getDefault_delivery_address().getCountry(),user.getRole().name());
    }
}
