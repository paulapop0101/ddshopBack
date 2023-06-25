package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.GetAddressDTO;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.mappers.AddressMapper;
import dd.projects.ddshop.mappers.AttributeMapper;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.repositories.AddressRepository;
import dd.projects.ddshop.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {


    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    private final AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);



    @Autowired
    public AddressService(final AddressRepository addressRepository, final UserRepository userRepository){
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }
    public void addAddress(final Address address){ addressRepository.save(address);}

    public List<Address> getAllAddresses() {
        return  addressRepository.findAll();
    }
    public void updateAddress(final AddressDTO address, final int id){
        addressExists(id);
        final Address a = addressRepository.getReferenceById(id);
        a.setCity(address.getCity());
        a.setCountry(address.getCountry());
        a.setCounty(address.getCounty());
        a.setPostalCode(address.getPostalCode());
        a.setStreetLine(address.getStreetLine());
        addressRepository.save(a);
    }

    public boolean deleteAddress(final int id) {
        addressRepository.deleteById(id);
        return true;
    }

    public void addressExists(final int id) {
        if(!addressRepository.existsById(id))
            throw new EntityDoesNotExist("Exception address does not exist");

    }

    public String addDAddress(AddressDTO addressDTO, int id) {
        User user = userRepository.getReferenceById(id);
        Address address = addressMapper.toModel(addressDTO);
        user.setDefault_delivery_address(address);
        userRepository.save(user);
        return "ok";
    }

    public String addBAddress(AddressDTO addressDTO, int id) {
        User user = userRepository.getReferenceById(id);
        Address address = addressMapper.toModel(addressDTO);
        user.setDefault_billing_address(address);
        userRepository.save(user);
        return "ok";
    }

    public GetAddressDTO getDAddress(int id){
        User user = userRepository.getReferenceById(id);
        Address address = user.getDefault_delivery_address();
        if(address==null)
            return new GetAddressDTO(0,"","","","","");
        return addressMapper.toDTO(address);
    }
    public GetAddressDTO getBAddress(int id){
        User user = userRepository.getReferenceById(id);
        Address address = user.getDefault_billing_address();
        if(address==null)
            return new GetAddressDTO(0,"","","","","");
        return addressMapper.toDTO(address);
    }
}
