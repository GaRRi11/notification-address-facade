package com.api.v01.NotificationAddressFacadeSystem.controller;

import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressType;
import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressTypeDTO;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressType.AddressTypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/address-type")
@Validated
@RestController
public class AddressTypeController {

    private AddressTypeService addressTypeService;

    @PostMapping("/create")
    public ResponseEntity<AddressType> createAddressType(@Valid  @RequestBody AddressTypeDTO addressTypeDTO) {
        if (addressTypeService.existsByTypeName(addressTypeDTO.getName())) {
            throw new ClassCastException("AddressType with type name: " + addressTypeDTO.getName() + " exists already");
        }

        AddressType savedAddressType = addressTypeService.createAddressType(addressTypeDTO.fromDTO(addressTypeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddressType);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddressType(@PathVariable @Min(value = 1, message = "ID must be a positive number") Long id) {
        if (addressTypeService.existsById(id)){
            throw new ClassCastException("AddressType with id: " + id + " does not exists");
        }
        addressTypeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("AddressType deleted successfully");
    }
}
