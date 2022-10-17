package dd.projects.ddshop.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EntryDTO {
   private int id;
   private int variant_id;
   private String name;
   private int quantity;
   private float price;
   private float totalPrice;
   private List<AssignedValueDTO> assignedValueDTOList;

}
