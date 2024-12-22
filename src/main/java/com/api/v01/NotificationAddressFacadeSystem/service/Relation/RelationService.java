package com.api.v01.NotificationAddressFacadeSystem.service.Relation;

import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import org.springframework.stereotype.Component;

@Component
public interface RelationService {
    Relation createRelation(Relation relation);

    boolean existsByCustomerIdAndAddressTypeId(Long customerId, Long addressTypeId);

    boolean existsById(Long id);
}
