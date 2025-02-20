package com.api.v01.NotificationAddressFacadeSystem.service.Customer;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CustomerService {

    Optional<Customer> findByUsername(String username);

    Customer createCustomer(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Customer updateUsername(Long id, String username);

    Customer addRelation(Long customerId, Long addressId);

    void deleteRelation(Long customerId, Long addressId);

    List<Relation> getAllRelations(Long customerId);

    void deleteCustomerById(@Min(value = 1, message = "ID must be a positive number") Long id);

    List<Customer> findCustomersByIds(List<Long> customersIds);
}
