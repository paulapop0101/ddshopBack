package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VarAssignedValueDTO {
    private String name;
    private List<idValuesDTO> values;
}
