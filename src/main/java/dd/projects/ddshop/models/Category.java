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
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Subcategory> subcategories;

    public Category(String name) {
        this.name = name;
        this.subcategories = new ArrayList<>();
    }
}