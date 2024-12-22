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
    @Transactional
    public void deleteRelation(Long customerId, Long relationId) {
        Customer customer = findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Long> relationIds = customer.getRelationIds();

        if (!relationIds.contains(relationId)) {
            throw new RuntimeException("Relation with id " + relationId + " is not associated with customer " + customerId);
        }

        relationIds.remove(relationId);
        customer.setRelationIds(relationIds);
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public List<Relation> getAllRelations(Long customerId) {
        Customer customer = findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Long> relationIds = customer.getRelationIds();
        return relationRepository.findAllById(relationIds);
    }
}
