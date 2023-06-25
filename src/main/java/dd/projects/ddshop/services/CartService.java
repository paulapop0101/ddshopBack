package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CartDTO;
import dd.projects.ddshop.dtos.CartEntryDTO;
import dd.projects.ddshop.mappers.CartMapper;
import dd.projects.ddshop.models.Cart;
import dd.projects.ddshop.models.Cart_entry;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.models.Variant;
import dd.projects.ddshop.repositories.CartEntryRepository;
import dd.projects.ddshop.repositories.CartRepository;
import dd.projects.ddshop.repositories.UserRepository;
import dd.projects.ddshop.repositories.VariantRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final CartEntryRepository cartEntryRepository;

    private final VariantRepository variantRepository;

    private final CartMapper cartMapper = Mappers.getMapper(CartMapper.class);

    @Autowired
    public CartService(final UserRepository userRepository, final CartRepository cartRepository, final CartEntryRepository cartEntryRepository, final VariantRepository variantRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartEntryRepository = cartEntryRepository;
        this.variantRepository = variantRepository;
    }

    public CartEntryDTO addEntry(final CartEntryDTO cartEntryDTO,final String email) {
        final int user_id=userRepository.findByEmail(email).getId();
        Cart cart = cartExists(user_id);
        if (cart == null) {
            cart = new Cart(userRepository.getReferenceById(user_id));
            cartRepository.save(cart);
        }

        final Cart_entry entry = entryExists(cart, cartEntryDTO.getVariant_id());
        if (entry != null) { //if variant already in cart
            entry.setQuantity(entry.getQuantity() + cartEntryDTO.getQuantity());
            entry.setTotal_price_per_entity(entry.getPrice_per_piece() * entry.getQuantity());
            cart.setTotal_price(cart.getTotal_price() + entry.getPrice_per_piece() * cartEntryDTO.getQuantity());
            cartEntryRepository.save(entry);
        } else {
            final Variant variant = variantRepository.getReferenceById(cartEntryDTO.getVariant_id());
            final Cart_entry cart_entry = new Cart_entry(cartEntryDTO.getQuantity(), variant.getPrice(), variant.getPrice() * cartEntryDTO.getQuantity(), cart, variant);
            cartEntryRepository.save(cart_entry);
            cart.getCart_entries().add(cart_entry);
            cart.setTotal_price(cart.getTotal_price() + variant.getPrice() * cartEntryDTO.getQuantity());

        }
        cartRepository.save(cart);
        return cartEntryDTO;
    }


    private Cart_entry entryExists(final Cart cart, final int variant_id) {
        for (final Cart_entry entry : cart.getCart_entries())
            if (entry.getVariant_id().getId() == variant_id)
                return entry;
        return null;
    }

    private Cart cartExists(final int user_id) {
        for (final Cart cart : cartRepository.findAll())
            if (cart.getUser().getId() == user_id && cart.getStatus() == 0)
                return cart;
        return null;
    }

    public boolean decreaseEntryQuantity(final int id){
        final Cart_entry entry = cartEntryRepository.getReferenceById(id);
        final Cart cart = entry.getCart_id();
        entry.setQuantity(entry.getQuantity()-1);
        entry.setTotal_price_per_entity(entry.getPrice_per_piece()*entry.getQuantity());
        cart.setTotal_price(cart.getTotal_price()-entry.getPrice_per_piece());
        cartEntryRepository.save(entry);
        cartRepository.save(cart);
        return true;
    }
    public boolean deleteCartEntry(final int id) {
        final Cart_entry entry = cartEntryRepository.getReferenceById(id);
        final Cart cart = entry.getCart_id();
        cart.setTotal_price(cart.getTotal_price() - entry.getTotal_price_per_entity());
        cartEntryRepository.deleteById(id);
        return true;
    }

    public boolean deleteCart(final int id) {
        cartRepository.deleteById(id);
        return true;
    }

    public CartDTO getUserCart(final String email) {
        final Cart cart = cartExists(userRepository.findByEmail(email).getId());
        return cartMapper.toDTO(cart);
    }
    public Integer getItemsCount(final String email){
        return cartEntryRepository.countCartItems(email);
    }

    public CartDTO getCart(int id) {
        return cartMapper.toDTO(cartRepository.getReferenceById(id));
    }

    public int getSales() {
        return cartEntryRepository.countSales();
    }
}
