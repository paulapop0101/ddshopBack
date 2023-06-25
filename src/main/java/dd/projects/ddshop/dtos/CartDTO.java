package dd.projects.ddshop.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private int id;
    private float total_price;
    List<EntryDTO> entries;
}
