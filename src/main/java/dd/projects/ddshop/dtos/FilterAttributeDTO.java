package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterAttributeDTO {
    private int attributeId;
    private List<Integer> valueIds;
}
