package com.api.v01.NotificationAddressFacadeSystem.controller;

import com.api.v01.NotificationAddressFacadeSystem.data.AddressType.AddressType;
import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Customer.CustomerDTO;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.RelationDTO;
import com.api.v01.NotificationAddressFacadeSystem.exceptions.CustomException;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressService.AddressService;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressType.AddressTypeService;
import com.api.v01.NotificationAddressFacadeSystem.service.Customer.CustomerService;
import com.api.v01.NotificationAddressFacadeSystem.service.Relation.RelationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AddressTypeService addressTypeService;
    private final RelationService relationService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);

        if (!customer.isPresent()) {
            throw new CustomException("Customer with id"+id+"not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customer.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customerDTO) {
        if (customerService.findByUsername(customerDTO.getUsername()).isPresent()) {
            throw new CustomException("Customer with username"+customerDTO.getUsername()+"already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDTO.fromDTO(customerDTO)));
    }

    @PutMapping("/{customerId}/update-username")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        if (!customerService.findById(customerId).isPresent()) {
            throw new CustomException("Customer with id"+customerId+"not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateUsername(customerId,customerDTO.getUsername()));
    }

    @PostMapping("/add-relation")
    public ResponseEntity<Customer> addRelation(@RequestBody RelationDTO relationDTO) {

        Long customerId = relationDTO.getCustomerId();

        Long addressTypeId = relationDTO.getAddressId();

        if (!customerService.findById(customerId).isPresent()) {
            throw new CustomException("Customer with id"+customerId+"not found");
        }
        if (!addressTypeService.existsById(addressTypeId)) {
            throw new CustomException("AddressType with id"+addressTypeId+"not found");
        }
        if (relationService.existsByCustomerIdAndAddresTypeId(customerId, addressTypeId)) {
            throw new CustomException("Relation with id"+customerId+"already exists");
        }

        Relation relation =  relationService.createRelation(relationDTO.fromDTO(relationDTO));

        Customer customer = customerService.findById(customerId).get();

        customerService.addRelation(customerId,relation.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);


    }

    // Endpoint to delete a relation
    @DeleteMapping("/{customerId}/relations/{relationId}")
    public ResponseEntity<String> deleteRelation(@PathVariable Long customerId, @PathVariable Long relationId) {
        if (!customerService.findById(customerId).isPresent()) {
            throw new CustomException("Customer with id " + customerId + " not found");
        }

        if (!relationService.existsById(relationId)) {
            throw new CustomException("Relation with id " + relationId + " not found");
        }

        customerService.deleteRelation(customerId, relationId);
        return ResponseEntity.status(HttpStatus.OK).body("Relation with id " + relationId + " deleted successfully");
    }

    // Endpoint to get all relations for a customer
    @GetMapping("/{customerId}/relations")
    public ResponseEntity<List<Relation>> getAllRelations(@PathVariable Long customerId) {
        if (!customerService.findById(customerId).isPresent()) {
            throw new CustomException("Customer with id " + customerId + " not found");
        }

        List<Relation> relations = customerService.getAllRelations(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(relations);
    }
}
}