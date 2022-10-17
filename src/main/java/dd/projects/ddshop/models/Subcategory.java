package dd.projects.ddshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString
@Table(name = "subcategory")
public class Subcategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToOne
  @JoinColumn(name="category_id", referencedColumnName = "id")
  private Category category;

  @ManyToMany(mappedBy = "subcategories")
  private List<ProductAttribute> productAttributes;

  @OneToMany(mappedBy = "subcategory",cascade = CascadeType.ALL)
  private List<Product> products;

  public Subcategory(String name, Category category) {
    this.name = name;
    this.category=category;
    this.productAttributes = new ArrayList<>();
    this.products = new ArrayList<>();
  }


}
