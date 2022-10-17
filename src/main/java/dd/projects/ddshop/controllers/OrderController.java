package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.OrderCreateDTO;
import dd.projects.ddshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderCreateDTO> createOrder(@RequestBody final OrderCreateDTO orderCreateDTO){
        return new ResponseEntity<>( orderService.createOrder(orderCreateDTO), HttpStatus.OK);
    }
    @DeleteMapping("/deleteOrder/{id}")
    public boolean deleteOrder(@PathVariable final int id){
        return orderService.deleteOrder(id);
    }
}
