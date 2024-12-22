package com.api.v01.NotificationAddressFacadeSystem.service.Relation;

import com.api.v01.NotificationAddressFacadeSystem.data.Relation.Relation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RelationService {
    Relation createRelation(Relation relation);

    boolean existsByCustomerIdAndAddresTypeId(Long customerId, Long addresTypeId);

    void deleteRelation(Long customerId, Long relationId);

    List<Relation> getAllRelations(Long customerId);
}
