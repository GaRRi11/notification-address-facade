package com.api.v01.NotificationAddressFacadeSystem.data.Address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressTextUpdateDTO {

    @NotNull(message = "Address ID cannot be null")
    private Long addressId;

    @Size(max = 100, message = "Address text must not exceed 100 characters")
    private String text;
}
