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
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="subcategory_id", referencedColumnName = "id")
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Variant> variants;

    public Product(String name, String description, Subcategory subcategory) {
        this.name=name;
        this.description=description;
        this.subcategory = subcategory;
        this.variants=new ArrayList<>();
    }

}
