package com.api.v01.NotificationAddressFacadeSystem.data.Customer;

import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    @Query("SELECT c.relationIds FROM Customer c WHERE c.id = :customerId")
    List<Long> getAllRelations(Long customerId);

    @Query("SELECT c FROM Customer c WHERE c.id IN :customerIds")
    List<Customer> findCustomersByIds(List<Long> customersIds);
}
