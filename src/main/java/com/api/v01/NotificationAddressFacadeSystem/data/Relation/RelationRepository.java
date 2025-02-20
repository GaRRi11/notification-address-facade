package com.api.v01.NotificationAddressFacadeSystem.data.Relation;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {

    boolean existsByCustomerIdAndAddressTypeId(Long customerId, Long addressTypeId);

    void deleteAllByAddressTypeId(Long addressTypeId);

    List<Relation> findAllByCustomerId(Long customerId);

    Optional<Relation> findByCustomerIdAndAddressTypeId(Long customerId, Long addressTypeId);

    List<Relation> findAllByAddressTypeId(Long addressTypeId);

    @Query("SELECT r.customerId FROM Relation r WHERE r.addressTypeId = :addressTypeId")
    List<Customer> getAllCustomersByAddressTypeId(Long addressTypeId);
}
