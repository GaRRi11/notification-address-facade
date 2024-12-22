package com.api.v01.NotificationAddressFacadeSystem.service.Customer;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Customer.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

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

    }

    @Override
    @Transactional
    public Customer addRelation(Long customerId, Long relationId) {
        Customer customer = findById(customerId).get();
        customer.setRelationIds(List.of(relationId));
        return customerRepository.save(customer);
    }


}
