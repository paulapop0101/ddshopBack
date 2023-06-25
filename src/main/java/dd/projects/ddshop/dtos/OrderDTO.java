package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {
    private int id;
    private String userEmail;
    private String order_date;

    private String paymentType;

    private Float totalPrice;

    private int cart_id;
}
