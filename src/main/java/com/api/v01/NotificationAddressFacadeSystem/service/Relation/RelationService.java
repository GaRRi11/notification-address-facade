package com.api.v01.NotificationAddressFacadeSystem.service.Relation;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RelationService {


    Relation createRelation(Long customerId, Long addressTypeId);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<Relation> findAllByCustomerId(Long customerId);

    void deleteByAddressTypeId(Long addressTypeId);

    Optional<Relation> findByCustomerIdAndAddressTypeId(Long customerId, Long addressTypeId);

    List<Customer> getAllCustomersByAddressTypeId(@Min(value = 1, message = "Address Type ID must be a positive number") Long addressTypeId);

    List<Relation> getRelationsByAddressTypeId(@Min(value = 1, message = "Address Type ID must be a positive number") Long addressTypeId);
}
