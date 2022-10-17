package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data@AllArgsConstructor
public class VariantCreateDTO {
    private String quantity;

    private String price;

    private int product_id;

    private List<Integer> attributes;
}

