package com.api.v01.NotificationAddressFacadeSystem.controller.address;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressDTO;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressTextUpdateDTO;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import com.api.v01.NotificationAddressFacadeSystem.exceptions.CustomException;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressService.AddressService;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressType.AddressTypeService;
import com.api.v01.NotificationAddressFacadeSystem.service.Customer.CustomerService;
import com.api.v01.NotificationAddressFacadeSystem.service.Relation.RelationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/address")
@AllArgsConstructor
@Validated
public class AddressApiController {

    private final AddressService addressService;
    private final RelationService relationService;
    private final CustomerService customerService;
    private final AddressTypeService addressTypeService;


    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO) {

        if (addressService.checkIfAddressExists(addressDTO.getCustomerId(),addressDTO.getAddressTypeId(),addressDTO.getText())){
            throw new CustomException("Address already exists");
        }

        Long customerId = addressDTO.getCustomerId();

        Long addressTypeId = addressDTO.getAddressTypeId();

        if (customerService.findById(customerId).isEmpty()) {
            throw new CustomException("Customer with id"+customerId+"not found");
        }
        if (addressTypeService.existsById(addressTypeId)) {
            throw new CustomException("AddressType with id"+addressTypeId+"not found");
        }

        Relation relation = relationService.createRelation(addressDTO.getCustomerId(),addressDTO.getAddressTypeId());

        customerService.addRelation(addressDTO.getCustomerId(),relation.getId());


        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createAddress(addressDTO.fromDTO(addressDTO)));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable @Min(value = 1, message = "ID must be a positive number") Long id) {

        Address address = addressService.findById(id).orElseThrow(() ->  new CustomException("Address with id: "+id+" does not exist"));

        Optional<Relation> relation = relationService.findByCustomerIdAndAddressTypeId(address.getCustomerId(),address.getAddressTypeId());

        if (relation.isEmpty()) {
            throw new CustomException("Relation for that customer not found");
        }

        relationService.deleteById(relation.get().getId());


        addressService.delete(id);
        return new ResponseEntity<>("Address deleted successfully",HttpStatus.OK);
    }

    @PutMapping("update-address")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody AddressTextUpdateDTO addressTextUpdateDTO) {
        if (addressService.existsById(addressTextUpdateDTO.getAddressId())) {
            throw new CustomException("Address with id: " + addressTextUpdateDTO.getAddressId()+ " does not exist");
        }

        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddressText(addressTextUpdateDTO.getAddressId(),addressTextUpdateDTO.getText()));
    }


}
