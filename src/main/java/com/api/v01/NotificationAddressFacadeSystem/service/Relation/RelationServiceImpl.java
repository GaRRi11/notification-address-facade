package com.api.v01.NotificationAddressFacadeSystem.service.Relation;

import com.api.v01.NotificationAddressFacadeSystem.data.Customer.Customer;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import com.api.v01.NotificationAddressFacadeSystem.data.Relation.RelationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;

    @Override
    public Relation createRelation(Long customerId, Long addressTypeId) {
        Relation relation = Relation.builder()
                .customerId(customerId)
                .addressTypeId(addressTypeId)
                .createdAt(LocalDateTime.now())
                .build();
        return relationRepository.save(relation);
    }

    @Override
    public boolean existsById(Long id) {
        return relationRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        relationRepository.deleteById(id);
    }

    @Override
    public List<Relation> findAllByCustomerId(Long customerId) {
        return relationRepository.findAllByCustomerId(customerId);
    }

    @Override
    public void deleteByAddressTypeId(Long addressTypeId) {
        relationRepository.deleteAllByAddressTypeId(addressTypeId);
    }

    @Override
    public Optional<Relation> findByCustomerIdAndAddressTypeId(Long customerId, Long addressTypeId) {
        return relationRepository.findByCustomerIdAndAddressTypeId(customerId,addressTypeId);
    }

    @Override
    public List<Customer> getAllCustomersByAddressTypeId(Long addressTypeId) {
        return relationRepository.getAllCustomersByAddressTypeId(addressTypeId);
    }

    @Override
    public List<Relation> getRelationsByAddressTypeId(Long addressTypeId) {
        return relationRepository.findAllByAddressTypeId(addressTypeId);
    }
}
