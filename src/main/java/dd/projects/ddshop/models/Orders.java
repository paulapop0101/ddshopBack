package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @OneToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart_id;

    private float total_price;

    @Enumerated(EnumType.ORDINAL)
    private PaymentType payment_type;

    private Timestamp order_date;

    @OneToOne
    @JoinColumn(name="delivery_address", referencedColumnName = "id")
    private Address delivery_address;

    @OneToOne
    @JoinColumn(name="invoice_address", referencedColumnName = "id")
    private Address invoice_address;

    public Orders(User user, Cart cart, String payment, Address address1, Address address2) {
        this.user_id=user;
        this.cart_id=cart;
        this.payment_type=PaymentType.valueOf(payment);
        this.delivery_address=address1;
        this.invoice_address=address2;
        long datetime = System.currentTimeMillis();
        this.order_date = new Timestamp(datetime);
        this.total_price=cart.getTotal_price();
    }
}