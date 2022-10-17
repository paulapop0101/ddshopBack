package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRoleDTO {
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String streetLine;
    private String postalCode;
    private String city;
    private String county;
    private String country;
    private String role;
}
