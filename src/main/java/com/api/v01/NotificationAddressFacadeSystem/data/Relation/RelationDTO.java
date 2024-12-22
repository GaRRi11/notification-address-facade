package com.api.v01.NotificationAddressFacadeSystem.data.Relation;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RelationDTO {

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotNull(message = "Address ID cannot be null")
    private Long addressId;

    public   Relation fromDTO(RelationDTO relationDTO) {
        return Relation.builder()
                .customerId(relationDTO.getCustomerId())
                .addressTypeId(relationDTO.addressId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
