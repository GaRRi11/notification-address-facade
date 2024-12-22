package com.api.v01.NotificationAddressFacadeSystem.data.Address;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressDTO {

    private Long customerId;
    private Long addressTypeId;
    private String text;

    public Address fromDTO(AddressDTO addressDTO) {
        Address address = Address.builder()
                .customerId(addressDTO.getCustomerId())
                .addressTypeId(addressDTO.getAddressTypeId())
                .text(addressDTO.getText())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return address;
    }
}
