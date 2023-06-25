package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.OrderCreateDTO;
import dd.projects.ddshop.dtos.OrderDTO;
import dd.projects.ddshop.mappers.OrderMapper;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.models.*;
import dd.projects.ddshop.repositories.AddressRepository;
import dd.projects.ddshop.repositories.CartRepository;
import dd.projects.ddshop.repositories.OrdersRepository;
import dd.projects.ddshop.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final AddressRepository addressRepository;

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);


    @Autowired
    public OrderService(final OrdersRepository ordersRepository, final UserRepository userRepository, final CartRepository cartRepository, final AddressRepository addressRepository) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }

    public OrderCreateDTO createOrder(final OrderCreateDTO orderCreateDTO){
        final User user = userRepository.getReferenceById(orderCreateDTO.getUser_id());
        final Cart cart = cartRepository.getReferenceById(orderCreateDTO.getCart_id());
        final Address address1 = addressRepository.getReferenceById(orderCreateDTO.getDelivery_address());
        final Address address2 = addressRepository.getReferenceById(orderCreateDTO.getInvoice_address());
        final Orders orders = new Orders(user,cart,orderCreateDTO.getPayment(),address1,address2);
        ordersRepository.save(orders);
        cart.setStatus(1);
        cartRepository.save(cart);
        return orderCreateDTO;
    }

    public boolean deleteOrder(final int id) {
        ordersRepository.deleteById(id);
        return true;
    }

    public List<OrderDTO> getOrders(int id) {

        return orderMapper.toDTO(ordersRepository.getByUser_idId(id));
    }

    public OrderDTO getOrder(int id){
        return orderMapper.toDTO(ordersRepository.getReferenceById(id));
    }

    public int getTotalIncome(){
        return ordersRepository.getTotalIncome();
    }
    public List<Integer> getTotalIncomeByMonths(){
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=12;i++)
        {
            if(i<10){
                if(ordersRepository.getTotalIncomeByMonth("2023-0"+i)!=null){
                    list.add(ordersRepository.getTotalIncomeByMonth("2023-0"+i));
                }
                else list.add(0);

            }
            else{
                if(ordersRepository.getTotalIncomeByMonth("2023-"+i)!=null){
                    list.add(ordersRepository.getTotalIncomeByMonth("2023-"+i));
                }
                else list.add(0);
            }
        }

        return list;
    }
    public int getTotalOrders(){
        return ordersRepository.getTotalOrders();
    }

    public List<Integer> getTotalOrdersByMonths(){
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=12;i++)
        {
            if(i<10){
                if(ordersRepository.getTotalOrdersByMonth("2023-0"+i)!=null){
                    list.add(ordersRepository.getTotalOrdersByMonth("2023-0"+i));
                }
                else list.add(0);

            }
            else{
                if(ordersRepository.getTotalOrdersByMonth("2023-"+i)!=null){
                    list.add(ordersRepository.getTotalOrdersByMonth("2023-"+i));
                }
                else list.add(0);
            }
        }

        return list;
    }

    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDTO(ordersRepository.findAll());
    }

    public int getTotalOrdersByUser(int id) {
        return ordersRepository.getTotalOrdersByUser(id);
    }
}
