package com.api.v01.NotificationAddressFacadeSystem.controller;

import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressType;
import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressTypeDTO;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressType.AddressTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@AllArgsConstructor
public class AddresTypeController {

    private AddressTypeService addressTypeService;

    @PostMapping("/create")
    public ResponseEntity<AddressType> createAddressType(@RequestBody AddressTypeDTO addressTypeDTO) {
        if (addressTypeService.existsByTypeName(addressTypeDTO.getName())) {
            throw new ClassCastException("AddressType with type name: " + addressTypeDTO.getName() + " exists already");
        }

        AddressType savedAddresType = addressTypeService.createAddressType(addressTypeDTO.fromDTO(addressTypeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddresType);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddressType(@PathVariable Long id) {
        if (!addressTypeService.existsById(id)){
            throw new ClassCastException("AddressType with id: " + id + " does not exists");
        }
        addressTypeService.deleteById(id);
        return new ResponseEntity<>("AddressType deleted successfully",HttpStatus.OK);
    }
}
