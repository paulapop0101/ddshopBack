package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class seeProductDTO {
    private int id;
    private String name;

    private String description;

    private SubcategoryDTO subcategory;
}
