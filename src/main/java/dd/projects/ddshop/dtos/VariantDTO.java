package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariantDTO {
    private int id;
    private int productId;
    private String name;
    private float price;
    private int quantity;
    private String added_date;
    private List<AssignedValueDTO> assignedValues;
}
