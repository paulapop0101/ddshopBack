package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String streetLine;


    private String postalCode;

    private String city;

    private String county;

    private String country;

    public Address(String streetLine, String postalCode, String city, String county, String country) {
        this.streetLine = streetLine;
        this.city=city;
        this.country=country;
        this.county=county;
        this.postalCode=postalCode;
    }
}
