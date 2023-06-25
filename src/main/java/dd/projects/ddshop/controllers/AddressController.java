package dd.projects.ddshop.controllers;

import dd.projects.ddshop.AppConfiguration;
import dd.projects.ddshop.dtos.AddressDTO;
import dd.projects.ddshop.dtos.GetAddressDTO;
import dd.projects.ddshop.dtos.UserCreationDTO;
import dd.projects.ddshop.mappers.UserMapper;
import dd.projects.ddshop.models.User;
import dd.projects.ddshop.utils.Util;
import dd.projects.ddshop.models.Address;
import dd.projects.ddshop.services.AddressService;
import org.mapstruct.factory.Mappers;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
public class AddressController {

    private final AddressService addressService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final MessageSource messageSource = new AppConfiguration().messageSource();
    public AddressController(final AddressService addressService){
        this.addressService = addressService;
    }
    @GetMapping("/getAllAddresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
    }
    @GetMapping("/Test")
    public ResponseEntity<String> Test() {
//        final AddressDTO a = new AddressDTO("s","s","city","s","s");
//        final UserCreationDTO u = new UserCreationDTO("s","s","s","s","s","s",a,a);
//        final User user = userMapper.dtoToModel(u);
//        System.out.println(user.getDefault_billing_address().getCity());
        return new ResponseEntity<>("here", HttpStatus.OK);
    }

    @PostMapping("/addDAddress/{id}")
    public ResponseEntity<String> addDAddress(@RequestBody final AddressDTO address,@PathVariable final int id) {
        return new ResponseEntity<>(addressService.addDAddress(address,id), HttpStatus.OK);
    }

    @PostMapping("/addBAddress/{id}")
    public ResponseEntity<String> addBAddress(@RequestBody final AddressDTO address,@PathVariable final int id) {
        return new ResponseEntity<>(addressService.addBAddress(address,id), HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody final AddressDTO address, @PathVariable final int id){
        addressService.updateAddress(address,id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public boolean deleteAddress(@PathVariable final int id)  {

        return  addressService.deleteAddress(id);
    }

    @GetMapping("/getBAddress/{id}")
    public ResponseEntity<GetAddressDTO> getBAddress(@PathVariable final int id) {
        return new ResponseEntity<>(addressService.getBAddress(id), HttpStatus.OK);
    }

    @GetMapping("/getDAddress/{id}")
    public ResponseEntity<GetAddressDTO> getDAddress(@PathVariable final int id) {
        return new ResponseEntity<>(addressService.getDAddress(id), HttpStatus.OK);
    }





}
