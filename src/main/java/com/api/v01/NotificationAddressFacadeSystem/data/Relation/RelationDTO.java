package com.api.v01.NotificationAddressFacadeSystem.data.Relation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RelationDTO {

    private Long customerId;

    private Long addressId;

    public   Relation fromDTO(RelationDTO relationDTO) {
        Relation relation = Relation.builder()
                .customerId(relationDTO.getCustomerId())
                .addressTypeId(relationDTO.addressId)
                .createdAt(LocalDateTime.now())
                .build();
        return relation;
    }
}
