package dd.projects.ddshop.dtos;

import lombok.Data;

@Data
public class OrderCreateDTO {
    private int user_id;
    private int cart_id;
    private String payment;
    private int delivery_address;
    private int invoice_address;
}
