package dd.projects.ddshop.validations;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.repositories.UserRepository;


public class UserValidation {


    private final UserRepository userRepository;

    public UserValidation(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userValidation(final UserCreationDTO userCreationDTO){
        checkNull(userCreationDTO);
        checkEmpty(userCreationDTO);
        checkEmail(userCreationDTO);
        if(!userCreationDTO.getPassword().equals(userCreationDTO.getCheckPassword()))
            throw new IncorrectInput(Util.getMessage("api.error.password.not.match", null));
        checkPassword(userCreationDTO.getPassword());
        checkPhone(userCreationDTO.getPhone());
    }

    private void checkNull(final UserCreationDTO userCreationDTO) {
        if(userCreationDTO.getFirstname()==null || userCreationDTO.getLastname()==null ||
                userCreationDTO.getEmail()==null || userCreationDTO.getPhone()==null ||
                userCreationDTO.getPassword()==null || userCreationDTO.getCheckPassword()==null)
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }

    private void checkEmail(final UserCreationDTO userCreationDTO) {
        for(final User user : userRepository.findAll())
            if(user.getEmail().equals(userCreationDTO.getEmail()))
                throw new EntityAlreadyExists(Util.getMessage("api.error.duplicate", new Object[]{"Account","email"}));
    }

    private void checkPhone(final String phone) {
        if(phone.length()!=10)
            throw new IncorrectInput(Util.getMessage("api.error.phone", null));
        for(final Character c : phone.toCharArray())
            if(!Character.isDigit(c))
                throw new IncorrectInput(Util.getMessage("api.error.phone", null));

    }

    private void checkPassword(final String password) {
        boolean uppercase=false;
        if(password.length()<8)
            throw new IncorrectInput(Util.getMessage("api.error.password", null));
        for(int i=0;i<password.length();i++) {
            if(Character.isUpperCase(password.charAt(i)))
                uppercase=true;
            if(Character.isSpaceChar(password.charAt(i)))
                throw new IncorrectInput(Util.getMessage("api.error.password", null));
        }
        if(!uppercase)
            throw new IncorrectInput(Util.getMessage("api.error.password", null));
    }

    private void checkEmpty(final UserCreationDTO userCreationDTO) {
        if(userCreationDTO.getFirstname().isEmpty() || userCreationDTO.getLastname().isEmpty() ||
                userCreationDTO.getEmail().isEmpty() || userCreationDTO.getPhone().isEmpty() ||
                userCreationDTO.getPassword().isEmpty() || userCreationDTO.getCheckPassword().isEmpty())
            throw new IncorrectInput(Util.getMessage("api.error.empty.fields", null));
    }

    private boolean checkEmptyAddress(final AddressDTO addressDTO) {
        return addressDTO.getCountry().isEmpty() || addressDTO.getCounty().isEmpty() || addressDTO.getCity().isEmpty() ||
                addressDTO.getPostalCode().isEmpty() || addressDTO.getStreetLine().isEmpty();
    }
}
