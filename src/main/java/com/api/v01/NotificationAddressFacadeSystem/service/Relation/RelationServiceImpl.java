package com.api.v01.NotificationAddressFacadeSystem.service.Relation;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.RelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;

    @Override
    public Relation createRelation(Relation relation) {
        return relationRepository.save(relation);
    }

    @Override
    public boolean existsByCustomerIdAndAddresTypeId(Long customerId, Long addresTypeId) {
        return relationRepository.existsByCustomerIdAndAddressTypeId(customerId,addresTypeId);
    }


    @Override
    public boolean existsById(Long id) {
        return relationRepository.existsById(id);
    }
}
