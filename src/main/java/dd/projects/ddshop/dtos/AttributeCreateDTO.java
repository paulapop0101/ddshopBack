package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AttributeCreateDTO {
    private String name;

    private Boolean isOnPDP;
    private List<String> values;
    private List<SubcategoryDTO> subcategories;
}
