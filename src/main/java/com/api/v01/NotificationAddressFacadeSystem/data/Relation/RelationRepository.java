package com.api.v01.NotificationAddressFacadeSystem.data.Relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RelationRepository extends JpaRepository<Relation, Long> {

    boolean existsByCustomerIdAndAddressTypeId(Long customerId, Long addressTypeId);
}
