package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.exceptions.EntityDoesNotExist;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {


    private final AddressRepository addressRepository;
    @Autowired
    public AddressService(final AddressRepository addressRepository){
        this.addressRepository = addressRepository;
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
}
