package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="user", referencedColumnName = "id")
    private User user;

    private float total_price;

    private int status;

    @OneToMany(mappedBy = "cart_id",cascade = CascadeType.ALL)
    private List<Cart_entry> cart_entries;

    public Cart(User user){
        this.user=user;
        this.total_price = 0;
        this.cart_entries=new ArrayList<>();
        this.status=0;
    }
}
