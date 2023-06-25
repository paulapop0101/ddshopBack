package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAddressDTO {
    private int id;
    private String streetLine;

    private String postalCode;

    private String city;

    private String county;

    private String country;
}
