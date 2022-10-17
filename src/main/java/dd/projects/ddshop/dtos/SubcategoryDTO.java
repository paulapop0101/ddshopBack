package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubcategoryDTO {
    private int id;
    private String name;
    private String categoryName;
}
