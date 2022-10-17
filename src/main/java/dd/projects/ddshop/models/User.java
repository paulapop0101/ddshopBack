package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="default_delivery_address", referencedColumnName = "id" )
    private Address default_delivery_address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="default_billing_address", referencedColumnName = "id")
    private Address default_billing_address;

    @OneToMany(mappedBy = "user_id",cascade = CascadeType.ALL)
    private List<Orders> orders;



}
