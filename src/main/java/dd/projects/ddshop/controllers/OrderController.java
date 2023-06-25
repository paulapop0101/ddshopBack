package dd.projects.ddshop.controllers;

import dd.projects.ddshop.dtos.OrderCreateDTO;
import dd.projects.ddshop.dtos.OrderDTO;
import dd.projects.ddshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
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

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable final int id){

        return new ResponseEntity<>( orderService.getOrder(id), HttpStatus.OK);
    }
    @GetMapping("/getOrders/{id}")
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable final int id){
        return new ResponseEntity<>( orderService.getOrders(id), HttpStatus.OK);
    }



    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return new ResponseEntity<>( orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/getIncome")
    public ResponseEntity<Integer> getTotalIncome(){

        return new ResponseEntity<>( orderService.getTotalIncome(), HttpStatus.OK);
    }
    @GetMapping("/getOrders")
    public ResponseEntity<Integer> getTotalOrders(){

        return new ResponseEntity<>( orderService.getTotalOrders(), HttpStatus.OK);
    }
    @GetMapping("/getTotalOrders/{id}")
    public ResponseEntity<Integer> getTotalOrdersByUser(@PathVariable final int id){

        return new ResponseEntity<>( orderService.getTotalOrdersByUser(id), HttpStatus.OK);
    }
    @GetMapping("/getIncomeByMonths")
    public ResponseEntity<List<Integer>> getTotalIncomeByMonths(){

        return new ResponseEntity<>( orderService.getTotalIncomeByMonths(), HttpStatus.OK);
    }
    @GetMapping("/getOrdersByMonths")
    public ResponseEntity<List<Integer>> getTotalOrdersByMonths(){

        return new ResponseEntity<>( orderService.getTotalOrdersByMonths(), HttpStatus.OK);
    }
}
