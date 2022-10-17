package dd.projects.ddshop.dtos;

import dd.projects.ddshop.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
public class UserCreationDTO {

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String password;

    private String checkPassword;

}
