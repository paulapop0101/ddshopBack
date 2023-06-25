package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterDTO {
    private float lprice;
    private float hprice;

    private List<FilterAttributeDTO> attributes;
}
