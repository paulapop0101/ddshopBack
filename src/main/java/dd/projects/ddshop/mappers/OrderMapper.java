package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import dd.projects.ddshop.models.Orders;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "userEmail", expression = "java(order.getUser_id().getEmail())"),
            @Mapping(target = "order_date",dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(target = "cart_id",expression = "java(order.getCart_id().getId())"),
            @Mapping(target = "paymentType",expression = "java(order.getPayment_type().name())"),
            @Mapping(target = "totalPrice",expression = "java(order.getTotal_price())")

    })
    OrderDTO toDTO(Orders order);

    List<OrderDTO> toDTO(List<Orders> ordersList);
}
