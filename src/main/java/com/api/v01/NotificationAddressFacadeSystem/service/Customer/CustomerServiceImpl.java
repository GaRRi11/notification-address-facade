package com.api.v01.NotificationAddressFacadeSystem.service.Customer;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Customer.CustomerRepository;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.RelationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RelationRepository relationRepository;

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateUsername(Long id, String username) {
        Customer customer = findById(id).get();
        customer.setUsername(username);
        customer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer addRelation(Long customerId, Long relationId) {
        Customer customer = findById(customerId).get();
        customer.setRelationIds(List.of(relationId));
        return customerRepository.save(customer);
    }

    @Override
    public void deleteRelation(Long customerId, Long relationId) {
        Customer customer = findById(customerId).get();
        List<Long> updatedRelations = customer.getRelationIds();
        updatedRelations.remove(relationId);
        customer.setRelationIds(updatedRelations);
        customer.setUpdatedAt(LocalDateTime.now());
        customerRepository.save(customer);
        relationRepository.deleteById(relationId);
    }

    @Override
    public List<Relation> getAllRelations(Long customerId) {
        return customerRepository.getAllRelations(customerId);
    }


}
