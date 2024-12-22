package com.api.v01.NotificationAddressFacadeSystem.service.Relation;

import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.RelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;

    @Override
    public Relation createRelation(Relation relation) {
        return relationRepository.save(relation);
    }

    @Override
    public boolean existsByCustomerIdAndAddressTypeId(Long customerId, Long addressTypeId) {
        return relationRepository.existsByCustomerIdAndAddressTypeId(customerId,addressTypeId);
    }


    @Override
    public boolean existsById(Long id) {
        return relationRepository.existsById(id);
    }
}
