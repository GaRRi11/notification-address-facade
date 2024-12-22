package com.api.v01.NotificationAddressFacadeSystem.data.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
public class AddressDTO {

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotNull(message = "Address Type ID cannot be null")
    private Long addressTypeId;


    @NotBlank(message = "Address text cannot be blank")
    @Size(max = 100, message = "Address text must not exceed 30 characters")
    private String text;

    public Address fromDTO(AddressDTO addressDTO) {
        return Address.builder()
                .customerId(addressDTO.getCustomerId())
                .addressTypeId(addressDTO.getAddressTypeId())
                .text(addressDTO.getText())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
