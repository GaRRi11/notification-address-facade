package com.api.v01.NotificationAddressFacadeSystem.service.Customer;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {

    Optional<Customer> findByUsername(String username);

    Customer createCustomer(Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Customer updateUsername(Long id, String username);

    Customer addRelation(Long customerId, Long addressId);

    void deleteRelation(Long customerId, Long addressId);

    List<Relation> getAllRelations(Long customerId);
}
