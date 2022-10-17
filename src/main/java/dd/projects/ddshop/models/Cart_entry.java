package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart_entry")
public class Cart_entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    private float price_per_piece;

    private float total_price_per_entity;

    @ManyToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart_id;

    @OneToOne
    @JoinColumn(name="variant_id", referencedColumnName = "id")
    private Variant variant_id;

    public Cart_entry(final int quantity, final float price, final float v, final Cart cart, final Variant variant) {
        this.quantity=quantity;
        this.price_per_piece=price;
        this.total_price_per_entity=v;
        this.cart_id = cart;
        this.variant_id = variant;

    }
}