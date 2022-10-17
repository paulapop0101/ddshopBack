package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.OrderCreateDTO;
import dd.projects.ddshop.models.*;
import dd.projects.ddshop.repositories.AddressRepository;
import dd.projects.ddshop.repositories.CartRepository;
import dd.projects.ddshop.repositories.OrdersRepository;
import dd.projects.ddshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrdersRepository ordersRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final AddressRepository addressRepository;


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
        return orderCreateDTO;
    }

    public boolean deleteOrder(final int id) {
        ordersRepository.deleteById(id);
        return true;
    }
}
