package com.api.v01.NotificationAddressFacadeSystem.controller.customer;

import com.api.v01.NotificationAddressFacadeSystem.data.Address.Address;
import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Customer.CustomerDTO;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import com.api.v01.NotificationAddressFacadeSystem.exceptions.CustomException;
import com.api.v01.NotificationAddressFacadeSystem.service.AddressService.AddressService;
import com.api.v01.NotificationAddressFacadeSystem.service.Customer.CustomerService;
import com.api.v01.NotificationAddressFacadeSystem.service.Relation.RelationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
@Validated
public class CustomerApiController {

    private final CustomerService customerService;
    private final RelationService relationService;
    private final AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable @Min(value = 1, message = "ID must be a positive number") Long id) {

        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new CustomException("Customer with id " + id + " not found"));

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> addCustomer(@Valid  @RequestBody CustomerDTO customerDTO) {
        if (customerService.findByUsername(customerDTO.getUsername()).isPresent()) {
            throw new CustomException("Customer with username"+customerDTO.getUsername()+"already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDTO.fromDTO(customerDTO)));
    }

    @PutMapping("/{customerId}/update-username")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable @Min(value = 1, message = "ID must be a positive number") Long customerId
            , @RequestBody CustomerDTO customerDTO) {

        if (customerService.findById(customerId).isEmpty()) {
            throw new CustomException("Customer with id"+customerId+"not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateUsername(customerId,customerDTO.getUsername()));
    }

    @GetMapping("/{customerId}/relations")
    public ResponseEntity<List<Relation>> getAllRelations(
            @PathVariable @Min(value = 1, message = "ID must be a positive number") Long customerId) {
        if (customerService.findById(customerId).isEmpty()) {
            throw new CustomException("Customer with id " + customerId + " not found");
        }

        List<Relation> relations = customerService.getAllRelations(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(relations);
    }

    @GetMapping("/{customerId}/addresses")
    public ResponseEntity<List<Address>> getAlladdresses(
            @PathVariable @Min(value = 1, message = "ID must be a positive number") Long customerId) {
        if (customerService.findById(customerId).isEmpty()) {
            throw new CustomException("Customer with id " + customerId + " not found");
        }

        List<Address> addresses = addressService.findByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteCustomerById(
            @PathVariable @Min(value = 1, message = "ID must be a positive number") Long id) {

        if (customerService.findById(id).isEmpty()) {
            throw new CustomException("Customer with id " + id + " not found");
        }

        // Delete the customer
        customerService.deleteCustomerById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Customer with id " + id + " has been successfully deleted");
    }


    @GetMapping("/by-address-type/{addressTypeId}")
    public ResponseEntity<List<Customer>> findCustomersByAddressTypeId(
            @PathVariable @Min(value = 1, message = "Address Type ID must be a positive number") Long addressTypeId) {

        // Validate if the address type exists
        if (addressService.findById(addressTypeId).isEmpty()) {
            throw new CustomException("Address Type with ID " + addressTypeId + " not found");
        }

        // Step 1: Get all relations by addressTypeId
        List<Relation> relations = relationService.getRelationsByAddressTypeId(addressTypeId);
        if (relations.isEmpty()) {
            throw new CustomException("No relations found for Address Type ID " + addressTypeId);
        }

        // Step 2: Extract all customer IDs from the relations
        List<Long> customerIds = relations.stream()
                .map(Relation::getCustomerId)
                .distinct()
                .toList();

        if (customerIds.isEmpty()) {
            throw new CustomException("No customers associated with Address Type ID " + addressTypeId);
        }

        // Step 3: Retrieve all customers by the extracted customer IDs
        List<Customer> customers = customerService.findCustomersByIds(customerIds);

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }



}

