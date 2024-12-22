package com.api.v01.NotificationAddressFacadeSystem.controller;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressDTO;
import com.api.v01.NotificationAddressFacadeSystem.data.Address.AddressTextUpdateDTO;
import com.api.v01.NotificationAddressFacadeSystem.exceptions.CustomException;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressService.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/address")
@AllArgsConstructor
@Validated
public class AddressController {

    private final AddressService addressService;


    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        if (addressService.checkIfAddressExists(addressDTO.getCustomerId(),addressDTO.getAddressTypeId(),addressDTO.getText())){
            throw new CustomException("Address already exists");
        }
        Address savedAddress = addressService.createAddress(addressDTO.fromDTO(addressDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable @Min(value = 1, message = "ID must be a positive number") Long id) {
        if (addressService.existsById(id)) {
            throw new CustomException("Address with id: "+id+" does not exist");
        }
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
