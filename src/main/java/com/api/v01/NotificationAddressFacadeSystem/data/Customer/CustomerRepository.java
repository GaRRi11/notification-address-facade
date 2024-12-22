package com.api.v01.NotificationAddressFacadeSystem.data.Customer;

import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);

    List<Relation> getAllRelations (Long customerId);


}
