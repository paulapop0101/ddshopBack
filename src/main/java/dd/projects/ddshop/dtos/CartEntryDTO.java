package dd.projects.ddshop.dtos;

import lombok.Data;

@Data
public class CartEntryDTO {
    private int quantity;
    private int variant_id;
}
